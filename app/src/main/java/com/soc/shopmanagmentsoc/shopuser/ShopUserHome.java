package com.soc.shopmanagmentsoc.shopuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.soc.shopmanagmentsoc.R;

public class ShopUserHome extends AppCompatActivity {
    TextView tv_shop_user_name,tv_shop_user_phone;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_user_home);

        sharedPreferences = getSharedPreferences(getResources().getString(R.string.shareData),MODE_PRIVATE);

        tv_shop_user_name= findViewById(R.id.tv_shop_user_name);
        tv_shop_user_name.setText(sharedPreferences.getString("full_name",""));
        tv_shop_user_phone= findViewById(R.id.tv_shop_user_phone);
        tv_shop_user_phone.setText(sharedPreferences.getString("mobile_no",""));
    }
}