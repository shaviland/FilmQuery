package com.skilldistillery.filmquery.entities;

import java.util.List;

public class Actor {
	private int id;
	private String firstName;
	private String lasstName;
	private List<Film> films;
	
	
	
	public Actor() {
		super();
	}
	
	
	public Actor(int id, String firstName, String lasstName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lasstName = lasstName;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLasstName() {
		return lasstName;
	}
	public void setLasstName(String lasstName) {
		this.lasstName = lasstName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lasstName == null) ? 0 : lasstName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Actor other = (Actor) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lasstName == null) {
			if (other.lasstName != null)
				return false;
		} else if (!lasstName.equals(other.lasstName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return String.format("Actor [id=%s, firstName=%s, lasstName=%s]", id, firstName, lasstName);
	}
	
	
	
	
	

}
