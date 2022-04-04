package com.lior.eliyao.mypills;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {

                Toast.makeText(MainActivity.this, ">>>"+l/1000+"<<<", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                Intent go = new Intent(MainActivity.this,JunctionActivity.class);
                startActivity(go);
            }
        }.start();

    }
}