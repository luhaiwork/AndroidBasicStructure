//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.0.1.
//


package com.example.mainproject;


public final class MyApplication_
    extends MyApplication
{

    private static MyApplication INSTANCE_;

    public static MyApplication getInstance() {
        return INSTANCE_;
    }

    /**
     * Visible for testing purposes
     * 
     */
    public static void setForTesting(MyApplication application) {
        INSTANCE_ = application;
    }

    @Override
    public void onCreate() {
        INSTANCE_ = this;
        init_();
        super.onCreate();
    }

    private void init_() {
    }

}
