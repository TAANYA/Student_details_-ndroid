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
import android.widget.TextView;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity
{
    Spinner spinner;
    TextView t1, t2, t3;
    Button button;
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        spinner = (Spinner)findViewById(R.id.spinner);
        button = (Button)findViewById(R.id.button6);
        t1 = (TextView)findViewById(R.id.textView2);
        t2 = (TextView)findViewById(R.id.textView3);
        t3 = (TextView)findViewById(R.id.textView4);

        final SQLiteDatabase db = openOrCreateDatabase("db",MODE_PRIVATE,null);

        final ArrayList<Integer> ids = new ArrayList<Integer>();
        String cols[] = {"id"};

        Cursor c = db.query("stu_details",cols,null,null,null,null,null);

        while (c.moveToNext())
        {
            ids.add(c.getInt(0));
        }

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,R.layout.support_simple_spinner_dropdown_item, ids);
        spinner.setAdapter(adapter);

      spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
          {
              index = position;
              String selection = "id="+ ids.get(index);
              Cursor c1 = db.query("stu_details",null,selection,null,null,null,null);

              while (c1.moveToNext())
              {
                  t1.setText(c1.getString(1));
                  t2.setText(String.valueOf(c1.getFloat(2)));
                  t3.setText(String.valueOf(c1.getFloat(3)));
              }

          }

          @Override
          public void onNothingSelected(AdapterView<?> parent)
          {

          }
      });

    }
}
