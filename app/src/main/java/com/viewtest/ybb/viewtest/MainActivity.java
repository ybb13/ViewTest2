package com.viewtest.ybb.viewtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.viewtest.ybb.viewtest.View.PanelView;
import com.viewtest.ybb.viewtest.View.PopupViewActivity;
import com.viewtest.ybb.viewtest.View.TitleBar;

public class MainActivity extends AppCompatActivity {

    private TitleBar mtitleBar;
    private TextView tv1;
    private TextView tv2;
    private ListView lv_one;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_viewpager);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(i);

            }
        });

        lv_one = (ListView) this.findViewById(R.id.lv_one);

        String[] strs1 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>
                (this, android.R.layout.simple_expandable_list_item_1, strs1);
        lv_one.setAdapter(adapter1);
        lv_one.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i;
                switch (position) {
                    case 0:
                        i = new Intent(MainActivity.this, PopupViewActivity.class);
                        startActivity(i);
                        break;
                    case 1:
                        i = new Intent(MainActivity.this, DrawerMenuActivity.class);
                        startActivity(i);
                        break;
                    case 2:
                        i = new Intent(MainActivity.this, SimpleLineChartActivity.class);
                        startActivity(i);
                        break;
                    case 3:
                        i = new Intent(MainActivity.this, PanelViewActivity.class);
                        startActivity(i);
                        break;

                }

            }
        });


//        mtitleBar = (TitleBar)findViewById(R.id.titleBar);
//        mtitleBar.setLimageView(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this,"left",Toast.LENGTH_LONG).show();
//            }
//        });
//
//        mtitleBar.setRimageViewt(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this,"right",Toast.LENGTH_LONG).show();
//            }
//        });
    }
}
