package com.duung.thuydungreview01;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Model.User;
import Model.UserList;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    CheckBox cbRemember;
    Button btnLogin;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        cbRemember = findViewById(R.id.cbRemember);
        btnLogin = findViewById(R.id.btnLogin);

        sharedPreferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE);

        // Tự động đăng nhập nếu đã lưu và hợp lệ
        boolean isSaved = sharedPreferences.getBoolean("isSaved", false);
        String savedUser = sharedPreferences.getString("username", "");
        String savedPass = sharedPreferences.getString("password", "");

        if (isSaved) {
            User user = UserList.findUserByUsername(savedUser);
            if (user != null && user.getPassword().equals(savedPass)) {
                // Tự động chuyển sang màn hình ProductList
                startActivity(new Intent(LoginActivity.this, ProductListActivity.class));
                finish();
                return;
            }
        }

        // Nếu không tự động đăng nhập, thì load username/password vào EditText
        if (isSaved) {
            etUsername.setText(savedUser);
            etPassword.setText(savedPass);
            cbRemember.setChecked(true);
        }

        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            User user = UserList.findUserByUsername(username);
            if (user != null && user.getPassword().equals(password)) {
                // Lưu thông tin đăng nhập nếu chọn
                if (cbRemember.isChecked()) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", username);
                    editor.putString("password", password);
                    editor.putBoolean("isSaved", true);
                    editor.apply();
                } else {
                    sharedPreferences.edit().clear().apply();
                }

                // Chuyển màn hình
                Intent intent = new Intent(LoginActivity.this, ProductListActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Sai thông tin đăng nhập!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
