/**
 * 
 */
package com.example.mainproject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mainproject.models.Province;
import com.example.mainproject.parser.ParserByPULL;

/**
 * </br>
 * 
 * @author luh </br>
 * @time 2014年3月12日 下午4:13:21 </br>
 * 
 */
@EFragment(R.layout.fragment_location_select)
public class LocationSelectFragment extends Fragment {
	@ViewById
	Spinner sp_province;
	@ViewById
	Spinner sp_city;
	@ViewById
	Spinner sp_district;
	private List<Province> provinces;
	@AfterViews
	void afterView() {
		AssetManager asset = getActivity().getAssets();
		InputStream input=null;
		try {
			input = asset.open("basic_loaction_info.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			provinces = ParserByPULL.getProvince(input);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Province province : provinces) {
			Log.e("test","province-name:"+province.getProvinceName()+"-code:"+province.getProvinceCode());
		}
		ArrayAdapter adp = new ArrayAdapter<Province>(getActivity(), resource, textViewResourceId)
		sp_province.setAdapter(pad);
	}
}
