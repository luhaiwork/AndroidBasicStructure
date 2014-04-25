package com.example.mainproject;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.example.mainproject.dialogfragment.MyDialogFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

/**
 * Created by Administrator on 2014/4/25.
 */
@EFragment(R.layout.fragment_test_dialog)
public class TestDialogFragment extends Fragment {
    private FragmentManager fm;
    @AfterViews
    void afterViews() {
        fm = getActivity().getSupportFragmentManager();
    }

    @Click
    void btn_dialog_commonTest(View view) {
        MyDialogFragment myDialogFragment = new MyDialogFragment(MyDialogFragment.Type.COMM_DIAG);
        myDialogFragment.show(fm, "myDialog");
    }

    @Click
    void btn_dialog_itemTest(View view) {
        MyDialogFragment myDialogFragment = new MyDialogFragment(MyDialogFragment.Type.ITEM_DIAG);
        myDialogFragment.show(fm, "myDialog");
    }

    @Click
    void btn_multicheck_dialog(View view) {
        MyDialogFragment myDialogFragment = new MyDialogFragment(MyDialogFragment.Type.ITEM_MULTICHOICE);
        myDialogFragment.show(fm, "myDialog");
    }

    @Click
    void btn_cus_dialog(View view) {
        MyDialogFragment myDialogFragment = new MyDialogFragment(MyDialogFragment.Type.ITEM_CUS);
        myDialogFragment.show(fm, "myDialog");
    }
}
