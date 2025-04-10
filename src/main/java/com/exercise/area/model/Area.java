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
	private String name;

	@Column(length = 500)
	private String description;

	@OneToMany(mappedBy = "area")
	private List<UserArea> userAreas = new ArrayList<>();

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<UserArea> getUserAreas() {
		return userAreas;
	}

	public void setUserAreas(List<UserArea> userAreas) {
		this.userAreas = userAreas;
	}

	public Area(Integer areaId, String name, String description, List<UserArea> userAreas) {
		super();
		this.areaId = areaId;
		this.name = name;
		this.description = description;
		this.userAreas = userAreas;
	}

}