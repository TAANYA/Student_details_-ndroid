package com.example.tanya.studentproject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UpdateActivity extends AppCompatActivity
{
    Spinner spinner;
    Button button;
    EditText e1, e2 ,e3;
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        spinner = (Spinner)findViewById(R.id.spinner2);
        button = (Button)findViewById(R.id.button7);
        e1 = (EditText)findViewById(R.id.editText5);
        e2 = (EditText)findViewById(R.id.editText6);
        e3 = (EditText)findViewById(R.id.editText7);

        final SQLiteDatabase db = openOrCreateDatabase("db",MODE_PRIVATE,null);

        final ArrayList<Integer> ids = new ArrayList<Integer>();

        String cols[] ={"id"};

        Cursor cursor = db.query("stu_details",cols,null,null,null,null,null);

        while (cursor.moveToNext())
        {
            ids.add(cursor.getInt(0));
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item, ids);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                index = position;
                String selection = "id="+ids.get(index);

                Cursor c1 = db.query("stu_details",null,selection,null,null,null,null);
                while (c1.moveToNext())
                {
                    e1.setText(c1.getString(1));
                    e2.setText(String.valueOf(c1.getFloat(2)));
                    e3.setText(String.valueOf(c1.getFloat(3)));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                return;
            }
        });

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ContentValues values = new ContentValues();
                values.put("name",e1.getText().toString());
                values.put("marks",e2.getText().toString());
                values.put("attd",e3.getText().toString());

                String whereclause = "id="+ids.get(index);

                int i = (int)db.update("stu_details",values,whereclause,null);

                if(i>0)
                {
                    Toast.makeText(getApplicationContext(),"data is updated",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
