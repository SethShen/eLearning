package com.example.lenovo.learning;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import cn.smssdk.SMSSDK;

public class LoginActivity extends Activity {
    String APPKEY = "1b41998782634";
    String APPSECRET = "2234eec827102c8486efe3929ae95b85";
    private Button btn_login;
    private Button btn_register;
    private EditText txt_username;
    private EditText txt_password;
    private CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        SMSSDK.initSDK(this,APPKEY,APPSECRET);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_register = (Button)findViewById(R.id.btn_register);
        txt_username = (EditText) findViewById(R.id.et_username);
        txt_password = (EditText) findViewById(R.id.et_password);
        checkBox = (CheckBox) findViewById(R.id.cb_rem);
        SharedPreferences sharedPreference = getSharedPreferences("users", Activity.MODE_PRIVATE);
        String username = sharedPreference.getString("username", "");
        String password = sharedPreference.getString("password", "");
        String checked = sharedPreference.getString("checked", "");
        txt_username.setText(username);
        txt_password.setText(password);
        if (checked.equals("yes")) {
            checkBox.setChecked(true);
        }else{
            checkBox.setChecked(false);
        }

    }
    public void Login(View view){
        String username = txt_username.getText().toString();
        String password = txt_password.getText().toString();
        if(checkBox.isChecked()){
            SharedPreferences sharedPref = getSharedPreferences("users", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("username", username.toString());
            editor.putString("password", password.toString());
            editor.putString("checked","yes");
            editor.commit();
        }else{
            SharedPreferences sharedPref = getSharedPreferences("users", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("username", "");
            editor.putString("password", "");
            editor.putString("checked","no");
            editor.commit();
        }
    }

    public void Register(View view){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case 1:
                if(resultCode==RESULT_OK){
                    String username = data.getStringExtra("username");
                    String password = data.getStringExtra("password");
                    txt_username.setText(username);
                    txt_password.setText(password);
                }
                break;
            default:
        }
    }
}
