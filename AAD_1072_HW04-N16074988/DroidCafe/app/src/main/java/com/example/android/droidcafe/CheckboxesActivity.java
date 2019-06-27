package com.example.android.droidcafe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;


/**
 * This activity handles five checkboxes and a Show Toast button for displaying selected checkboxes
 */
public class CheckboxesActivity extends AppCompatActivity {
    public  int pressed_buttons;
    public String must_show;
    boolean[] array = new boolean[6];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkboxes);
        pressed_buttons=0;
        CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        CheckBox checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
        CheckBox checkBox4 = (CheckBox) findViewById(R.id.checkBox4);
        CheckBox checkBox5 = (CheckBox) findViewById(R.id.checkBox5);

        checkBox1.setOnCheckedChangeListener(mOnCheckedChangeListener);
        checkBox2.setOnCheckedChangeListener(mOnCheckedChangeListener);
        checkBox3.setOnCheckedChangeListener(mOnCheckedChangeListener);
        checkBox4.setOnCheckedChangeListener(mOnCheckedChangeListener);
        checkBox5.setOnCheckedChangeListener(mOnCheckedChangeListener);

    }

    private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switch (buttonView.getId()){
                    case R.id.checkBox1:
                        if(isChecked){
                            pressed_buttons++;
                            Log.d("CheckboxesActivity","checkbox 1");
                           array[1]=true;
                        }
                        else {
                            pressed_buttons--;
                            array[1]=false;
                        }
                        break;
                    case R.id.checkBox2:
                        if(isChecked){
                            pressed_buttons++;
                            Log.d("CheckboxesActivity","checkbox 2");
                            array[2]=true;
                        }
                        else {
                            pressed_buttons--;
                            array[2]=false;
                        }
                        break;
                    case R.id.checkBox3:
                        if(isChecked){
                            pressed_buttons++;
                            Log.d("CheckboxesActivity","checkbox 3");
                            array[3]=true;
                        }
                        else {
                            pressed_buttons--;
                            array[3]=false;
                        }
                        break;
                    case R.id.checkBox4:
                        if(isChecked){
                            pressed_buttons++;
                            Log.d("CheckboxesActivity","checkbox 4");
                            array[4]=true;
                        }
                        else {
                            pressed_buttons--;
                            array[4]=false;
                        }
                        break;
                    case R.id.checkBox5:
                        if(isChecked){
                            pressed_buttons++;
                            Log.d("CheckboxesActivity","checkbox 5");
                            array[5]=true;
                        }
                        else {
                            pressed_buttons--;
                            array[5]=false;
                        }
                        break;
                }
            }
        };

    public void showtoast(View view) {

        must_show="Toppings: ";

        if(array[1]==true  )
        {
            must_show+=getString(R.string.checkBox1)+" ";
        }
        if(array[2]==true  )
        {
            must_show+=getString(R.string.checkBox2)+" ";
        }
        if(array[3]==true  )
        {
            must_show+=getString(R.string.checkBox3)+" ";
        }
        if(array[4]==true  )
        {
            must_show+=getString(R.string.checkBox4)+" ";
        }
        if(array[5]==true  )
        {
            must_show+=getString(R.string.checkBox5)+" ";
        }

        displayToast(must_show);

        if(pressed_buttons==1){
            Log.d("CheckboxesActivity","One checked");
            //displayToast(getString(R.string.pick_up));

          //  displayToast(must_show);
        }
        if(pressed_buttons>1){
            Log.d("CheckboxesActivity","More than one checked");
            //displayToast(getString(R.string.pick_up));
          //  displayToast(must_show);
        }
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }


}
