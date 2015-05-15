package com.hyn.refreshlistview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

/**
 * Created by hyn on 2015/5/15.
 */
public class ListViewActivity extends ActionBarActivity {
    private PullToRefreshListView listView;
    private ArrayList<String> arrayList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_listview);
        listView = (PullToRefreshListView) findViewById(R.id.listView);
        for (int i = 0; i < 10; i++) {
            arrayList.add("第 " + i + " item");
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);
//        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 2; i++) {
                            arrayList.add(0, "刷新" + i);
                            adapter.notifyDataSetChanged();
                            listView.onRefreshComplete();
                        }
                    }
                }, 2000);

            }
        });
        listView.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                for (int i = 0; i < 2; i++) {
                    arrayList.add("last加载" + i);
                    adapter.notifyDataSetChanged();
                    listView.onRefreshComplete();
                }
            }
        });
    }
}
