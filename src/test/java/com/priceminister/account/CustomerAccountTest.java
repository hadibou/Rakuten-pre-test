package com.priceminister.account;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Test;

import com.priceminister.account.implementation.CustomerAccount;
import com.priceminister.account.implementation.CustomerAccountRule;


/**
 * Please create the business code, starting from the unit tests below.
 * Implement the first test, the develop the code that makes it pass.
 * Then focus on the second test, and so on.
 * 
 * We want to see how you "think code", and how you organize and structure a simple application.
 * 
 * When you are done, please zip the whole project (incl. source-code) and send it to recrutement-dev@priceminister.com
 * 
 */
public class CustomerAccountTest {
    
    Account customerAccount;
    AccountRule rule;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        customerAccount = new CustomerAccount();
        rule = new CustomerAccountRule();
    }
    
    /**
     * Tests that an empty account always has a balance of 0.0, not a NULL.
     */
    @Test
    public void testAccountWithoutMoneyHasZeroBalance() {
    	
        assertEquals(new Double(0), customerAccount.getBalance());
    }
    
    /**
     * Adds money to the account and checks that the new balance is as expected.
     */
    @Test
    public void testAddPositiveAmount() {
    	
    	Double amountToAdd;
    	Double actualBalance;
    	Double newBalance;
    	
    	amountToAdd = 23.54;
        actualBalance = customerAccount.getBalance();
        
        customerAccount.add(amountToAdd);
        newBalance = customerAccount.getBalance();
        
        assertEquals(new Double(actualBalance + amountToAdd), new Double(newBalance));
    }
    
    /**
     * Tests that an illegal withdrawal throws the expected exception.
     * Use the logic contained in CustomerAccountRule; feel free to refactor the existing code.
     * @throws IllegalBalanceException 
     */
    @Test
    public void testWithdrawAndReportBalanceIllegalBalance() {
    	
    	Double amountToAdd;
    	Double withdrawnAmount;
    	Double actualBalance;
    	
    	// Test with a positive withdrawal amount
    	amountToAdd = 86.7;
    	customerAccount.add(amountToAdd);
    	actualBalance = customerAccount.getBalance();
        withdrawnAmount = actualBalance + 100.0;
        
        assertTrue(withdrawnAmount > actualBalance);
        
		try {
			customerAccount.withdrawAndReportBalance(withdrawnAmount, rule);
			// Fails if we get here
			fail("Test with positive withdrawal failed!");
		} catch (IllegalBalanceException e) {
			// Assert that exception is of type IllegalBalanceException
			assertThat(e, IsInstanceOf.instanceOf(IllegalBalanceException.class));
		}
		
		// Test with negative withdrawal amount
        actualBalance = customerAccount.getBalance();
        withdrawnAmount = actualBalance - 100.0;
        
        assertTrue(withdrawnAmount < 0);
        
		try {
			customerAccount.withdrawAndReportBalance(withdrawnAmount, rule);
			// Fails if we get here
			fail("Test with negative withdrawal failed!");
		} catch (IllegalBalanceException e) {
			// Assert that exception is of type IllegalBalanceException
			assertThat(e, IsInstanceOf.instanceOf(IllegalBalanceException.class));
		}
    }
    
    /**** Tests with 'expected' attribute to test exceptions (Optional) ****/
    
    /**
    * Tests that a positive withdrawal amount throws the expected exception
    * @throws IllegalBalanceException
    */
    @Test(expected = IllegalBalanceException.class)
    public void testPositiveWithdrawAndReportBalanceIllegalBalance() throws IllegalBalanceException {
    	
    	Double amountToAdd;
    	Double withdrawnAmount;
    	Double actualBalance;
    	
    	amountToAdd = 86.7;
    	customerAccount.add(amountToAdd);
    	actualBalance = customerAccount.getBalance();
        withdrawnAmount = actualBalance + 100.0;
        
        assertTrue(withdrawnAmount > actualBalance);
        
		customerAccount.withdrawAndReportBalance(withdrawnAmount, rule);
    }
    
     /**
     * Tests that a negative withdrawal amount throws the expected exception
     * @throws IllegalBalanceException
     */
    @Test(expected = IllegalBalanceException.class)
    public void testNegativeWithdrawAndReportBalanceIllegalBalance() throws IllegalBalanceException {
    	
    	customerAccount.add(86.7);
        Double actualBalance = customerAccount.getBalance();
        Double withdrawnAmount = actualBalance - 100.0;
        
        assertTrue(withdrawnAmount < 0);
		customerAccount.withdrawAndReportBalance(withdrawnAmount, rule);
    }
    
    /**** End tests with expected attribute ****/
    
    
    // Also implement missing unit tests for the above functionalities.

    /**
     * Adds negative amount to the account and checks that balance is not affected.
     */
    @Test
    public void testAddNegativeAmount() {
    	
    	Double amountToAdd;
    	Double actualBalance;
    	Double newBalance;
    	
        amountToAdd = -53.2;
        actualBalance = customerAccount.getBalance();
        
        customerAccount.add(amountToAdd);
        newBalance = customerAccount.getBalance();
        
        assertEquals(new Double(actualBalance), new Double(newBalance));
    }
    
    /**
     * Tests that a legal withdrawal amount is accepted
     * @throws IllegalBalanceException
     */
    @Test
    public void testWithdrawAndReportBalance() {
    	
    	Double amountToAdd;
    	Double actualBalance;
    	Double withdrawnAmount;
    	
    	amountToAdd = 63.1;
    	customerAccount.add(amountToAdd);
        actualBalance = customerAccount.getBalance();
        withdrawnAmount = actualBalance - 10;
        
        assertTrue(withdrawnAmount >= 0);
        
		try {
			customerAccount.withdrawAndReportBalance(withdrawnAmount, rule);
		} catch (IllegalBalanceException e) {
			fail("test failed!: " + e.toString());
		}
    }
}
