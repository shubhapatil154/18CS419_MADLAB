package com.example.calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.text.TextUtils;
public class MainActivity extends AppCompatActivity implements OnClickListener {
    TextView tv,tv1,tv2;
    EditText ed1,ed2;
    Button b1,b2,b3,b4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 =  findViewById(R.id.button6);
        b2 =  findViewById(R.id.button7);
        b3 =  findViewById(R.id.button8);
        b4 =  findViewById(R.id.button9);
        ed1 = findViewById(R.id.editText5);
        ed2 = findViewById(R.id.editText6);
        tv = findViewById(R.id.textView2);
        tv1 = findViewById(R.id.textView3);
        tv2 = findViewById(R.id.textView4);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
    }
    @Override
    public void onClick(View v)
    {
        float num1 = 0;
        float num2 = 0;
        float result = 0;
        if(TextUtils.isEmpty(ed1.getText().toString())||TextUtils.isEmpty(ed2.getText().toString()))
            return;
        num1 = Float.parseFloat(ed1.getText().toString());
        num2 = Float.parseFloat(ed2.getText().toString());
        String operator = " ";
        switch(v.getId())
        {
            case R.id.button6 : operator = "+";
                tv.setText("+");
                result = num1+num2;
                break;
            case R.id.button7 : operator = "-";
                tv.setText("-");
                result = num1-num2;
                break;
            case R.id.button9 : operator = "x";
                tv.setText("x");
                result = num1*num2;
                break;
            case R.id.button8 : operator = "/";
                tv.setText("/");
                result = num1/num2;
                break;
            default : break;
        }
        tv2.setText(result+"");
    }
}
