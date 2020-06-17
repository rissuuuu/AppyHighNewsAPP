package com.appyhigh.newsfeed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener {
    EditText username, password;
    Button login;
    TextView forgotpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.id_username);
        password = findViewById(R.id.id_password);
        login = findViewById(R.id.id_Login);
        forgotpassword = findViewById(R.id.id_forgotpassword);
        login.setOnClickListener(this);
        forgotpassword.setOnClickListener(this);
        SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
        String check=preferences.getString("save","");
        if(check.equals("true")){
            startActivity(new Intent(Login.this, Dashboard.class));

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_Login:
                SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
                String user = username.getText().toString();
                String pass = password.getText().toString();
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("save", "true");
                editor.apply();
                startActivity(new Intent(Login.this, Dashboard.class));
                break;

            case R.id.id_forgotpassword:
                Toast.makeText(getApplicationContext(), "Forgot Password", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
