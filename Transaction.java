/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkingaccount;

/**
 * Represents checking account transaction data.
 * 
 * @author jeffrey_jacinto
 */
public class Transaction {
    private int transNumber;
    private int transId;
    private double transAmt;
    
    /**
     * Constructs a new Transaction object.
     * 
     * @param number transaction number of parent CheckingAccount
     * @param id     transaction code
     * @param amount transaction amount
     * @see          CheckingAccount
     */
    public Transaction(int number, int id, double amount)
    {
        transNumber = number;
        transId = id;
        transAmt = amount;
    }
    
    /**
     * Returns transaction number of parent CheckingAccount.
     * 
     * @return transaction number
     * @see    CheckingAccount
     */
    public int getTransNumber()
    {
        return transNumber;
    }
    
    /**
     * Returns transaction code detailing what type of Transaction this object
     * represents. 1 represents a check withdrawal, 2 represents a deposit, and
     * 3 represents a service charge.
     * 
     * @return transaction code
     * @see    CheckingAccount
     */
    public int getTransId()
    {
        return transId;
    }
    
    /**
     * Returns transaction amount.
     * 
     * @return transaction amount
     */
    public double getTransAmount()
    {
        return transAmt;
    }
}
