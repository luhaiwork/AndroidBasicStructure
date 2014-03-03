/**
 * 
 */
package com.example.mainproject;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.SystemService;
import org.androidannotations.annotations.ViewById;

import android.app.NotificationManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * </br>
 * 
 * @author luh </br>
 * @time 2014年3月1日 上午11:30:12 </br>
 *
 */
@EFragment (R.layout.fragment_myfragmenttop)
public class MyFragment extends Fragment {
	@ViewById
	TextView tv_testfragment;
	@ViewById
	Button btn_test_notice;
	@SystemService
	 NotificationManager notificationManager;
	@AfterViews
	void afterView(){
		Toast.makeText(getActivity(), tv_testfragment.getText().toString(), Toast.LENGTH_SHORT).show();;
	}
	@Click
	void btn_test_notice(){
		NotificationCompat.Builder mBuilder =
		        new NotificationCompat.Builder(getActivity())
		        .setSmallIcon(R.drawable.ic_launcher)
		        .setContentTitle("My notification")
		        .setContentText("Hello World!");
		notificationManager.notify(0, mBuilder.build());
	}
}
