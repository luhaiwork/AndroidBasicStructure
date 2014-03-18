package com.example.mainproject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import android.content.res.AssetManager;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.mainproject.models.City;
import com.example.mainproject.models.District;
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
	/** cities */
	private List<City> cities = new ArrayList<City>();
	/** district */
	private List<District> districts = new ArrayList<District>();
	ArrayAdapter provinceAdp;
	ArrayAdapter citiesAdp;
	ArrayAdapter districtAdp;
	private String provinceCode;

	@AfterViews
	void afterView() {
		try {
			provinces = ParserByPULL.getProvince(getLocalDataXMLInputStream());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		provinceAdp = new ArrayAdapter<Province>(getActivity(),
				android.R.layout.simple_spinner_item, provinces);
		sp_province.setAdapter(provinceAdp);
		citiesAdp = new ArrayAdapter<City>(getActivity(),
				android.R.layout.simple_spinner_item, cities);
		sp_city.setAdapter(citiesAdp);
		districtAdp = new ArrayAdapter<District>(getActivity(),
				android.R.layout.simple_spinner_item, districts);
		sp_district.setAdapter(districtAdp);
		sp_province.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Province province = provinces.get(position);
				try {
					List<City> tmpCities = ParserByPULL.getCities(
							getLocalDataXMLInputStream(),
							province.getProvinceCode());
					provinceCode = province.getProvinceCode();
					cities.clear();
					for (int i = 0; i < tmpCities.size(); i++) {
						cities.add(tmpCities.get(i));
					}
					citiesAdp.notifyDataSetChanged();
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		sp_city.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				City city = cities.get(position);
				try {
					List<District> tmpDistricts = ParserByPULL.getDistricts(
							getLocalDataXMLInputStream(), provinceCode,
							city.getCode());
					districts.clear();
					for (int i = 0; i < tmpDistricts.size(); i++) {
						districts.add(tmpDistricts.get(i));
					}
					districtAdp.notifyDataSetChanged();
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

	}

	private InputStream getLocalDataXMLInputStream() {
		AssetManager asset = getActivity().getAssets();
		InputStream input = null;
		try {
			input = asset.open("basic_loaction_info.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return input;
	}
}
