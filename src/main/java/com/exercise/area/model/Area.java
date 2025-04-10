package com.exercise.area.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "areas")

public class Area {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "area_id")
	private Integer areaId;

	@Column(nullable = false, unique = true, length = 100)
	private String areaName;

	@Column(length = 500)
	private String areaDescription;

	@OneToMany(mappedBy = "area")
	private List<UserArea> userAreas = new ArrayList<>();

	public Area() {

	}

	public Area(Integer areaId, String areaName, String areaDescription, List<UserArea> userAreas) {
		super();
		this.areaId = areaId;
		this.areaName = areaName;
		this.areaDescription = areaDescription;
		this.userAreas = userAreas;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaDescription() {
		return areaDescription;
	}

	public void setAreaDescription(String areaDescription) {
		this.areaDescription = areaDescription;
	}

	public List<UserArea> getUserAreas() {
		return userAreas;
	}

	public void setUserAreas(List<UserArea> userAreas) {
		this.userAreas = userAreas;
	}

}