package com.exercise.area.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_areas")

public class UserArea {
	@EmbeddedId
	private UserAreaId id;

	@ManyToOne
	@MapsId("userId")
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@MapsId("areaId")
	@JoinColumn(name = "area_id")
	private Area area;

	public UserAreaId getId() {
		return id;
	}

	public void setId(UserAreaId id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public UserArea(UserAreaId id, User user, Area area) {
		super();
		this.id = id;
		this.user = user;
		this.area = area;
	}

}