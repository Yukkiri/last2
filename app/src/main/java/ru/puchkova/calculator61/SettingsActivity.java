package ru.puchkova.calculator61;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SettingsActivity extends AppCompatActivity {
    private ImageButton cancel;
    private Button ok;
    private EditText path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        init();
    }

    public void init(){
        path = findViewById(R.id.path);
        ok = findViewById(R.id.ok);
        cancel = findViewById(R.id.cancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imagePath = path.getText().toString();
                if (!imagePath.equals("") && checkExist(imagePath)) {
                    Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                    intent.putExtra("background", imagePath);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Изображение не существует :(", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean checkExist(String imagePath){

        Path path = Paths.get(imagePath);

        return Files.exists(path);
    }
}
