package com.android.example.jokepresentationlib;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {

    private TextView tvJoke;
    private String joke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);

        Intent intent = getIntent();

        joke = intent.getStringExtra("joke");

        tvJoke = (TextView) findViewById(R.id.tv_joke);

        tvJoke.setText(joke);

    }
}
