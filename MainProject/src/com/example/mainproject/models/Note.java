/**
 * 
 */
package com.example.mainproject.models;

import java.util.Date;

import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.annotations.AutoIncrementPrimaryKey;
import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.DynamicColumn;
import se.emilsjolander.sprinkles.annotations.Table;
import android.util.Log;

/**
 * </br>
 * 
 * @author luh </br>
 * @time 2014年2月27日 下午5:10:02 </br>
 * 
 */
@Table("Notes")
public class Note extends Model {
	private String TAG =Note.class.getSimpleName();
	// 自增-若查询使用cursor方式作为adpter数据源，id需要使用_id
	@AutoIncrementPrimaryKey
	@Column("_id")
	private long id;
	@Column("content")
	private String content;
	@Column("created_at")
	private Date createdAt;
	@Column("updated_at")
	private Date updatedAt;
	@DynamicColumn("tag_count")
	private int tagCount;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getTagCount() {
		return tagCount;
	}

	public void setTagCount(int tagCount) {
		this.tagCount = tagCount;
	}
	//数据创建时执行
	@Override
	protected void beforeCreate() {
		Log.e(TAG, "before create");
		super.beforeCreate();
		createdAt = new Date();
	}
	//数据更新时执行
	@Override
	protected void beforeSave() {
		Log.e(TAG, "before save");
		super.beforeSave();
		updatedAt = new Date();
	}
}
