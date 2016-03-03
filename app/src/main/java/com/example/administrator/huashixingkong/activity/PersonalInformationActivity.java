package com.example.administrator.huashixingkong.activity;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.huashixingkong.R;

import java.util.ArrayList;
import java.util.HashMap;

public class PersonalInformationActivity extends ActionBarActivity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        listView = (ListView) findViewById(R.id.activity_personal_information_list);
        PersonalInformationAdapter personalInformationAdapter = new PersonalInformationAdapter(PersonalInformationActivity.this);
        listView.setAdapter(personalInformationAdapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_personal_information, menu);
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

        if (id == android.R.id.home){
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private ArrayList<HashMap<String,Object>> getDate(){
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
        for (int i=0;i<4;i++){
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemTitle", "这是第"+i+"行");
            map.put("ItemMessage","wo");
            listItem.add(map);
        }
        return listItem;
    }
    private class PersonalInformationAdapter extends BaseAdapter {
        private LayoutInflater mInflater;//得到一个LayoutInfalter对象用来导入布局 /*构造函数*/
        public PersonalInformationAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            return getDate().size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            //观察convertView随ListView滚动情况
            Log.v("MyListViewBase", "getView " + position + " " + convertView);
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.activity_personal_information_item_view,
                        null);
                holder = new ViewHolder();
                    /*得到各个控件的对象*/
                holder.title = (TextView) convertView.findViewById(R.id.personal_information_title);
                holder.message = (TextView) convertView.findViewById(R.id.personal_information_message);
                convertView.setTag(holder);//绑定ViewHolder对象
            }else{
                holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象
            }
            /*设置TextView显示的内容，即我们存放在动态数组中的数据*/
            holder.title.setText(getDate().get(position).get("ItemTitle").toString());
            holder.message.setText(getDate().get(position).get("ItemMessage").toString());
            return convertView;
        }
    }
    public final class ViewHolder{
        public TextView title;
        public TextView message;
    }
}
