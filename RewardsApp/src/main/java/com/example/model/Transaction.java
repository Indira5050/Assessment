package com.example.model;

import java.time.LocalDate;

public class Transaction {

	private int amount;
	private int rewards;
	private LocalDate transactionDate;
	private int customerid;

	public Transaction() {
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getRewards() {
		return rewards;
	}

	public void setRewards(int rewards) {
		this.rewards = rewards;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

}
