package com.example.mainproject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.example.mainproject.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

@EActivity(R.layout.activity_http_test)
public class HttpTestActivity extends Activity {
	private String TAG = HttpTestActivity.class.getSimpleName();
	@ViewById
	TextView tv_result;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@AfterViews
	public void afterviewload() {
		Log.e(TAG, "after view load ");
	}

	@Click(R.id.btn_get)
	void testGet() {
		AsyncHttpClient client = new AsyncHttpClient();
		client.get("http://www.baidu.com", new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					byte[] responseBody) {
				String str = new String(responseBody);
				tv_result.setText(str);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
					tv_result.setText("error");
			}
		});
	}

	/**
	 * test Entity
	 * 
	 * 
	 * @author luh </br>
	 * @time 2014年2月27日 上午9:43:10 </br>
	 * 
	 */
	@Click(R.id.btn_entity)
	void testEntity() {
		AsyncHttpClient client = new AsyncHttpClient();
		StringEntity entity = null;
		try {
			entity = new StringEntity("{aa:sss}");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		client.post(this, "http://192.168.1.67/test_content.php", entity, null,
				new TextHttpResponseHandler() {
					@Override
					public void onSuccess(int statusCode, Header[] headers,
							String responseString) {
						tv_result.setText(responseString);
					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							String responseString, Throwable throwable) {
						tv_result.setText("error");
					}
				});
	}

	@Click(R.id.btn_post)
	void testPost() {
		String url = "http://192.168.1.67/test_post.php";
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("username", "myusername");
		client.post(this, url, params, new TextHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
//				Log.e(TAG, "test post result :" + responseString);
				tv_result.setText(responseString);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				tv_result.setText("error");
			}
		});
	}

	@Click(R.id.btn_file)
	void testUploadFile() {
		String url = "http://192.168.1.67/test_uploadfile.php";
		String filename = "myfile";
		String string = "Hello world!";
		FileOutputStream outputStream;
		try {
			outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
			outputStream.write(string.getBytes());
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		AsyncHttpClient client = new AsyncHttpClient();
		FileInputStream openFileInput = null;
		try {
			openFileInput = openFileInput(filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		RequestParams params = new RequestParams();
		params.put("file", openFileInput, "myfile_test");
		client.post(url, params, new TextHttpResponseHandler() {
			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				tv_result.setText("error");
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
//				Log.e(TAG, "responseString:" + responseString);
				tv_result.setText(responseString);
			}
		});
	}
}
