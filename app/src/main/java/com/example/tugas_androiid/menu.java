package com.example.tugas_androiid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class menu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void cuaca(View view) {
        Intent intent = new Intent(menu.this, com.example.tugas_androiid.cuaca.class);
        startActivity(intent);
    }
}
