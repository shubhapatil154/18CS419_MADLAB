package com.example.intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {


    EditText e1,e2;
    Button bt;
    Spinner s;


    String [] dept_array={"CSE","ECE","IT","Mech","Civil"};
    String name,reg,dept;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        e1= findViewById(R.id.editText);
        e2= findViewById(R.id.editText2);
        bt=  findViewById(R.id.button);
        s=  findViewById(R.id.spinner);

        ArrayAdapter adapter= new ArrayAdapter(MainActivity.this,android.R.layout.simple_spinner_item,dept_array);
        s.setAdapter(adapter);


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name=e1.getText().toString();
                reg=e2.getText().toString();
                dept=s.getSelectedItem().toString();

                Intent i = new Intent(MainActivity.this,SecondActivity.class);
                i.putExtra("name_key", name);
                i.putExtra("reg_key",reg);
                i.putExtra("dept_key", dept);
                startActivity(i);

            }

        });

    }

}
