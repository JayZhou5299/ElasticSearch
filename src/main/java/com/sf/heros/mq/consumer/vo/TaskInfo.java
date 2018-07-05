package com.sf.heros.mq.consumer.vo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.sf.heros.mq.consumer.utils.APP;

//@Document(indexName = APP.ESProp.INDEX_NAME, type = APP.ESProp.TYPE_TASK_INFO, indexStoreType = APP.ESProp.INDEX_STORE_TYPE, shards = APP.ESProp.SHARDS, replicas = APP.ESProp.REPLICAS, refreshInterval = APP.ESProp.REFRESH_INTERVAL)
@Document(indexName = APP.ESProp.INDEX_NAME, type = APP.ESProp.TYPE_TASK_INFO)
public class TaskInfo {
    @Id
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String taskId;
    
    @Field(type = FieldType.Integer, index = FieldIndex.not_analyzed, store = true)
    private Integer userId;

    @Field(type = FieldType.String, indexAnalyzer="ik", searchAnalyzer="ik", store = true)
    private String taskContent;

    @Field(type = FieldType.String, indexAnalyzer="ik", searchAnalyzer="ik", store = true)
    private String taskArea;
    
    @Field(type = FieldType.String, indexAnalyzer="ik", searchAnalyzer="ik", store = true)
    private String taskTags;
    
    @Field(type = FieldType.Integer, index = FieldIndex.not_analyzed, store = true)
    private Integer taskState;

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String updateTime;

    @Field(type = FieldType.String, indexAnalyzer="ik", searchAnalyzer="ik", store = true)
    private String userNickName;
    
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public String getTaskArea() {
        return taskArea;
    }

    public void setTaskArea(String taskArea) {
        this.taskArea = taskArea;
    }

    public String getTaskTags() {
		return taskTags;
	}

	public void setTaskTags(String taskTags) {
		this.taskTags = taskTags;
	}

	public Integer getTaskState() {
        return taskState;
    }

    public void setTaskState(Integer taskState) {
        this.taskState = taskState;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    @Override
    public String toString() {
        return "TaskInfo [taskId=" + taskId + ", userId=" + userId
                + ", taskContent=" + taskContent + ", taskArea=" + taskArea
                + ", taskState=" + taskState
                + ", updateTime=" + updateTime + ", userNickName="
                + userNickName + "]";
    }

	public TaskInfo(String taskId, Integer userId, String taskContent,
			String taskArea, String taskTags, Integer taskState,
			String updateTime, String userNickName) {
		this.taskId = taskId;
		this.userId = userId;
		this.taskContent = taskContent;
		this.taskArea = taskArea;
		this.taskTags = taskTags;
		this.taskState = taskState;
		this.updateTime = updateTime;
		this.userNickName = userNickName;
	}
    public TaskInfo() {
		// TODO Auto-generated constructor stub
	}
}
