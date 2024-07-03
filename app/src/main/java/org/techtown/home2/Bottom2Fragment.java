package org.techtown.home2;

import android.app.AsyncNotedAppOp;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.FragmentActivity;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import javax.xml.transform.Result;


public class Bottom2Fragment extends Fragment {
    private ArrayList<ResultData> mSearchlist = new ArrayList<>();
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    private EditText editSearch;

    private Button button3;

    private static String IP_ADDRESS = "10.0.2.2";
    private static String TAG="phpquerytest";
    private static final String TAG_JSON="webnautes";
    private static final String TAG_ID= "id";
    private static final String TAG_prdNm="prdNm";
    private static final String TAG_brandNm="brandNm";
    String mJsonString;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_search, container, false);

        editSearch=rootView.findViewById(R.id.editSearch);
        button3=rootView.findViewById(R.id.button3);
        //searchView=rootView.findViewById(R.id.SearchView);
        recyclerView = rootView.findViewById(R.id.recyclerview_search);
        //recyclerView3 = rootView.findViewById(R.id.recyclerView3);


        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);


        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mSearchlist.clear();

                String name=editSearch.getText().toString();
                editSearch.setText(null);

                GetData task = new GetData();
                task.execute(name);

            }
        });
        mSearchlist=new ArrayList<>();


        return rootView;
    }


    class GetData extends AsyncTask<String, Void, String>
    {
        ProgressDialog progressDialog;
        String errorString =null;
        protected void onPreExecute()
        {
            super.onPreExecute();
            //progressDialog=ProgressDialog.show(Main)
        }
        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);
            if(result!=null)
            {
                mJsonString = result;
                showResult();
            }
        }

        @Override
        protected String doInBackground(String... params) {
            String text=(String)params[0];

            String serverURL= "http://"+IP_ADDRESS+"/query.php";

            String postParameters="text="+text;

            try {
                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();

                OutputStream outputStream=httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();

                int responseStatusCode=httpURLConnection.getResponseCode();
                Log.d(TAG, "response code - "+responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK)
                {
                    inputStream=httpURLConnection.getInputStream();
                }
                else{
                    inputStream=httpURLConnection.getErrorStream();
                }
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);

                StringBuilder sb=new StringBuilder();
                String line;

                while((line=bufferedReader.readLine())!=null){
                    sb.append(line);
                }
                bufferedReader.close();

                return sb.toString().trim();
            } catch (Exception e){
                Log.d(TAG, "InsertData: Error", e);
                errorString=e.toString();
                return null;
            }
        }

    }

    private void showResult()
    {
        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject item = jsonArray.getJSONObject(i);
                String id = item.getString(TAG_ID);
                String prdNm = item.getString(TAG_prdNm);
                String brandNm = item.getString(TAG_brandNm);

                ResultData resultData = new ResultData();
                resultData.setPrice(id);
                resultData.setPrdNm(prdNm);
                resultData.setBrandNm(brandNm);


                mSearchlist.add(resultData);

            }
            ResultAdapter r_adapter = new ResultAdapter(getContext(),mSearchlist);
            recyclerView.setAdapter(r_adapter);

        } catch (JSONException e) {
            Log.d(TAG, "showResult", e);

        }
    }

}