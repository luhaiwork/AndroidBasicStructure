package com.example.mainproject;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Administrator on 2014/5/4.
 */
@EFragment(R.layout.fragment_test_volley)
public class TestVolleyFragment extends Fragment {
    @ViewById
    TextView tv_result;

    @AfterViews
    void afterViews() {

    }

    @Click
    void btn_testvolley(View view) {
        RequestQueue rq = Volley.newRequestQueue(getActivity());
        StringRequest postReq = new StringRequest(Request.Method.GET, "http://www.baidu.com", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                tv_result.setText(s);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        rq.add(postReq);
    }
}
