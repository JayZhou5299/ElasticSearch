package com.sf.heros.mq.consumer.vo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.sf.heros.mq.consumer.utils.APP;

//@Document(indexName = APP.ESProp.INDEX_NAME, type = APP.ESProp.TYPE_NEWS_INFO, indexStoreType = APP.ESProp.INDEX_STORE_TYPE, shards = APP.ESProp.SHARDS, replicas = APP.ESProp.REPLICAS, refreshInterval = APP.ESProp.REFRESH_INTERVAL)
@Document(indexName = APP.ESProp.INDEX_NAME, type = APP.ESProp.TYPE_NEWS_INFO)
public class NewsInfo {
    @Id
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String newsId;

    @Field(type = FieldType.Integer, index = FieldIndex.not_analyzed, store = true)
    private Integer userId;

    @Field(type = FieldType.String, indexAnalyzer="ik", searchAnalyzer="ik", store = true)
    private String newsContent;

    @Field(type = FieldType.String, indexAnalyzer="ik", searchAnalyzer="ik", store = true)
    private String newsArea;

    @Field(type = FieldType.String, indexAnalyzer="ik", searchAnalyzer="ik", store = true)
    private String newsTags = "";
    
    @Field(type = FieldType.Integer, index = FieldIndex.not_analyzed, store = true)
    private Integer newsState;

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String updateTime;

    @Field(type = FieldType.String, indexAnalyzer="ik", searchAnalyzer="ik", store = true)
    private String userNickName;

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getNewsArea() {
        return newsArea;
    }

    public void setNewsArea(String newsArea) {
        this.newsArea = newsArea;
    }
    
    public String getNewsTags() {
		return newsTags;
	}

	public void setNewsTags(String newsTags) {
		this.newsTags = newsTags;
	}

	public Integer getNewsState() {
        return newsState;
    }

    public void setNewsState(Integer newsState) {
        this.newsState = newsState;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "NewsInfo [newsId=" + newsId + ", userId=" + userId
                + ", newsContent=" + newsContent + ", newsArea=" + newsArea
                + ", newsState=" + newsState
                + ", updateTime=" + updateTime + ", userNickName="
                + userNickName + "]";
    }
}
