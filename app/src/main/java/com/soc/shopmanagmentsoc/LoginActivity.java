package com.soc.shopmanagmentsoc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.soc.shopmanagmentsoc.adminpart.AdminUserHome;
import com.soc.shopmanagmentsoc.shopuser.ShopUserHome;
import com.soc.shopmanagmentsoc.util.AllClassHere;
import com.soc.shopmanagmentsoc.util.httpd.HttpClientPost;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    EditText edit_userName,edit_userPassword;
    CheckBox cbSavePass;
    String str_userName="",str_userPassword="",strStatusCode="",strUserType="",strMsg="",strValue="",strCheck="";
    Button btn_logIn;
    SharedPreferences sharedPreferences;
    AllClassHere allClassHere = new AllClassHere(LoginActivity.this);

    public static String rootPath = "http://103.91.54.60/ShopManagment/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        sharedPreferences = getSharedPreferences(getResources().getString(R.string.shareData),MODE_PRIVATE);

        strCheck = sharedPreferences.getString("check","");

        edit_userName =  findViewById(R.id.edit_userName);
        edit_userPassword =  findViewById(R.id.edit_userPassword);
        btn_logIn =  findViewById(R.id.btn_logIn);
        cbSavePass =  findViewById(R.id.cbSavePass);
        if (strCheck.equals("1")){
            edit_userName.setText(sharedPreferences.getString("admin_user_name",""));
            edit_userPassword.setText(sharedPreferences.getString("password",""));
            if (sharedPreferences.getString("user_type_name","").equals("ADMIN")){
                startActivity(new Intent(LoginActivity.this, AdminUserHome.class));
                finish();
            }
            else if (sharedPreferences.getString("user_type_name","").equals("Customer")){
                startActivity(new Intent(LoginActivity.this, ShopUserHome.class));
                finish();
            }
            else if (sharedPreferences.getString("user_type_name","").equals("Shop User")){
                startActivity(new Intent(LoginActivity.this, ShopUserHome.class));
                finish();
            }
        }
        btn_logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_userName =  edit_userName.getText().toString().trim();
                str_userPassword =  edit_userPassword.getText().toString().trim();
                if (!str_userName.equals("")){
                    if (!str_userPassword.equals("")){
                        UserLogIn userLogIn = new UserLogIn();
                        userLogIn.execute();
                    }else{
                        allClassHere.validationAlert("Please Enter Your User Name");
                    }
                }else{
                    allClassHere.validationAlert("Please Enter Your Password");
                }
            }
        });
    }

    private class UserLogIn extends AsyncTask<Void, Void, String> {
        ProgressDialog pd = null;
        @Override
        protected void onPreExecute() {
            pd = ProgressDialog.show(LoginActivity.this, "Process Data", "Please wait...");
        }

        @Override
        protected String doInBackground(Void... params) {

            List<Pair<String, String>> postParameters = new ArrayList<>();
            postParameters.add(new Pair("Type","Admin LogIn"));
            postParameters.add(new Pair("USER_ID",str_userName));
            postParameters.add(new Pair("USER_PASSWORD",str_userPassword));
            String result = "";
            try {
                String response = HttpClientPost.execute(LoginActivity.rootPath+"DataProcess.php", postParameters);
                result = response.toString();
                result = result.replaceAll("\n", "");

            } catch (Exception e) {
                strValue= "Connection Timeout";
                Log.e("log_tag", "Error in http connection!!" + e.toString());
            }
            try {
                JSONObject jsonObject=new JSONObject(result);
                strStatusCode = jsonObject.getString("status_code");
                strMsg = jsonObject.getString("msg");
                strValue = jsonObject.getString("values");

                JSONArray jsonArray3 = new JSONArray(strValue);
                for (int i = 0; i < jsonArray3.length(); i++) {
                    JSONObject jsonObjectProducts = jsonArray3.getJSONObject(i);

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    if (cbSavePass.isChecked()){
                        editor.putString("check", "1");
                    }else {
                        editor.putString("check", "0");
                    }
                    editor.putString("admin_id", jsonObjectProducts.getString("admin_id"));
                    editor.putString("admin_user_name", jsonObjectProducts.getString("admin_user_name"));
                    editor.putString("password",edit_userPassword.getText().toString().trim());
                    editor.putString("full_name", jsonObjectProducts.getString("full_name"));
                    editor.putString("mobile_no", jsonObjectProducts.getString("mobile_no"));
                    editor.putString("email_address", jsonObjectProducts.getString("email_address"));
                    editor.putString("address", jsonObjectProducts.getString("address"));
                    editor.putString("latitude", jsonObjectProducts.getString("latitude"));
                    editor.putString("longitude", jsonObjectProducts.getString("longitude"));
                    editor.putString("admin_user_status", jsonObjectProducts.getString("admin_user_status"));
                    editor.putString("user_type_id", jsonObjectProducts.getString("user_type_id"));
                    editor.putString("user_type_name", jsonObjectProducts.getString("user_type_name"));
                    editor.commit();
                    strUserType = jsonObjectProducts.getString("user_type_name");

                }
            } catch (Exception e) {
                Log.e("log_tag", "Error in http connection!!" + e.toString());
            }

            return strStatusCode;
        }

        @Override
        protected void onPostExecute(String result) {
            if (pd != null) {
                pd.dismiss();
            }
            if (strStatusCode.equals("200")){
                if(strUserType.equals("ADMIN")){
                    startActivity(new Intent(LoginActivity.this, AdminUserHome.class));
                    finish();
                }else if (strUserType.equals("Customer")){
                    startActivity(new Intent(LoginActivity.this, ShopUserHome.class));
                    finish();
                }else if (strUserType.equals("Shop User")){
                    startActivity(new Intent(LoginActivity.this, ShopUserHome.class));
                    finish();
                }
            }else {
                allClassHere.errorAlertBtn1("Failed",strValue,"Ok");
            }

        }
    }
}