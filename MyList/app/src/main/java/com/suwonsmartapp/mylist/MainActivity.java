package com.suwonsmartapp.mylist;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    ListView listView;
    SingerAdapter adapter;

    String[] names = {"소녀시대", "걸스데이", "씨스타", "포미닛"};
    String[] ages = {"20", "22", "21", "25"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView);
        adapter = new SingerAdapter();

        adapter.addItem(new SingerItem(names[0], ages[0]));
        adapter.addItem(new SingerItem(names[1], ages[1]));
        adapter.addItem(new SingerItem(names[2], ages[2]));
        adapter.addItem(new SingerItem(names[3], ages[3]));

        listView.setAdapter(adapter);
    }

    class SingerAdapter extends BaseAdapter {
        ArrayList<SingerItem> items = new ArrayList<SingerItem>();



        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(SingerItem item) {
            items.add(item);
        }



        @Override
        public Object getItem(int position) {

            return items.get(position);
        }

        @Override
        public long getItemId(int position) {

            return position;
        }

        @Override
        public View getView(int position, View convertview, ViewGroup viewGroup ) {

            /*TextView view1 = new TextView(getApplicationContext());
            view1.setText(names[position]);
            view1.setTextSize(50.0f);
            view1.setTextColor(Color.BLACK);
            */

            SingerItemView view1 = null;
            if (convertview == null) {
                view1 = new SingerItemView(getApplicationContext());
            } else {
                view1 = (SingerItemView) convertview;
            }

            SingerItem curItem = items.get(position);

            view1.setName(curItem.getName());
            view1.setAge(curItem.getAge());


            return view1;
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
