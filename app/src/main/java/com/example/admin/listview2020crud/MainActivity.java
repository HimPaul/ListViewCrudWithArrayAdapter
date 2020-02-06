package com.example.admin.listview2020crud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etname;
    Button add,edit,search;
    ListView listView;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    int var;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etname = (EditText) findViewById(R.id.EtName);
        add = (Button) findViewById(R.id.BtAdd);
        edit = (Button) findViewById(R.id.BtEdit);
        search = (Button) findViewById(R.id.BtSearch);
        listView = (ListView) findViewById(R.id.listview);
        arrayList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(this,R.layout.listitem,arrayList);
        listView.setAdapter(arrayAdapter);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = etname.getText().toString();
                if(item.equals("")){
                    Toast.makeText(MainActivity.this,"Please Enter City Name",Toast.LENGTH_LONG).show();
                    return;
                }
                arrayList.add(item);
                arrayAdapter.notifyDataSetChanged();
                etname.setText("");
                etname.requestFocus();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,arrayList.get(position),Toast.LENGTH_LONG).show();
                etname.setText(arrayList.get(position));
                var = position;

            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item1 = etname.getText().toString();
                arrayList.set(var,item1);
                etname.setText("");
                etname.requestFocus();
                arrayAdapter.notifyDataSetChanged();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchcity = etname.getText().toString();
                if(searchcity.equals("")){
                    Toast.makeText(MainActivity.this,"Enter City Name",Toast.LENGTH_LONG).show();
                    return;
                }
                int index = arrayList.indexOf(searchcity);
                Toast.makeText(MainActivity.this,"Position of city "+index+" ",Toast.LENGTH_LONG).show();
                etname.setText("");
                etname.requestFocus();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                arrayList.remove(position);
                etname.setText("");
                arrayAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}
