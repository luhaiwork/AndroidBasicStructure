package com.example.mainproject;

import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2014/5/4.
 */
@EFragment(R.layout.fragment_test_volley)
public class TestVolleyFragment extends Fragment {
    @ViewById
    TextView tv_result;
    @ViewById
    ImageView img_test;
    private Fragment fragment;
    private RequestQueue rq;

    @AfterViews
    void afterViews() {
        fragment = this;
        rq = Volley.newRequestQueue(getActivity());
    }

    @Override
    public void onStop() {
        super.onStop();
        rq.cancelAll(this);
    }

    /**
     * add request to the queue
     *
     * @param request
     */
    private void addRequest(Request request) {
        rq.add(request);
    }

    @Click
    void btn_testvolley_get(View view) {
        StringRequest getReq = new StringRequest(Request.Method.GET, "http://www.baidu.com", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                tv_result.setText(s);
            }
        }, errorListener());
        addRequest(getReq);
    }

    /**
     * test post request
     *
     * @param view
     */
    @Click
    void btn_testvolley_post(View view) {
        StringRequest postReq = new StringRequest(Request.Method.POST, "http://www.baidu.com", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                tv_result.setText(s);
            }
        }, errorListener()) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("param1", "test");
                return params;
            }
        };
        addRequest(postReq);
    }

    @Click
    void btn_testvolley_json(View view) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://echo.jsontest.com/key/value/one/two", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                tv_result.setText(jsonObject.toString());
            }
        }, errorListener());
        addRequest(jsonObjectRequest);
    }

    @Click
    void btn_testvolley_img(View view) {
        ImageRequest req = new ImageRequest("http://www.baidu.com/img/bdlogo.gif", new Response.Listener<Bitmap>() {

            @Override
            public void onResponse(Bitmap bitmap) {
                img_test.setImageBitmap(bitmap);
            }
        }, 0, 0, null, errorListener());
        addRequest(req);
    }

    protected Response.ErrorListener errorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };
    }
}
