package com.soc.shopmanagmentsoc.adminpart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.soc.shopmanagmentsoc.ChangePassword;
import com.soc.shopmanagmentsoc.LoginActivity;
import com.soc.shopmanagmentsoc.Profile;
import com.soc.shopmanagmentsoc.R;

public class AdminUserHome extends AppCompatActivity implements View.OnClickListener {
    TextView tv_admin_user_name,tv_admin_user_phone;

    SharedPreferences sharedPreferences;

    CardView cvUserInfo,cvShopInfo,cvUserType,cvChnagePassword,cvProfile,cvReporting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_user_home);
        sharedPreferences = getSharedPreferences(getResources().getString(R.string.shareData),MODE_PRIVATE);

        tv_admin_user_name= findViewById(R.id.tv_admin_user_name);
        tv_admin_user_name.setText(sharedPreferences.getString("full_name",""));
        tv_admin_user_phone= findViewById(R.id.tv_admin_user_phone);
        tv_admin_user_phone.setText(sharedPreferences.getString("mobile_no",""));

        cvUserInfo = findViewById(R.id.cvUserInfo);
        cvShopInfo = findViewById(R.id.cvShopInfo);
        cvUserType = findViewById(R.id.cvUserType);
        cvChnagePassword = findViewById(R.id.cvChnagePassword);
        cvProfile = findViewById(R.id.cvProfile);
        cvReporting = findViewById(R.id.cvReporting);

        cvUserInfo.setOnClickListener(this);
        cvShopInfo.setOnClickListener(this);
        cvUserType.setOnClickListener(this);
        cvChnagePassword.setOnClickListener(this);
        cvProfile.setOnClickListener(this);
        cvReporting.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cvUserInfo:
                Intent intent = new Intent(AdminUserHome.this, DashBordProscessData.class);
                intent.putExtra("Name","View Admin Users");
                startActivity(intent);
                break;
            case R.id.cvUserType:
                Intent intents = new Intent(AdminUserHome.this, DashBordProscessData.class);
                intents.putExtra("Name","View User Type");
                startActivity(intents);
                break;
            case R.id.cvShopInfo:
                Intent intentss = new Intent(AdminUserHome.this, DashBordProscessData.class);
                intentss.putExtra("Name","View Shop Info");
                startActivity(intentss);
                break;
            case R.id.cvChnagePassword:
                Intent intent1 = new Intent(AdminUserHome.this, ChangePassword.class);
                startActivity(intent1);
                break;
            case R.id.cvProfile:
                Intent intents2= new Intent(AdminUserHome.this, Profile.class);
                startActivity(intents2);
                break;
            case R.id.cvReporting:
                /*Intent intents3= new Intent(AdminUserHome.this, DashBordProscessData.class);
                intents3.putExtra("Name","View Shop Info");
                startActivity(intents3);*/
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_search:
                getSharedPreferences(getResources().getString(R.string.shareData),MODE_PRIVATE).edit().clear().apply();
                Intent i = new Intent(AdminUserHome.this, LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                finish();

                break;

        }

        return false;
    }
}