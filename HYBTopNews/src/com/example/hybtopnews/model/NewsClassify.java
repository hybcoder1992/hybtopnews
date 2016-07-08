package com.example.hybtopnews.model;

/**
 * 新闻分类栏目属性
 * */
public class NewsClassify {
	private Integer id;
	private String type;
	private String title;
	private Boolean is_myinterest;//是否感兴趣
	public NewsClassify(){}
	public NewsClassify(Integer id, String type, String title, Boolean is_myinterest) {
		super();
		this.id = id;
		this.type = type;
		this.title = title;
		this.is_myinterest = is_myinterest;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Boolean getIs_myinterest() {
		return is_myinterest;
	}
	public void setIs_myinterest(Boolean is_myinterest) {
		this.is_myinterest = is_myinterest;
	}
	
}
