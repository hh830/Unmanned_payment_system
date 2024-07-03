package org.techtown.home2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {
    private static String ID_ADDRESS = "121.168.74.44";
    private static String TAG = "phptest";
    private static Button memberShip, loginbtn;
    private static EditText inputId, inputPass;
    private static TextView mTextViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        memberShip = (Button) findViewById(R.id.memberShip);
        loginbtn = (Button) findViewById(R.id.loginbtn);
        mTextViewResult = (TextView)findViewById(R.id.mTextViewResult);

        memberShip.setOnClickListener(new View.OnClickListener() { //회원가입 버튼
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), membership.class);
                startActivity(intent);
            }
        });

        inputId = (EditText) findViewById(R.id.inputId);//로그인 아이디 입력
        inputPass = (EditText) findViewById(R.id.inputPass);//로그인 비밀번호 입력

        loginbtn.setOnClickListener(new View.OnClickListener() { //로그인 버튼
            @Override
            public void onClick(View view) {
                String id = inputId.getText().toString();//로그인 아이디
                String pass = inputPass.getText().toString();//로그인 비밀번호

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                Toast.makeText(getApplicationContext(), "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show();
                                String userID = jsonResponse.getString("userID");
                                String userPassword = jsonResponse.getString("userPassword");
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class); //shopActivity에 넘기기
                                intent.putExtra("userID", userID);
                                intent.putExtra("userPassword", userPassword);


                            }else{
                                Toast.makeText(getApplicationContext(), "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(id, pass, responseListener);

                RequestQueue queue = Volley.newRequestQueue(login.this);
                queue.add(loginRequest);


                InsertData task = new InsertData();
                task.execute("http://" + ID_ADDRESS + "/login3.php", id, pass);

            }
        });
    }

    class LoginRequest extends StringRequest {
        final static private String URL = "http://121.168.74.44/login3.php"; // "http:// 퍼블릭 DSN 주소/Login.php";
        private Map<String, String> parameters;

        public LoginRequest(String userID, String userPassword, Response.Listener<String> listener) {
            super(Method.POST, URL, listener, null);

            parameters = new HashMap<>();
            parameters.put("userID", userID);
            parameters.put("userPassword", userPassword);
        }

        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            return parameters;
        }
    }

    class InsertData extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progressDialog = progressDialog.show(login.this,
                    "Please Wait", null, true, true);
        }

        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);

            progressDialog.dismiss();
            mTextViewResult.setText(result);
            Log.d(TAG, "Post response - " + result);

        }

        @Override
        protected String doInBackground(String... params) {
            //   task.execute("http://" + ID_ADDRESS + "/insert.php", name, sex, dateofbirth, number, id, password, passCheck);
            String id = (String) params[1];
            String pass = (String) params[2];

            String serverURL = (String) params[0];
            String postParameters = "id=" + id + "&pw=" + pass;

            try {
                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();

                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "Post response code - " + responseStatusCode);

                InputStream inputStream;
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();

                } else {
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");

                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }

                bufferedReader.close();
                return sb.toString();
            } catch (Exception e) {
                Log.d(TAG, "InsertData: Error", e);
                return new String("Error:" + e.getMessage());
            }
        }
    }


}