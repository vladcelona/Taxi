package com.mirea.taxi;// ThirdActivity.java
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    private EditText streetFromEditText, houseFromEditText, flatFromEditText;
    private EditText streetToEditText, houseToEditText, flatToEditText;
    private Button okButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        // Инициализация EditTexts
        streetFromEditText = findViewById(R.id.streetFromEditText);
        houseFromEditText = findViewById(R.id.houseFromEditText);
        flatFromEditText = findViewById(R.id.flatFromEditText);
        streetToEditText = findViewById(R.id.streetToEditText);
        houseToEditText = findViewById(R.id.houseToEditText);
        flatToEditText = findViewById(R.id.flatToEditText);

        okButton = findViewById(R.id.okButton);

        okButton.setOnClickListener(view -> {
            if (areAllFieldsFilled()) {
                // Все поля заполнены, можно возвращать результат
                Intent returnIntent = new Intent();
                String routeDetails = collectRouteDetails();
                returnIntent.putExtra("routeDetails", routeDetails);
                setResult(RESULT_OK, returnIntent);
                finish();
            } else {
                // Не все поля заполнены, показываем сообщение пользователю
                Toast.makeText(ThirdActivity.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean areAllFieldsFilled() {
        // Проверяем, заполнены ли все поля
        return !streetFromEditText.getText().toString().trim().isEmpty() &&
                !houseFromEditText.getText().toString().trim().isEmpty() &&
                !flatFromEditText.getText().toString().trim().isEmpty() &&
                !streetToEditText.getText().toString().trim().isEmpty() &&
                !houseToEditText.getText().toString().trim().isEmpty() &&
                !flatToEditText.getText().toString().trim().isEmpty();
    }

    private String collectRouteDetails() {
        // Собираем детали маршрута в одну строку (или другой формат, если требуется)
        return "From: " + streetFromEditText.getText().toString().trim() + ", " +
                houseFromEditText.getText().toString().trim() + ", " +
                flatFromEditText.getText().toString().trim() + " To: " +
                streetToEditText.getText().toString().trim() + ", " +
                houseToEditText.getText().toString().trim() + ", " +
                flatToEditText.getText().toString().trim();
    }
}
