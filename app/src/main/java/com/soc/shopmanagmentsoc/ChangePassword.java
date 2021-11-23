package com.soc.shopmanagmentsoc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassword extends AppCompatActivity {
    EditText edt_OldPassword,edt_New_Password,edt_Confirm_Password;
    String str_OldPassword,str_New_Password,str_Confirm_Password;
    Button btn_ChangePassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password);

        edt_OldPassword = findViewById(R.id.edt_OldPassword);
        edt_New_Password = findViewById(R.id.edt_New_Password);
        edt_Confirm_Password = findViewById(R.id.edt_Confirm_Password);
        btn_ChangePassword = findViewById(R.id.btn_ChangePassword);

        btn_ChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_OldPassword = edt_OldPassword.getText().toString();
                str_New_Password = edt_New_Password.getText().toString();
                str_Confirm_Password = edt_Confirm_Password.getText().toString();

                if (!str_OldPassword.equals("")){
                    if (!str_New_Password.equals("")){
                        if (!str_Confirm_Password.equals("")){
                            if (str_Confirm_Password.equals(str_New_Password)){

                            }else{
                                Toast.makeText(ChangePassword.this,"Enter Confirm Password",Toast.LENGTH_LONG);
                            }
                        }else{
                            Toast.makeText(ChangePassword.this,"Enter Confirm Password",Toast.LENGTH_LONG);
                        }
                    }else{
                        Toast.makeText(ChangePassword.this,"Enter New Password",Toast.LENGTH_LONG);
                    }
                }else{
                    Toast.makeText(ChangePassword.this,"Enter Old Password",Toast.LENGTH_LONG);
                }
            }
        });
    }
}