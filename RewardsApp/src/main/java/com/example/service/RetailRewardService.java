package com.example.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import com.example.model.Customer;
import com.example.model.MonthlyRewardPoints;
import com.example.model.RewardsRequest;
import com.example.model.RewardsResponse;
import com.example.model.Transaction;

@Service
public class RetailRewardService {

	public List<RewardsResponse> getRewardPoints(RewardsRequest request) {

		Map<Integer, List<Transaction>> customerTList = transactionMap(request.getTransactions());
		List<RewardsResponse> rewards = new ArrayList<>();

		for (Map.Entry<Integer, List<Transaction>> t : customerTList.entrySet()) {
			RewardsResponse response = new RewardsResponse();
			response.setCustomerName(Customer.getCustomerName(t.getKey()));
			response.setRewardsPerMonth(rewardsPerMonth(t.getValue(), request.getTimePeriod()));
			response.setTotalRewardsEarned(getTotalRewardsEarned(response.getRewardsPerMonth()));
			rewards.add(response);
		}
		return rewards;
	}

	private Map<Integer, List<Transaction>> transactionMap(List<Transaction> tList) {

		Map<Integer, List<Transaction>> keyMap = new TreeMap<>();

		for (Transaction t : tList) {
			List<Transaction> list = keyMap.get(t.getCustomerid());
			if (list != null) {
				list.add(t);
			} else {
				list = new ArrayList<>();
				list.add(t);
				keyMap.put(t.getCustomerid(), list);
			}
		}
		return keyMap;
	}

	private List<MonthlyRewardPoints> rewardsPerMonth(List<Transaction> list, int timePeriod) {

		List<MonthlyRewardPoints> rewards = new ArrayList<>();

		Set<String> months = new HashSet<>();
		int month = LocalDate.now().getMonthValue() - timePeriod;
		list.forEach(t -> {
			if ((t.getTransactionDate().getMonthValue()) > month) {
				months.add(t.getTransactionDate().getMonth().name());
			}
		});
		
		//Calculate rewards per month
		for (String monthName : months) {
			int totalRewardsPerMonth = 0;
			for (Transaction t : list) {
				String tMonth = t.getTransactionDate().getMonth().name();
				if (tMonth.equals(monthName)) {
					totalRewardsPerMonth += calculateRewards(t.getAmount());
				}
			}
			MonthlyRewardPoints monthlyReward = new MonthlyRewardPoints();
			monthlyReward.setMonth(monthName);
			monthlyReward.setRewards(totalRewardsPerMonth);
			rewards.add(monthlyReward);
		}

		return rewards;
	}

	private int getTotalRewardsEarned(List<MonthlyRewardPoints> monthlyRewards) {
		int totalrewards = 0;
		for (MonthlyRewardPoints monthlyReward : monthlyRewards) {
			totalrewards += monthlyReward.getRewards();
		}
		return totalrewards;
	}

	private int calculateRewards(int amount) {
		if (amount >= 50 && amount < 100) {// Dollar spent between 50 and 100
			return amount - 50;
		} else if (amount > 100) {
			return (2 * (amount - 100) + 50);
		}
		return 0;
	}

}
