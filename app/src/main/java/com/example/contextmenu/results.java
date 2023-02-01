package com.example.contextmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class results extends AppCompatActivity implements View.OnCreateContextMenuListener
{
    int index;
    TextView tv;
    ListView lv;
    double startingVal, jumper;
    double[] arrVal;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        tv = findViewById(R.id.tv1);
        lv = findViewById(R.id.lv);

        //getting values from intent
        Intent gi = getIntent();
        startingVal = gi.getDoubleExtra("startVal", 1);
        jumper = gi.getDoubleExtra("jump", 1);
        arrVal = gi.getDoubleArrayExtra("valArr");

        //converting from double array to string array in order to use the list view
        String[] arr = new String[20];
        for (int i = 0; i < 20; i++) {
            arr[i] = String.format("%.02f", arrVal[i]);
        }
        //creating an adapter for my listview
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arr);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv.setAdapter(adp);
        lv.setOnCreateContextMenuListener(this);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                index = i;
                return false;
            }
        });
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        menu.setHeaderTitle("Item operations");
        menu.add("Show item index");
        menu.add("Sum up to item");
    }

    public boolean onContextItemSelected(MenuItem item)
    {
        String operation = item.getTitle().toString();
        if (operation.equals("Show item index"))
        {
            tv.setText("INDEX:" + (index + 1));
        }
        else if(operation.equals("Sum up to item"))
        {
            double sum = 0;
            for(int i = 0; i <= index; i++)
            {
                sum += arrVal[i];
            }
            String str = String.format("%.02f", sum);
            tv.setText("SUM: " + str);
        }
        return true;
    }
}