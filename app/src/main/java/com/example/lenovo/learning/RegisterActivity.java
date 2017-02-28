package com.example.lenovo.learning;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by lenovo on 2017/2/27.
 */

public class RegisterActivity extends Activity{
    String APPKEY = "1b41998782634";
    String APPSECRET = "2234eec827102c8486efe3929ae95b85";
    private EditText txt_username;
    private EditText txt_FirstPassword;
    private EditText txt_SecondPassword;
    private EditText txt_phonenumber;
    private EditText txt_IdentifyingCode;
    private Button btn_getIdentifyingCode;
    private Button btn_submit;
    private Button btn_clear;
    private EventHandler eh;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txt_username  = (EditText)findViewById(R.id.txt_registername);
        txt_FirstPassword = (EditText)findViewById(R.id.txt_register_firstpassword);
        txt_SecondPassword = (EditText)findViewById(R.id.txt_register_secondpassword);
        txt_phonenumber = (EditText)findViewById(R.id.txt_registerphonenumber);
        txt_IdentifyingCode = (EditText)findViewById(R.id.txt_IdentifyingCode);
        btn_getIdentifyingCode = (Button)findViewById(R.id.btn_getIdentifyingCode);
        btn_submit = (Button)findViewById(R.id.btn_submit);
        btn_clear = (Button)findViewById(R.id.btn_clear);
        SMSSDK.initSDK(this,APPKEY,APPSECRET);
        SMSSDK.registerEventHandler(eh);
    }
    public void Submit(View view){
        String userName = txt_username.getText().toString();
        String firstPassword = txt_FirstPassword.getText().toString();
        String secondPassword = txt_SecondPassword.getText().toString();
        String phoneNumber = txt_phonenumber.getText().toString();
        String identifyingCode = txt_IdentifyingCode.getText().toString();
        if(!firstPassword.equals(secondPassword)){
            Toast.makeText(this,"对不起，您两次输入的密码不一致",Toast.LENGTH_LONG).show();
        }else if(userName.equals("")){
            Toast.makeText(this,"请输入用户名",Toast.LENGTH_LONG).show();
        }else if(firstPassword.equals("")||secondPassword.equals("")){
            Toast.makeText(this,"请输入密码",Toast.LENGTH_LONG).show();
        }else{
            String IdentifyingCode = txt_IdentifyingCode.getText().toString();
            //验证短信验证码
            SMSSDK.registerEventHandler(eh);
            SMSSDK.submitVerificationCode("86",phoneNumber,identifyingCode);
            SMSSDK.unregisterEventHandler(eh);
        }
        Intent intent = new Intent(this,LoginActivity.class);
        intent.putExtra("username",userName);
        intent.putExtra("password",firstPassword);
        setResult(RESULT_OK,intent);
        finish();
    }

    public void Clear(View view){
        txt_username.setText("");
        txt_FirstPassword.setText("");
        txt_SecondPassword.setText("");
        txt_phonenumber.setText("");
        txt_IdentifyingCode.setText("");
    }

    public void getIdentifyingCode(View view){
        String phoneNumber = txt_phonenumber.getText().toString();
        if(phoneNumber.equals("")){
            Toast.makeText(this,"请输入手机号码",Toast.LENGTH_LONG).show();
        }else{
            eh = new EventHandler(){
                @Override
                public void afterEvent(int event, int result, Object data) {
                    if (result == SMSSDK.RESULT_COMPLETE) {
                        //回调完成
                        if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                            //提交验证码成功
                            Toast.makeText(RegisterActivity.this,"服务器已经接收到请求",Toast.LENGTH_LONG).show();
                        }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                            //获取验证码成功
                            Toast.makeText(RegisterActivity.this,"获取验证码成功",Toast.LENGTH_LONG).show();
                        }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                            //返回支持发送验证码的国家列表
                        }
                    }else{
                        ((Throwable)data).printStackTrace();
                    }
                }
            };
            SMSSDK.getVerificationCode("86",phoneNumber);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eh);
    }
}
