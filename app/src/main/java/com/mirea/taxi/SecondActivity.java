package com.mirea.taxi;// SecondActivity.java
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private TextView nameTextView, phoneTextView, routeTextView;
    private Button setPathButton, callTaxiButton;
    private static final int REQUEST_CODE_PATH = 1; // Уникальный код запроса для идентификации ответа

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Инициализация View-компонентов
        nameTextView = findViewById(R.id.nameTextView);
        phoneTextView = findViewById(R.id.phoneTextView);
        routeTextView = findViewById(R.id.routeTextView);
        setPathButton = findViewById(R.id.setPathButton);
        callTaxiButton = findViewById(R.id.callTaxiButton);

        // Получение данных из Intent
        Intent intent = getIntent();
        String phone = intent.getStringExtra("phone");
        String name = intent.getStringExtra("name");
        String surname = intent.getStringExtra("surname");

        // Установка полученных данных в TextView
        nameTextView.setText(String.format("%s %s", name, surname));
        phoneTextView.setText(phone);

        // Обработчик нажатия кнопки Set Path
        setPathButton.setOnClickListener(view -> {
            // Явный вызов ThirdActivity для получения маршрута
            Intent pathIntent = new Intent(SecondActivity.this, ThirdActivity.class);
            startActivityForResult(pathIntent, REQUEST_CODE_PATH);
        });

        // Изначально делаем кнопку Call Taxi недоступной
        callTaxiButton.setEnabled(false);

        // Обработчик нажатия кнопки Call Taxi
        callTaxiButton.setOnClickListener(view -> {
            // Показываем Toast-сообщение об успешном вызове такси
            Toast.makeText(
                    SecondActivity.this, "Taxi Called Successfully!",
                    Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_PATH && resultCode == RESULT_OK && data != null) {
            // Предполагаем, что параметры маршрута передаются в виде одной строки
            String routeDetails = data.getStringExtra("routeDetails");

            // Формирование сообщения о маршруте
            String routeMessage = "Your route: " + routeDetails + ". Click 'Call Taxi' when ready.";
            routeTextView.setText(routeMessage);

            callTaxiButton.setEnabled(true); // Делаем кнопку Call Taxi доступной
        }
    }
}
