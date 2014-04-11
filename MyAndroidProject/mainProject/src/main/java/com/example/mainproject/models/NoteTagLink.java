/**
 * 
 */
package com.example.mainproject.models;

import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.annotations.CascadeDelete;
import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.ForeignKey;
import se.emilsjolander.sprinkles.annotations.PrimaryKey;
import se.emilsjolander.sprinkles.annotations.Table;

/**
 * </br>
 * 
 * @author luh </br>
 * @time 2014年3月5日 上午11:37:32 </br>
 * 
 */
@Table("notetag_link")
public class NoteTagLink extends Model{
	@PrimaryKey
	@ForeignKey("Tag(tag_id)")
	@CascadeDelete
	@Column("tag_id")
	private long tagId;
	@PrimaryKey
	@Column("node_id")
	@ForeignKey("Notes(_id)")
	@CascadeDelete
	private long NoteId;
	public long getTagId() {
		return tagId;
	}
	public void setTagId(long tagId) {
		this.tagId = tagId;
	}
	public long getNoteId() {
		return NoteId;
	}
	public void setNoteId(long noteId) {
		NoteId = noteId;
	}
	
}
