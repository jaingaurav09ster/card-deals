package com.portal.deals.model;

import java.util.List;

public class SearchDealResponse {

	private int count;
	private List<Deal> deals;

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the deals
	 */
	public List<Deal> getDeals() {
		return deals;
	}

	/**
	 * @param deals
	 *            the deals to set
	 */
	public void setDeals(List<Deal> deals) {
		this.deals = deals;
	}

}