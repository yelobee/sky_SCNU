package com.example.administrator.huashixingkong.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.huashixingkong.R;
import com.example.administrator.huashixingkong.activity.DiscussPageActivity;
import com.example.administrator.huashixingkong.myview.RefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;

public class DiscussViewFragment extends Fragment {

    private ListView listView;
    RefreshLayout myRefreshListview;

    static DiscussViewFragment newInstance(String s){
        DiscussViewFragment discussViewFragment = new DiscussViewFragment();

        return discussViewFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discuss_view,container,false);

        listView = (ListView) view.findViewById(R.id.fragment_discuss_list);
        myRefreshListview = (RefreshLayout) view.findViewById(R.id.swipe_layout);

        DiscussViewAdapter myAdapter = new DiscussViewAdapter(getActivity());

        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), DiscussPageActivity.class);
                startActivity(intent);
            }
        });

        myRefreshListview.setColorScheme(android.R.color.black, android.R.color.white,
                android.R.color.holo_blue_bright, android.R.color.holo_red_dark);

        myRefreshListview.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getActivity(), "refresh", Toast.LENGTH_SHORT).show();
                myRefreshListview.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        myRefreshListview.setRefreshing(false);
                    }
                }, 1000);
            }
        });

        myRefreshListview.setOnLoadListener(new RefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                Toast.makeText(getActivity(), "load", Toast.LENGTH_SHORT).show();

                myRefreshListview.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        myRefreshListview.setLoading(false);
                    }
                }, 1500);

            }

        });

        return view;
    }

    private ArrayList<HashMap<String,Object>> getDate(){
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
        for (int i=0;i<4;i++){
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemText", "这是第"+i+"行");
            listItem.add(map);
        }
        return listItem;
    }
    private class DiscussViewAdapter extends BaseAdapter {
        private LayoutInflater mInflater;//得到一个LayoutInfalter对象用来导入布局 /*构造函数*/
        public DiscussViewAdapter(Context context) {
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
                convertView = mInflater.inflate(R.layout.fragment_discuss_item_view,
                        null);
                holder = new ViewHolder();
                    /*得到各个控件的对象*/
                holder.image = (ImageView) convertView.findViewById(R.id.fragment_discuss_image);
                holder.title = (TextView) convertView.findViewById(R.id.fragment_discuss_title);
                holder.message = (TextView) convertView.findViewById(R.id.fragment_discuss_message);
                holder.date = (TextView) convertView.findViewById(R.id.fragment_discuss_date);
                convertView.setTag(holder);//绑定ViewHolder对象
            }else{
                holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象
            }
            /*设置TextView显示的内容，即我们存放在动态数组中的数据*/
            holder.image.setImageResource(R.drawable.abc);
            holder.title.setText(getDate().get(position).get("ItemText").toString());
            return convertView;
        }
    }
    public final class ViewHolder{
        public ImageView image;
        public TextView title;
        public TextView message;
        public TextView date;
    }
}
