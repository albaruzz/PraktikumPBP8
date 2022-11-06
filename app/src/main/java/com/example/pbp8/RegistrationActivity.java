package com.example.pbp8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etName;
    private EditText etNIM;
    private EditText etPhone;
    private EditText etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etName = findViewById(R.id.et_name);
        etNIM = findViewById(R.id.et_nim);
        etPhone = findViewById(R.id.et_phone_number);
        etEmail = findViewById(R.id.et_email);
        Button btnNext = findViewById(R.id.btn_next);
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_next) {
            // Ambil data dari EditText
            String name = etName.getText().toString().trim();
            String nim = etNIM.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            // Pastikan seluruh EditText telah diisi
            boolean isEmptyField = false;
            if (name.isEmpty()) {
                etName.setError("Field harus diisi");
                isEmptyField = true;
            }
            if (nim.isEmpty()) {
                etNIM.setError("Field harus diisi");
                isEmptyField = true;
            }
            if (phone.isEmpty()) {
                etPhone.setError("Field harus diisi");
                isEmptyField = true;
            }
            if (email.isEmpty()) {
                etEmail.setError("Field harus diisi");
                isEmptyField = true;
            }
            // Jika seluruh EditText telah diisi, pindah Activity
            if (!isEmptyField && emailValidator(etEmail)) {
                Intent detailIntent = new Intent(this, DetailActivity.class);
                detailIntent.putExtra(DetailActivity.KEY_NAME, name);
                detailIntent.putExtra(DetailActivity.KEY_NIM, nim);
                detailIntent.putExtra(DetailActivity.KEY_PHONE, phone);
                detailIntent.putExtra(DetailActivity.KEY_EMAIL, email);
                startActivity(detailIntent);
            }
        }
    }

    public boolean emailValidator(EditText etMail) {
        String emailToText = etMail.getText().toString();
        if(Patterns.EMAIL_ADDRESS.matcher(emailToText).matches()) {
            return true;
        }else {
            etEmail.setError("Email tidak valid!");
            return false;
        }
    }
}