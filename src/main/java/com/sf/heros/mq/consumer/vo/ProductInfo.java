package com.sf.heros.mq.consumer.vo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.sf.heros.mq.consumer.utils.APP;

@Document(indexName = APP.ESProp.DAIDONGXI_INDEX_NAME, type = APP.ESProp.TYPE_PRODUCT_INFO)
public class ProductInfo {

	@Id
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String id;

	@Field(type = FieldType.String, indexAnalyzer="ik", searchAnalyzer="ik", store = true)
    private String title;

	@Field(type = FieldType.String, indexAnalyzer="ik", searchAnalyzer="ik", store = true)
    private String categoryName;

	@Field(type = FieldType.String, indexAnalyzer="ik", searchAnalyzer="ik", store = true)
    private String location;

	@Field(type = FieldType.String, indexAnalyzer="ik", searchAnalyzer="ik", store = true)
    private String content;

	@Field(type = FieldType.String, indexAnalyzer="ik", searchAnalyzer="ik", store = true)
    private String createTime;

	@Field(type = FieldType.Integer, store = true)
    private Integer popularCount;

	public ProductInfo() {
	}

	public ProductInfo(String id, String title, String categoryName,
			String location, String content, String createTime,
			Integer popularCount) {
		super();
		this.id = id;
		this.title = title;
		this.categoryName = categoryName;
		this.location = location;
		this.content = content;
		this.createTime = createTime;
		this.popularCount = popularCount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getPopularCount() {
		return popularCount;
	}

	public void setPopularCount(Integer popularCount) {
		this.popularCount = popularCount;
	}

	@Override
	public String toString() {
		return "ProductInfo [id=" + id + ", title=" + title + ", categoryName="
				+ categoryName + ", location=" + location + ", content="
				+ content + ", createTime=" + createTime + ", popularCount="
				+ popularCount + "]";
	}

}