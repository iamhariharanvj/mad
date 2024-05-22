package com.mad.intent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText urlEditText = findViewById(R.id.urlEt);
        Button urlButton = findViewById(R.id.urlBtn);
        Button activityButton = findViewById(R.id.pageBtn);

        urlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri url = Uri.parse(urlEditText.getText().toString());
                Intent i = new Intent(Intent.ACTION_VIEW,url);
                startActivity(i);
            }

        });

        activityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SecondActivity.class);
                i.putExtra("message", "Hello");
                startActivity(i);
            }
        });

    }
}