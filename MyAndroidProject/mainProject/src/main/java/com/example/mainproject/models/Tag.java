/**
 * 
 */
package com.example.mainproject.models;

import java.util.Date;

import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.annotations.AutoIncrementPrimaryKey;
import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.PrimaryKey;
import se.emilsjolander.sprinkles.annotations.Table;

/**
 * </br>
 * 
 * @author luh </br>
 * @time 2014年3月5日 上午9:59:36 </br>
 * 
 */
@Table("Tag")
public class Tag extends Model{
	/**
	 * 
	 */
	public Tag() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
	public Tag(String tagName) {
		this.tagName=tagName;
	}
	@PrimaryKey
	@AutoIncrementPrimaryKey
	@Column("tag_id")
	private long tagId;
	@Column("tag_name")
	private String tagName;
	private Date createDate;
	private Date updateDate;
	@Override
	protected void beforeCreate() {
		super.beforeCreate();
		createDate=new Date();
	}
	@Override
	protected void beforeSave() {
		super.beforeSave();
		updateDate=new Date();
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	/**
	 * @return the tagId
	 */
	public long getTagId() {
		return tagId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	
}
