package www.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    TextView welcomeMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        welcomeMessage = findViewById(R.id.welcomeMsg);

        Intent intent = getIntent();
        String name = intent.getStringExtra("msg");
        welcomeMessage.setText("Welcome " + name);
    }
}