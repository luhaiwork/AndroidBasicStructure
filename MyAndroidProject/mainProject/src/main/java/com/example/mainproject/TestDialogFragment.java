package com.example.mainproject;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.mainproject.dialogfragment.MyDialogFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

/**
 * Created by Administrator on 2014/4/25.
 */
@EFragment(R.layout.fragment_test_dialog)
public class TestDialogFragment extends Fragment {
    @AfterViews
    void afterViews() {
        MyDialogFragment myDialogFragment = new MyDialogFragment();
        FragmentManager fm = getActivity().getSupportFragmentManager();
        myDialogFragment.show(fm, "myDialog");
    }
}
