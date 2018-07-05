package com.sf.heros.mq.consumer.vo;

public class StoryInfo {

	public static final Integer IS_NORMAL = 1;
    public static final Integer IS_DELETED = 0;

	private String id;

    private Integer userId;

    private String productIds;

    private String title;

    private String category;

    private String content;

    private String cover;
    
    private String imageUrls;

	private Integer favoriteNum;

    private Integer collectionNum;

    private String createTime;

    private String updateTime;

    private Integer isDeleted;

    private Integer popularCount;

    
    public StoryInfo(){}
    
    public StoryInfo(String id, 
    				Integer user_id, 
    				String title,
    				String content,
    				String cover){
    	this.id = id;
    	this.userId = user_id;
    	this.title = title;
    	this.content = content;
    	this.cover = cover;
    }
    
    
    
    
    public String getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(String imageUrls) {
		this.imageUrls = imageUrls;
	}

    public Integer getPopularCount() {
		return popularCount;
	}

	public void setPopularCount(Integer popularCount) {
		this.popularCount = popularCount;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getProductIds() {
        return productIds;
    }

    public void setProductIds(String productIds) {
        this.productIds = productIds == null ? null : productIds.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }

    public Integer getFavoriteNum() {
        return favoriteNum;
    }

    public void setFavoriteNum(Integer favoriteNum) {
        this.favoriteNum = favoriteNum;
    }

    public Integer getCollectionNum() {
        return collectionNum;
    }

    public void setCollectionNum(Integer collectionNum) {
        this.collectionNum = collectionNum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}