package com.example.mainproject;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.mainproject.custom.SlideCutListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014/5/4.
 */
@EFragment(R.layout.fragment_slide_list_view)
public class SlideListViewFragment extends Fragment implements SlideCutListView.RemoveListener {
    @ViewById
    SlideCutListView lv_test;
    List<String> myData;


    @AfterViews
    void afterViews() {
        myData = new ArrayList<String>();
        myData.add("1111");
        myData.add("2222");
        myData.add("3333");
        MyAdapter adp = new MyAdapter(getActivity(), R.layout.fragment_slide_list_item, android.R.id.text1, myData);
        lv_test.setAdapter(adp);
        lv_test.setRemoveListener(this);
    }


    @Override
    public void removeItem(SlideCutListView.RemoveDirection direction, int position) {

    }

    class MyAdapter extends ArrayAdapter<String> {
        public MyAdapter(Context context, int resource, int textViewResourceId, List<String> objects) {
            super(context, resource, textViewResourceId, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = super.getView(position, convertView, parent);
            return view;
        }
    }
}
