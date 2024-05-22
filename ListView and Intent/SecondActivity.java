package com.mad.intent;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import  androidx.appcompat.app.AppCompatActivity;


class CustomAdapter extends BaseAdapter {

    String[] headings;
    String[] subheadings;
    Context context;
    LayoutInflater inflater;

    public CustomAdapter(Context context, String[] headings, String[] subheadings){
        this.headings = headings;
        this.subheadings = subheadings;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public Object getItem(int position) {
        return headings[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.activity_listview, null);
        TextView heading = view.findViewById(R.id.headingTv);
        TextView subheading = view.findViewById(R.id.subheadingTv);
        heading.setText(headings[position]);
        subheading.setText(subheadings[position]);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, headings[position]+" is pressed! ", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}

public class SecondActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedBundleInstance){
        super.onCreate(savedBundleInstance);
        setContentView(R.layout.activity_second);

        String[] headings = {"Heading 1", "Heading 2", "Heading 3", "Heading 4", "Heading 5"};
        String[] subheadings = {"Sub Heading 1", "Sub Heading 2", "Sub Heading 3", "Sub Heading 4", "Sub Heading 5"};

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(new CustomAdapter(getApplicationContext(), headings, subheadings));
    }
}
