package com.psy.dto;

import java.util.Date;

public class BoardVO {
	private int id;
	private String title;
	private Date date;
	private String content;
	private int viewcnt;
	private int commcnt;
	
	public BoardVO( ) {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getViewcnt() {
		return viewcnt;
	}

	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}
	
	public int getCommcnt() {
		return commcnt;
	}

	public void setCommcnt(int commcnt) {
		this.commcnt = commcnt;
	}
	
}
