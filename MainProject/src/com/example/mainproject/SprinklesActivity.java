package com.example.mainproject;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ItemLongClick;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.ViewById;

import se.emilsjolander.sprinkles.ManyQuery;
import se.emilsjolander.sprinkles.Model.OnDeletedCallback;
import se.emilsjolander.sprinkles.Query;
import se.emilsjolander.sprinkles.Transaction;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.mainproject.models.Note;
import com.example.mainproject.models.NoteTagLink;
import com.example.mainproject.models.Tag;

//页面布局
@EActivity(R.layout.activity_sprinkles)
public class SprinklesActivity extends Activity {
	private String TAG=SprinklesActivity.class.getSimpleName();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	@ViewById
	Button btn_add;
	@ViewById
	EditText et_val;
	@ViewById
	ListView lv_data;
	@ViewById
	EditText et_search;
	@ViewById
	EditText et_valforedit;
	@ViewById
	Button btn_edit;
	private SimpleCursorAdapter simpleDataAdp;
	@TextChange(R.id.et_search)
	 void onTextChangesOnEtSearch() {
		freshAdapter();
	 }
	@Click
	void btn_add() {
		Note note = new Note();
		note.setContent(et_val.getText().toString());
		note.save();
		//清空搜索框
		et_search.setText("");
		// fresh adpter data
		freshAdapter();
	}
	@Click
	void btn_edit(){
		String content = et_valforedit.getText().toString();
		int _id=-1;
		try {
			_id=Integer.parseInt(et_valforedit.getTag()+"");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Note note = Query.one(Note.class, "select * from Notes where _id=?", _id).get();
		if(note!=null){
			note.setContent(content);
			boolean save = note.save();
			Toast.makeText(this,("opt :"+save), Toast.LENGTH_SHORT).show();
			freshAdapter();
		}
		
	}
	/**
	 * 刷新数据
	 * 
	 *
	 * @author luh </br>
	 * @time 2014年3月1日 上午9:49:38 </br>
	 *
	 */
	void freshAdapter() {
		simpleDataAdp.getCursor().close();
		ManyQuery<Note> many = Query.many(Note.class, "select * from Notes where content like ?",
				"%"+et_search.getText().toString()+"%");
		Cursor cursor = many.get().getCursor();
		simpleDataAdp.changeCursor(cursor);
		simpleDataAdp.notifyDataSetChanged();
	}

	// 页面加载完成后执行，该代码不能在onCreate 执行，onCreate中android annotation 还未加载布局
	@AfterViews
	void afterExcute() {
		// 加载list数据
		ManyQuery<Note> many = Query.many(Note.class, "select * from Notes",
				null);
		Cursor cursor = many.get().getCursor();
		String[] from = { "content" };
		int[] to = { R.id.tv_note };
		simpleDataAdp = new SimpleCursorAdapter(this, R.layout.list_line,
				cursor, from, to);
		lv_data.setAdapter(simpleDataAdp);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.sprinkles, menu);
		return true;
	}

	@ItemClick
	void lv_dataItemClicked(Cursor curserItem) {
		String content = curserItem.getString(curserItem.getColumnIndex("content"));
		int _id= curserItem.getInt(curserItem.getColumnIndex("_id"));
		Toast.makeText(this,
				content,
				Toast.LENGTH_SHORT).show();
		et_valforedit.setText(content);
		et_valforedit.setTag(_id);
	}

	@ItemLongClick
	void lv_dataItemLongClicked(Cursor curserItem) {
		int id = curserItem.getInt(curserItem.getColumnIndex("_id"));
		// 参数可以为object
		final Note note = Query.one(Note.class,
				"select * from Notes where _id = ?", id).get();
		note.deleteAsync(new OnDeletedCallback() {
			@Override
			public void onDeleted() {
				Toast.makeText(SprinklesActivity.this,
						"delete success-对象是否存在：" + note.exists(),
						Toast.LENGTH_SHORT).show();
				if(!note.exists()){
					freshAdapter();
				}
			}
		});
	}
	@Click
	void test_transaction(){
		Tag tag=null;
		Note note=null;
		Transaction t = new Transaction();
		boolean result = true;
		try{
		tag = new Tag("tag1");
		result=result&&tag.save(t);
		note =new Note();
		note.setContent("note content");
		result=result&&note.save(t);
		NoteTagLink link = new NoteTagLink();
		link.setNoteId(tag.getTagId());
		link.setTagId(note.getId());
		result=result&&link.save(t);
		t.setSuccessful(result);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			t.finish();
		}
		Toast.makeText(this, "result:"+result, Toast.LENGTH_SHORT).show();
		try{
			note.delete();
		}catch(Exception e){
			e.printStackTrace();
			Log.e("aa",e.getMessage());
		}
	}
}
