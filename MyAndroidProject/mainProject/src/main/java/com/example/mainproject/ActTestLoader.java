package com.example.mainproject;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mainproject.loader.MyDataLoader;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_act_test_loader)
public class ActTestLoader extends FragmentActivity implements LoaderManager.LoaderCallbacks<List<String>> {
    /**
     * 用于模拟真实数据，loader 从此变量中取数据，然后改动数据源，触发loader 重新获取数据
     */
    public static List<String> dataSouce;
    @ViewById
    ListView lv_list;
    private ArrayAdapter adapter;
    private Loader<List<String>> listLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //excute after android annotation init finished.
    @AfterViews
    void afterView() {
        dataSouce = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            dataSouce.add("test" + i);
        }
        adapter = new ArrayAdapter<String>(this, R.layout.testloader_list_line, R.id.tv_note, new ArrayList<String>());
        listLoader = getSupportLoaderManager().initLoader(0, null, this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.act_test_loader, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<List<String>> onCreateLoader(int id, Bundle args) {
        return new MyDataLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<String>> loader, List<String> data) {
        Log.e("test", "==========onLoadFinished");
        adapter.clear();
        adapter.addAll(data);
        lv_list.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader<List<String>> loader) {
        Log.e("test", "==========on loader reset");
        adapter.clear();
    }

    @Click(R.id.btn_add_data)
    public void addData() {
        dataSouce.add("new Data " + System.currentTimeMillis());
        listLoader.onContentChanged();
    }

}

