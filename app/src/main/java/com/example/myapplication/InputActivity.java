package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class InputActivity extends AppCompatActivity {
    Button buttonSave;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        buttonSave = findViewById(R.id.buttonSave);
        editText = findViewById(R.id.editText);
    }

    public void addNote(View view) {

    }
}
