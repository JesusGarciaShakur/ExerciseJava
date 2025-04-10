package com.exercise.area.model;


import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserAreaId implements Serializable {
    private String userId;
    private Integer areaId;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public UserAreaId(String userId, Integer areaId) {
		super();
		this.userId = userId;
		this.areaId = areaId;
	}

}