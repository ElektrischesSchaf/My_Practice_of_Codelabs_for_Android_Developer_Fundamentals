package com.example.batterylevelindicator;

import android.graphics.drawable.LevelListDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView thebattery;
    public int level_yee=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        thebattery = (ImageView) findViewById(R.id.imageView_the_battery);
         thebattery.setImageLevel(level_yee);
    }

    public void add(View view) {
        if(level_yee<6) {
            level_yee++;
            thebattery.setImageLevel(level_yee);
            Log.d("click add", "click add " + level_yee);
        }
    }

    public void minus(View view) {
            if(level_yee>0)
            {
                level_yee--;
                thebattery.setImageLevel(level_yee);
                Log.d("click minus", "click minus " + level_yee);
            }

    }
}
