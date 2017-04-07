package com.portal.deals.model;

public class Sector {

	public Sector(String name) {
		this.name = name;
	}

	private String name;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	

	@Override
	public boolean equals(Object obj) {
		Sector sector = (Sector) obj;
		return this.name == sector.name;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 89 * hash + (this.name != null ? this.name.hashCode() : 0);
		return hash;
	}

}