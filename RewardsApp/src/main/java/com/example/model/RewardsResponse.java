package com.example.model;

import java.util.List;

public class RewardsResponse {

	private String customerName;
	private List<MonthlyRewardPoints> rewardsPerMonth;
	private int totalRewardsEarned;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public List<MonthlyRewardPoints> getRewardsPerMonth() {
		return rewardsPerMonth;
	}

	public void setRewardsPerMonth(List<MonthlyRewardPoints> rewardsPerMonth) {
		this.rewardsPerMonth = rewardsPerMonth;
	}

	public int getTotalRewardsEarned() {
		return totalRewardsEarned;
	}

	public void setTotalRewardsEarned(int totalRewardsEarned) {
		this.totalRewardsEarned = totalRewardsEarned;
	}

}
