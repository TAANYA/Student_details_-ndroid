package com.example.tanya.studentproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    Button badd, bupdate, bdelete, bview;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        badd = (Button)findViewById(R.id.button);
        bupdate = (Button)findViewById(R.id.button2);
        bdelete = (Button)findViewById(R.id.button3);
        bview = (Button)findViewById(R.id.button4);

        badd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i1 = new Intent(MainActivity.this,AddActivity.class);
                startActivity(i1);

            }
        });

        bupdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i2 = new Intent(MainActivity.this,UpdateActivity.class);
                startActivity(i2);

            }
        });

        bdelete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i3 = new Intent(MainActivity.this,DeleteActivity.class);
                startActivity(i3);

            }
        });

        bview.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i4 = new Intent(MainActivity.this,ViewActivity.class);
                startActivity(i4);

            }
        });

    }
}
