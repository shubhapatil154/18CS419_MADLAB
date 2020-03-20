package com.example.dbms;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity implements OnClickListener {
    EditText Rollno,Name,Marks;
    Button Insert,Delete,Update,View,ViewAll;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Rollno=(EditText)findViewById(R.id.roll);
        Name=(EditText)findViewById(R.id.name);
        Marks=(EditText)findViewById(R.id.marks);
        Insert =(Button)findViewById(R.id.ins);
        Delete=(Button)findViewById(R.id.del);
        Update=(Button)findViewById(R.id.upd);
        View=(Button)findViewById(R.id.vie);
        ViewAll=(Button)findViewById(R.id.but);

        Insert.setOnClickListener(this);
        Delete.setOnClickListener(this);
        Update.setOnClickListener(this);
        View.setOnClickListener(this);
        ViewAll.setOnClickListener(this);

        db=openOrCreateDatabase("StudentDB",Context.MODE_PRIVATE,null);
        db.execSQL("Create Table if not exists student69(rollno VARCHAR,name VARCHAR,marks VARCHAR);");


    }
    public void  onClick(View view){
        if(view==Insert){
            if(Rollno.getText().toString().trim().length()==0|| Name.getText().toString().trim().length()==0|| Marks.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter all values");
                return;
            }
            db.execSQL("INSERT INTO student69 VALUES('"+Rollno.getText()+"','"+Name.getText()+ "','"+Marks.getText()+"');");
            showMessage("Success", "Record added");
            clearText();
        }
        if(view==Delete)
        {
            if(Rollno.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter Rollno");
                return;
            }
            Cursor c=db.rawQuery("SELECT * FROM student69 WHERE rollno='"+Rollno.getText()+"'", null);
            if(c.moveToFirst()) {
                db.execSQL("DELETE FROM student69 WHERE rollno='"+Rollno.getText()+"'");
                showMessage("Success", "Record Deleted");
            }
            else {
                showMessage("Error", "Invalid Rollno");
            }
            clearText();
        }
        if(view==Update){
            if(Rollno.getText().toString().trim().length()==0){
                showMessage("Error","Please enter Rollno");
                return;
            }
            Cursor c=db.rawQuery("Select * from student69 where rollno='"+Rollno.getText()+"'",null);
            if(c.moveToFirst()){
                db.execSQL("UPDATE student69 SET name='"+ Name.getText() + "',marks='"+ Marks.getText() + "' WHERE rollno='"+Rollno.getText()+"'");
                showMessage("Success", "Record Modified");
            }
            else{
                showMessage("Error", "Invalid Rollno");
            }
            clearText();
        }


        if(view==View) {

            if(Rollno.getText().toString().trim().length()==0) {
                showMessage("Error", "Please enter Rollno");
                return;
            }
            Cursor c=db.rawQuery("SELECT * FROM student69 WHERE rollno='"+Rollno.getText()+"'", null);
            if(c.moveToFirst()) {
                Name.setText(c.getString(1));
                Marks.setText(c.getString(2));
            }
            else {
                showMessage("Error", "Invalid Rollno");
                clearText();
            }
        }
        if(view==ViewAll) {
            Cursor c=db.rawQuery("SELECT * FROM student69", null);
            if(c.getCount()==0) {
                showMessage("Error", "No records found");
                return;
            }
            StringBuffer buffer=new StringBuffer();
            while(c.moveToNext()) {
                buffer.append("Rollno: "+c.getString(0)+"\n");
                buffer.append("Name: "+c.getString(1)+"\n");
                buffer.append("Marks: "+c.getString(2)+"\n\n");
            }
            showMessage("Student Details", buffer.toString());
        }
    }
    public void showMessage(String title,String message)
    {
        Builder builder=new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText()
    {
        Rollno.setText("");
        Name.setText("");
        Marks.setText("");
        Rollno.requestFocus();
    }
}
