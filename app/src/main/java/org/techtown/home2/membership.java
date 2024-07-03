package org.techtown.home2;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class membership extends AppCompatActivity {
    private static String ID_ADDRESS = "121.168.74.44";
    private static String TAG = "phptest";
    Button join;
    private EditText inputName, inputSex, inputId, inputPass,
            inputPassCheck, inputPhone, inputBirthday;
    private TextView mTextViewResult;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_membership);

                join = (Button) findViewById(R.id.join);
                inputName = (EditText) findViewById(R.id.inputName);
                inputSex = (EditText) findViewById(R.id.inputSex);
                inputId = (EditText) findViewById(R.id.inputId);
                inputPass = (EditText) findViewById(R.id.inputPass);
                inputPassCheck = (EditText) findViewById(R.id.inputPassCheck);
                inputPhone = (EditText) findViewById(R.id.inputPhone);
                inputBirthday = (EditText) findViewById(R.id.inputBirthday);

                mTextViewResult = (TextView)findViewById(R.id.mTextViewResult);

                mTextViewResult.setMovementMethod(new ScrollingMovementMethod());

                join.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = inputName.getText().toString();
                        String sex = inputSex.getText().toString();
                        String dateofbirth = inputBirthday.getText().toString();
                        String number = inputPhone.getText().toString();
                        String id = inputId.getText().toString();
                        String password = inputPass.getText().toString();
                        String passCheck = inputPassCheck.getText().toString();



                        InsertData task = new InsertData();
                        task.execute("http://" + ID_ADDRESS + "/signup.php", name, sex, dateofbirth, number, id, password, passCheck);

                        inputName.setText("");
                        inputSex.setText("");
                        inputId.setText("");
                        inputPass.setText("");
                        inputPassCheck.setText("");
                        inputPhone.setText("");
                        inputBirthday.setText("");
                        finish();
                    }
                });
            }

    class InsertData extends AsyncTask<String, Void, String>{


        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progressDialog = progressDialog.show(membership.this,
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
            String name = (String) params[1];
            String sex = (String) params[2];
            String dateofbirth = (String) params[3];
            String number = (String) params[4];
            String id = (String) params[5];
            String password = (String) params[6];
            String passCheck = (String) params[7];



            String serverURL = (String) params[0];
            String postParameters = "name=" + name + "&sex=" + sex +"&dateofbirth=" + dateofbirth + "&number=" + number +
                    "&userid=" + id + "&userpassword=" + password + "&reuserpassword=" + passCheck;


            try{
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
                Log.d(TAG, "Post response code - "+responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK){
                    inputStream = httpURLConnection.getInputStream();

                }else{
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");

                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }

                bufferedReader.close();
                return sb.toString();
            }catch (Exception e){
                Log.d(TAG, "InsertData: Error", e);
                return new String("Error:"+e.getMessage());
            }
        }
    }
}