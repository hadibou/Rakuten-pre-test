package com.priceminister.account.implementation;

import com.priceminister.account.*;


public class CustomerAccount implements Account {
	
	// Actual instance account balance
	private Double balance = 0.0;

	@Override
    public void add(Double addedAmount) {
		// Argument must not be null
    	if(addedAmount != null)
    	{
    		if(addedAmount > 0) {
    			// add positive amount to balance
    			balance += addedAmount;
    		}
    	}
    }

	@Override
    public Double getBalance() {
    	// return the actual balance
        return balance;
    }

	@Override
    public Double withdrawAndReportBalance(Double withdrawnAmount, AccountRule rule) 
    		throws IllegalBalanceException {
    	
    	// Arguments must be not null
    	if(withdrawnAmount != null && rule != null) {
    		
    		// Calculate the new supposed balance
    		Double newBalance = balance - withdrawnAmount;
    		
    		// Check if withdraw is allowed
    		if((newBalance <= balance) && rule.withdrawPermitted(newBalance)) {
    			// Withdraw allowed! assign new balance to account balance
    			balance = newBalance;
    		}
    		else {
    			// Withdraw not allowed, rise exception
    			throw new IllegalBalanceException(newBalance);
    		}
    	}
    	
    	// Return the remaining account balance
		return balance;
    }

}
