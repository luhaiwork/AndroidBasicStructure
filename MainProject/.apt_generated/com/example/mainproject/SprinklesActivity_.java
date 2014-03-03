//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.0.1.
//


package com.example.mainproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.example.mainproject.R.layout;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class SprinklesActivity_
    extends SprinklesActivity
    implements HasViews, OnViewChangedListener
{

    private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        OnViewChangedNotifier previousNotifier = OnViewChangedNotifier.replaceNotifier(onViewChangedNotifier_);
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        OnViewChangedNotifier.replaceNotifier(previousNotifier);
        setContentView(layout.activity_sprinkles);
    }

    private void init_(Bundle savedInstanceState) {
        OnViewChangedNotifier.registerOnViewChangedListener(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    @Override
    public void setContentView(View view, LayoutParams params) {
        super.setContentView(view, params);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    public static SprinklesActivity_.IntentBuilder_ intent(Context context) {
        return new SprinklesActivity_.IntentBuilder_(context);
    }

    public static SprinklesActivity_.IntentBuilder_ intent(Fragment supportFragment) {
        return new SprinklesActivity_.IntentBuilder_(supportFragment);
    }

    @Override
    public void onViewChanged(HasViews hasViews) {
        et_search = ((EditText) hasViews.findViewById(com.example.mainproject.R.id.et_search));
        lv_data = ((ListView) hasViews.findViewById(com.example.mainproject.R.id.lv_data));
        et_valforedit = ((EditText) hasViews.findViewById(com.example.mainproject.R.id.et_valforedit));
        btn_edit = ((Button) hasViews.findViewById(com.example.mainproject.R.id.btn_edit));
        et_val = ((EditText) hasViews.findViewById(com.example.mainproject.R.id.et_val));
        btn_add = ((Button) hasViews.findViewById(com.example.mainproject.R.id.btn_add));
        {
            View view = hasViews.findViewById(com.example.mainproject.R.id.btn_edit);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        SprinklesActivity_.this.btn_edit();
                    }

                }
                );
            }
        }
        {
            View view = hasViews.findViewById(com.example.mainproject.R.id.btn_add);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        SprinklesActivity_.this.btn_add();
                    }

                }
                );
            }
        }
        {
            AdapterView<?> view = ((AdapterView<?> ) hasViews.findViewById(com.example.mainproject.R.id.lv_data));
            if (view!= null) {
                view.setOnItemClickListener(new OnItemClickListener() {


                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        SprinklesActivity_.this.lv_dataItemClicked(((Cursor) parent.getAdapter().getItem(position)));
                    }

                }
                );
            }
        }
        {
            AdapterView<?> view = ((AdapterView<?> ) hasViews.findViewById(com.example.mainproject.R.id.lv_data));
            if (view!= null) {
                view.setOnItemLongClickListener(new OnItemLongClickListener() {


                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        SprinklesActivity_.this.lv_dataItemLongClicked(((Cursor) parent.getAdapter().getItem(position)));
                        return true;
                    }

                }
                );
            }
        }
        {
            final TextView view = ((TextView) hasViews.findViewById(com.example.mainproject.R.id.et_search));
            if (view!= null) {
                view.addTextChangedListener(new TextWatcher() {


                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        SprinklesActivity_.this.onTextChangesOnEtSearch();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }

                }
                );
            }
        }
        afterExcute();
    }

    public static class IntentBuilder_ {

        private Context context_;
        private final Intent intent_;
        private Fragment fragmentSupport_;

        public IntentBuilder_(Context context) {
            context_ = context;
            intent_ = new Intent(context, SprinklesActivity_.class);
        }

        public IntentBuilder_(Fragment fragment) {
            fragmentSupport_ = fragment;
            context_ = fragment.getActivity();
            intent_ = new Intent(context_, SprinklesActivity_.class);
        }

        public Intent get() {
            return intent_;
        }

        public SprinklesActivity_.IntentBuilder_ flags(int flags) {
            intent_.setFlags(flags);
            return this;
        }

        public void start() {
            context_.startActivity(intent_);
        }

        public void startForResult(int requestCode) {
            if (fragmentSupport_!= null) {
                fragmentSupport_.startActivityForResult(intent_, requestCode);
            } else {
                if (context_ instanceof Activity) {
                    ((Activity) context_).startActivityForResult(intent_, requestCode);
                } else {
                    context_.startActivity(intent_);
                }
            }
        }

    }

}
