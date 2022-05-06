package hcmute.edu.vn.caodinhsyvy_19110143.foody2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import hcmute.edu.vn.caodinhsyvy_19110143.foody2.database.DBManager;

public class SignUpActivity extends AppCompatActivity {

    TextView txtCreateAccount, txtAlreadyHaveAccount;
    EditText edtName, edtEmail, edtPassword;
    DBManager dbManager;

    private void mapping() {
        dbManager = new DBManager(this);
        txtCreateAccount = findViewById(R.id.signUpActivity_txtCreateAccount);
        edtName = findViewById(R.id.signUpActivity_edtName);
        edtEmail = findViewById(R.id.signUpActivity_edtEmail);
        edtPassword = findViewById(R.id.signUpActivity_edtPassword);
        txtAlreadyHaveAccount = findViewById(R.id.signUpActivity_txtAlreadyHaveAccount);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mapping();

        txtCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (createAccount()) {
                    Toast.makeText(SignUpActivity.this, "Create account successfully. You can login now!", Toast.LENGTH_LONG).show();
                    finish();
                }
                else
                    Toast.makeText(SignUpActivity.this, "Cannot create account. Please check again!", Toast.LENGTH_SHORT).show();
            }
        });

        txtAlreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private Boolean createAccount() {
        String name = edtName.getText().toString();
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        try {
            String query = "INSERT INTO [User] VALUES('"+email+"', '"+password+"', '"+name+"')";
            dbManager.QueryData(query);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}