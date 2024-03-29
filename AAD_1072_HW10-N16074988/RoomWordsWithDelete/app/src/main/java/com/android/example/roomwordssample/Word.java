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

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Entity class that represents a word in the database
 */

@Entity(tableName = "word_table")
public class Word {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    @ColumnInfo(name = "word")
    public String mWord;

    @ColumnInfo(name="answer")
    public String mAnswer;

    // Old construtor with one string para.
    // public  Word(@NonNull String word){this.mWord=word;}

    // Nes constructor with two string para.
    public Word(@NonNull String word,  String answer){this.mWord=word; this.mAnswer=answer;}

    @Ignore
    public Word(int id, @NonNull String word, String answer) {
        this.id = id;
        this.mWord = word;
        this.mAnswer=answer;
    }

    public String getWord(){return this.mWord;}
    public String getAnswer(){return  this.mAnswer;}

    public int getId() {return id;}
    public void setId(int id) {
        this.id = id;
    }

    /*
    @ColumnInfo(name="answer")
    private String mAnswer;
    public String getAnswer(){return  this.mAnswer;}
    */
}
