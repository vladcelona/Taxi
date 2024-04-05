package com.mirea.taxi;// MainActivity.java
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText phoneEditText, nameEditText, surnameEditText;
    private Button registrationButton;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneEditText = findViewById(R.id.phoneEditText);
        nameEditText = findViewById(R.id.nameEditText);
        surnameEditText = findViewById(R.id.surnameEditText);
        registrationButton = findViewById(R.id.registrationButton);

        sharedPreferences = getSharedPreferences("TaxiApp", MODE_PRIVATE);
        loadUserData();

        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (areFieldsFilled()) {
                    saveUserData();
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("phone", phoneEditText.getText().toString().trim());
                    intent.putExtra("name", nameEditText.getText().toString().trim());
                    intent.putExtra("surname", surnameEditText.getText().toString().trim());
                    startActivity(intent);
                } else {
                    Toast.makeText(
                            MainActivity.this, "All fields must be filled!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveUserData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("phone", phoneEditText.getText().toString().trim());
        editor.putString("name", nameEditText.getText().toString().trim());
        editor.putString("surname", surnameEditText.getText().toString().trim());
        editor.apply();
    }

    private void loadUserData() {
        String phone = sharedPreferences.getString("phone", "");
        String name = sharedPreferences.getString("name", "");
        String surname = sharedPreferences.getString("surname", "");

        phoneEditText.setText(phone);
        nameEditText.setText(name);
        surnameEditText.setText(surname);

        if (!phone.isEmpty() && !name.isEmpty() && !surname.isEmpty()) {
            registrationButton.setText(getString(R.string.sign_in));
        }
    }

    private boolean areFieldsFilled() {
        return !phoneEditText.getText().toString().trim().isEmpty() &&
                !nameEditText.getText().toString().trim().isEmpty() &&
                !surnameEditText.getText().toString().trim().isEmpty();
    }
}
