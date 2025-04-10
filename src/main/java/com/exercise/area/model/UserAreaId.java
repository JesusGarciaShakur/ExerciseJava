package com.exercise.area.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserAreaId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String userId;
	private Integer areaId;

	public UserAreaId() {

	}

	public UserAreaId(String userId, Integer areaId) {
		super();
		this.userId = userId;
		this.areaId = areaId;
	}
	
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

}