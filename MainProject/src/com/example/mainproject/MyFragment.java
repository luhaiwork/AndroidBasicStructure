/**
 * 
 */
package com.example.mainproject;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import android.support.v4.app.Fragment;
import android.widget.TextView;
import android.widget.Toast;
import com.example.mainproject.R;

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
	@AfterViews
	void afterView(){
		Toast.makeText(getActivity(), tv_testfragment.getText().toString(), Toast.LENGTH_SHORT).show();;
	}
}
