package com.example.tanya.studentproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class DeleteActivity extends AppCompatActivity
{
    Spinner spinner;
    Button button;
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        spinner = (Spinner)findViewById(R.id.spinner3);
        button = (Button)findViewById(R.id.button8);

        final SQLiteDatabase db = openOrCreateDatabase("db",MODE_PRIVATE,null);

        final ArrayList<Integer> ids = new ArrayList<Integer>();
        String cols[] = {"id"};

        Cursor c  = db.query("stu_details", cols, null,null,null,null,null);

        while (c.moveToNext())
        {
            ids.add(c.getInt(0));
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,ids);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                index = position;
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String whereclause = "id="+ids.get(index);
                int i = db.delete("stu_details",whereclause,null);

                if (i>0)
                {
                    Toast.makeText(getApplicationContext(),"row is successfully deleted from the database",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
