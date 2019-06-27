/*
 * Copyright (C) 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.example.roomwordssample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.example.roomwordssample.R;

import static com.android.example.roomwordssample.MainActivity.EXTRA_DATA_UPDATE_WORD;
// Answer
import static com.android.example.roomwordssample.MainActivity.EXTRA_DATA_UPDATE_ANSWER;
// ID
import static com.android.example.roomwordssample.MainActivity.EXTRA_DATA_ID;

/**
 * This class displays a screen where the user enters a new word.
 * The NewWordActivity returns the entered word to the calling activity
 * (MainActivity) which then stores the new word and updates the list of
 * displayed words.
 */
public class NewWordActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.roomwordsample.REPLY";

    // Answer
    public static final String EXTRA_ANSWER="com.example.android.roomwordsample.ANSWER";
    // ID
    public static final String EXTRA_REPLY_ID = "com.android.example.roomwordssample.REPLY_ID";


    // Question
    private EditText mEditWordView;
    //Answer
    private EditText mEditAnswerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);

        mEditWordView = findViewById(R.id.edit_word);
        mEditAnswerView=findViewById(R.id.edit_answer);

        final Bundle extras = getIntent().getExtras();

        // If we are passed content, fill it in for the user to edit.
        if (extras != null) {
            String word = extras.getString(EXTRA_DATA_UPDATE_WORD, "");

            // Answers
            String answer=extras.getString(EXTRA_DATA_UPDATE_ANSWER,"");

            // ID
            int test_id=extras.getInt(EXTRA_DATA_ID);

            if (!word.isEmpty()) {
                // Questions
                mEditWordView.setText(word);
                mEditWordView.setSelection(word.length());
                mEditWordView.requestFocus();

                // Answers
                mEditAnswerView.setText(answer);
                mEditAnswerView.setSelection(answer.length());
                mEditAnswerView.requestFocus();
            }
        } // Otherwise, start with empty fields.

        final Button button = findViewById(R.id.button_save);

        // When the user presses the Save button, create a new Intent for the reply.
        // The reply Intent will be sent back to the calling activity (in this case, MainActivity)
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Create a new Intent for the reply.
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditWordView.getText())) {
                    // No word was entered, set the result accordingly.
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    // Get the new word that the user entered.
                    String word = mEditWordView.getText().toString();
                    // Answer
                    String answer=mEditAnswerView.getText().toString();
                    // Put the new word in the extras for the reply Intent.
                    replyIntent.putExtra(EXTRA_REPLY, word);
                    Log.d("the_question:   ", word);
                    Log.d("EXTRA_REPLY",EXTRA_REPLY);
                    // Answer
                    replyIntent.putExtra(EXTRA_ANSWER,answer);
                    Log.d("the_answer:     ",answer);
                    Log.d("EXTRA_ANSWER  ",EXTRA_ANSWER);

                    // id
                    if (extras != null && extras.containsKey(EXTRA_DATA_ID)) {
                        int id = extras.getInt(EXTRA_DATA_ID, -1);
                        Log.d("the_id_1:   ",EXTRA_DATA_ID);
                        if (id != -1) {
                            replyIntent.putExtra(EXTRA_REPLY_ID, id);
                            Log.d("the_id_2:   ",EXTRA_DATA_ID);
                        }
                    }

                    // Set the result status to indicate success.
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
