package com.example.contextmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity
{
    ToggleButton tb;
    EditText startingVal, jump;
    Button results;
    double start, jumper;
    double[] arr = new double[20];
    String getStartingVal, getJump;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tb = findViewById(R.id.toggleButton);
        startingVal = findViewById(R.id.et1);
        jump = findViewById(R.id.et2);
        results = findViewById(R.id.button);
    }

    public void goToResults(View view)
    {
        getStartingVal = startingVal.getText().toString();
        getJump = jump.getText().toString();
        if(getStartingVal.equals(""))
        {
            Toast.makeText(this, "no starting value entered please enter and try again", Toast.LENGTH_SHORT).show();
        }
        else if(getJump.equals(""))
        {
            Toast.makeText(this, "no adder/multiplier entered please enter and try again", Toast.LENGTH_SHORT).show();
        }
        else
        {
            start = Double.parseDouble(getStartingVal);
            jumper = Double.parseDouble(getJump);
            if(jumper == 0)
            {
                Toast.makeText(this, "you cant have 0 as adder or multiplier please enter a new one", Toast.LENGTH_SHORT).show();
            }
            else if(tb.isChecked() && start == 0)
            {
                Toast.makeText(this, "you can't have a starting value 0 on geometrical series", Toast.LENGTH_SHORT).show();
            }
            else
            {
                arr[0] = start;
                if (!tb.isChecked())
                {
                    for (int i = 1; i < 20; i++)
                    {
                        arr[i] = start + (jumper * i);
                    }
                }
                else
                {
                    for (int i = 1; i < 20; i++)
                    {
                        arr[i] = start * (Math.pow(jumper, i));
                    }
                }
                Intent si = new Intent(this, results.class);
                si.putExtra("startVal", start);
                si.putExtra("jump", jumper);
                si.putExtra("valArr", arr);
                startActivity(si);
            }
        }
    }
}