package com.example.lenovo.talenthunt;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
//import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lenovo.talenthunt.JSONParser;
import com.example.lenovo.talenthunt.R;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Register extends Activity {

    private ProgressDialog pDialog;

    JSONParser jsonParser =new JSONParser();
    EditText inputemail;
    EditText inputname;
    EditText inputpassword;
    Button btn_register;

    private static String url_create_user="http://192.168.0.110/Talhunt/insert.php";//What to give here?
    private static final String TAG_SUCCESS="success";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        inputemail=(EditText)findViewById(R.id.email);
        inputname=(EditText)findViewById(R.id.name);
        inputpassword=(EditText)findViewById(R.id.password);
        btn_register=(Button)findViewById(R.id.btn);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CreateUser().execute();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    class  CreateUser extends AsyncTask<String,String,String>
    {
        protected void onPreExecute()
        {
            super.onPreExecute();
            pDialog=new ProgressDialog(Register.this);
            pDialog.setMessage("Wait");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            String email=inputemail.getText().toString();
            String name=inputname.getText().toString();
            String password=inputpassword.getText().toString();

            HashMap<String,String> p= new HashMap<String,String>();
            p.put("email",email);
            p.put("name", name);
            p.put("password", password);

            JSONObject json=jsonParser.makeHttpRequest(url_create_user,"POST",p);
            Log.d("Create Response", json.toString());
            //          int success = json.getInt(TAG_SUCCESS);

            //        if (success == 1) {
            // finish();
            //} else {

//                }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pDialog.dismiss();
        }
    }
}


