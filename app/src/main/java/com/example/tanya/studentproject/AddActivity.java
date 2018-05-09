package com.example.tanya.studentproject;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity
{
    EditText e1, e2, e3, e4;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        e1 = (EditText)findViewById(R.id.editText);
        e2 = (EditText)findViewById(R.id.editText2);
        e3 = (EditText)findViewById(R.id.editText3);
        e4 = (EditText)findViewById(R.id.editText4);
        button = (Button)findViewById(R.id.button5);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SQLiteDatabase db = openOrCreateDatabase("db",MODE_PRIVATE,null);

                int id = Integer.parseInt(e1.getText().toString());
                String name = e2.getText().toString();
                float marks = Float.parseFloat(e3.getText().toString());
                float attd = Float.parseFloat(e4.getText().toString());

                Toast.makeText(getApplicationContext(),"database is created", Toast.LENGTH_SHORT).show();

                String create = "create table if not exists stu_details(id int,name varchar(20),marks float ,attendance float) ";
                db.execSQL(create);

                Toast.makeText(getApplicationContext(), "student_details table created", Toast.LENGTH_LONG).show();

                ContentValues values = new ContentValues();

                values.put("id",id);
                values.put("name",name);
                values.put("marks",marks);
                values.put("Attendance",attd);

                int i = (int)db.insert("stu_details",null,values);

                if(i>0)
                {
                    Toast.makeText(getApplicationContext(), "data is successfully inserted", Toast.LENGTH_LONG).show();
                }


            }});

    }
}
