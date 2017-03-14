package com.example.lenovo.learning;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Random;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

public class LoginActivity extends Activity {
    String APPKEY = "1b41998782634";
    String APPSECRET = "2234eec827102c8486efe3929ae95b85";
    private Button btn_login;
    private EditText txt_username;
    private EditText txt_password;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        SMSSDK.initSDK(this,APPKEY,APPSECRET);
        btn_login = (Button) findViewById(R.id.btn_login);
        txt_username = (EditText) findViewById(R.id.et_username);
        txt_password = (EditText) findViewById(R.id.et_password);
        checkBox = (CheckBox) findViewById(R.id.cb_rem);
        TextView forgetPassword = (TextView)findViewById(R.id.forgetPassword);
        //TextView register = (TextView)findViewById(R.id.btn_register);
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
        String loginUrl = "";
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
        /****************登录验证，通过服务器访问数据库**********************/
        /*OkHttpUtils.get().url(loginUrl).addParams("",username).addParams("",password).build().execute(new Callback() {
            @Override
            public Object parseNetworkResponse(Response response) throws IOException {
                return null;
            }

            @Override
            public void onError(Request request, Exception e) {
                Toast.makeText(LoginActivity.this,"对不起，验证错误",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Object response) {
                Intent intent = new Intent(LoginActivity.this,homePage.class);
                startActivity(intent);
            }
        });*/
        /*************测试使用，不考虑验证，直接登录进入********************/
        Intent intent = new Intent(LoginActivity.this, SampleActivity.class);
        startActivity(intent);
    }

    public void Register(View view){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivityForResult(intent,1);
    }

    public void forgetPassword(View view){
        //绑定手机号
        RegisterPage registerPage = new RegisterPage();
        //回调事件
        registerPage.setRegisterCallback(new EventHandler(){
            @Override
            public void afterEvent(int event,int result,Object data){
                //判断结果是否已经完成
                if(result==SMSSDK.RESULT_COMPLETE){
                    //获取数据data
                    HashMap<String,Object> map=(HashMap<String,Object>)data;
                    //获取国家信息 ,手机号信息
                    String country = (String)map.get("country");
                    String phone = (String)map.get("phone");
                    submitUserInfo(country,phone);
                }
            }
        });
        //显示结果
        registerPage.show(this);
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

    public void submitUserInfo(String country,String phone){
        Random r = new Random();
        String uid =Math.abs(r.nextInt())+"";
        String name= "sjh";
        SMSSDK.submitUserInfo(uid,name,null,country,phone);
    }


}
