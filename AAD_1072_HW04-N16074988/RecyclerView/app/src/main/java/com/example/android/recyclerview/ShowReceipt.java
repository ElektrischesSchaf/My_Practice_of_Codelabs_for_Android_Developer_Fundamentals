package com.example.android.recyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ShowReceipt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_receipt);
        Intent intent = getIntent();
        String message = intent.getStringExtra(WordListAdapter.EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.showreceipttext);
        textView.setText(message);
        Log.d("yee",message);
    }
}
