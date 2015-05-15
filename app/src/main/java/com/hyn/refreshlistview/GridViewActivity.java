package com.hyn.refreshlistview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import java.util.ArrayList;

/**
 * Created by hyn on 2015/5/15.
 */
public class GridViewActivity extends ActionBarActivity {
    private PullToRefreshGridView gridView;
    private ArrayList<String> arrayList = new ArrayList<String>();
    private int LastVisiblePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_gridview);
        gridView = (PullToRefreshGridView) findViewById(R.id.gridView);
        for (int i = 0; i < 20; i++) {
            arrayList.add("第 " + i + " item");
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        gridView.setAdapter(adapter);
        gridView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        gridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 4; i++) {
                            arrayList.add(0, "刷新" + i);
                            adapter.notifyDataSetChanged();
                            gridView.onRefreshComplete();
                        }
                    }
                }, 2000);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 4; i++) {
                            arrayList.add("加载" + i);
                            adapter.notifyDataSetChanged();
                            gridView.onRefreshComplete();
                        }
                    }
                }, 2000);
            }
        });
        gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (absListView.getLastVisiblePosition() == (absListView.getCount() - 1)) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < 4; i++) {
                                arrayList.add("自动加载" + i);
                                adapter.notifyDataSetChanged();
                                gridView.onRefreshComplete();
                            }
                        }
                    }, 2000);
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i2, int i3) {

            }
        });

    }
}
