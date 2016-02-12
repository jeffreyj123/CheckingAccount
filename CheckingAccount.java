/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Represent a checking account that stores Transactions.
 * 
 * @author jeffrey_jacinto
 */
package checkingaccount;

import java.util.ArrayList;

public class CheckingAccount
{
 private double balance;
 private double totalServiceCharge;
 private ArrayList<Transaction> transactions;
 private int transCount;
 private boolean firstCharge = true;
 
 /**
  * Constructs a new CheckingAccount object.
  * 
  * @param initialBalance initial balance
  */
 public CheckingAccount(double initialBalance)
 {
    balance = initialBalance;
    totalServiceCharge = 0;
    transactions = new ArrayList<Transaction>();
    transCount = 0;
 }
 
 /**
  * Returns checking account balance.
  * 
  * @return checking account balance
  */
 public double getBalance()
 {
    return balance;
 }
 
 /**
  * Returns checking account balance after service charges are applied.
  * 
  * @return final balance
  */
 public double getFinalBalance()
 {
     return balance - totalServiceCharge;
 }
 
 /**
  * Sets the balance by adding or reducing it by a transaction amount depending
  * on transaction code indicating a check withdrawal or deposit.
  * 
  * @param transAmt transaction amount
  * @param tCode    transaction code
  * @see            Transaction
  */
  public void setBalance(double transAmt, int tCode)
 {
    if (tCode == 1)
        balance = balance - transAmt;
    else
        balance = balance + transAmt;
 }
  
 /**
  * Returns accumulated service charges.
  * 
  * @return service charges
  */
 public double getServiceCharge()
 {
    return totalServiceCharge;
 }
 
 /**
  * Adds to accumulated service charges.
  * 
  * @param currentServiceCharge service charge to be added
  */
 public void setServiceCharge(double currentServiceCharge)
 {
    totalServiceCharge = totalServiceCharge + currentServiceCharge;
 }
 
 /**
  * Returns whether or not the checking account has been charged for the balance
  * dropping under 500.
  * 
  * @return the boolean value
  */
 public boolean getFirstCharge()
 {
     return firstCharge;
 }
 
 /**
  * Sets the boolean representing whether the checking account has been charged
  * for the balance dropping below 500 to the new value.
  * 
  * @param newValue the new value to be set
  */
 public void setFirstCharge(boolean newValue)
 {
     firstCharge = newValue;
 }
 
 /**
  * Return the number of transactions stored within the checking account.
  * 
  * @return number of transactions
  */
 public int getTransCount()
 {
     return transCount;
 }
 
 /**
  * Store new Transaction in checking account and change balance and service
  * charges accordingly.
  * 
  * @param newTrans transaction to be added
  * @see            Transaction
  */
 public void addTrans(Transaction newTrans)
 {
     switch (newTrans.getTransId()) {
         case 1:
             setBalance(newTrans.getTransAmount(), 1);
             break;
         case 2:
             setBalance(newTrans.getTransAmount(), 2);
             break;
         default:
             setServiceCharge(newTrans.getTransAmount());
     }

     transactions.add(newTrans);
     transCount++;
 }
 
 /**
  * Returns the Transaction stored at index i.
  * 
  * @param i index of transaction to be returned
  * @return  transaction
  * @see     Transaction
  */
 public Transaction getTrans(int i)
 {
     return transactions.get(i);
 }
}