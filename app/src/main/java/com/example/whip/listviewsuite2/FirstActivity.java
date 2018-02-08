package com.example.whip.listviewsuite2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnFilms;
    Button btnDocu;
    Button btnEmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        btnFilms = findViewById(R.id.btnFilms);
        btnDocu = findViewById(R.id.btnDocu);
        btnEmi = findViewById(R.id.btnEmi);

        btnFilms.setOnClickListener(this);
        btnDocu.setOnClickListener(this);
        btnEmi.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent (this,MainActivity.class);

        switch(view.getId()) {
            case R.id.btnFilms:
                intent.putExtra("TYPEEMISSION","Films");
                break;
            case R.id.btnDocu:
                intent.putExtra("TYPEEMISSION","Documentaires");
                break;
            case R.id.btnEmi:
                intent.putExtra("TYPEEMISSION","Emissions");
                break;
        }

        startActivity(intent);

    }
}
