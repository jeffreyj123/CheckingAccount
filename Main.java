/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * A GUI application to create and display checking account transactions using
 * CheckingAccount and Transaction classes to store respective data and the
 * COptionsPanel class to initiate GUI.
 * 
 * @author jeffrey_jacinto
 */
package checkingaccount;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JFrame;
import java.text.DecimalFormat;

public class Main
{
    public static JFrame frame;
    private static DecimalFormat fmt = new DecimalFormat("0.00");
    private static CheckingAccount myAcc;
    
 public static void main (String[] args)
 {
    double initialBalance;
    initialBalance = getInitialBalance();

    myAcc = new CheckingAccount(initialBalance);
    
    frame = new JFrame("Checking Account Options");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    COptionsPanel panel = new COptionsPanel();
    frame.getContentPane().add(panel);
    
    frame.pack();
    frame.setVisible(true);
 }
 
 /**
  * Gets double value of initial balance of the checking account from user, then 
  * returns it. If user input is not a valid value, continues prompting user for
  * valid value.
  * 
  * @return the initial balance
  * @see    CheckingAccount
  */
 public static double getInitialBalance()
 {
    String balanceStr = JOptionPane.showInputDialog("Enter initial balance: ");
    try {
        return Double.parseDouble(balanceStr);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Please enter numerical amount");
        return getInitialBalance();
    }
 }
 
 /**
  * Gets int transaction code from user for a Transaction and returns it. If
  * user input is not a valid value, continues prompting user for a valid value.
  * 
  * @return transaction code
  * @see    Transaction
  */
 public static int getTransCode()
 {
    String numStr = JOptionPane.showInputDialog("Enter transaction code: ");
    try {
        int code = Integer.parseInt(numStr);
        if (code < 0 || code > 2) {
            JOptionPane.showMessageDialog(null, "Please enter 0, 1, or 2");
            return getTransCode();
        }
        return code;
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Please enter 0, 1, or 2");
        return getTransCode();
    }
 }
 
 /**
  * Gets double transaction amount from user for a Transaction and returns it.
  * If user input is not a valid value, continues prompting user for a valid
  * value.
  * 
  * @return the transaction amount
  * @see    Transaction
  */
 public static double getTransAmt()
 {
    String numStr;
    numStr = JOptionPane.showInputDialog("Enter the transaction amount: ");
    try {
        return Double.parseDouble(numStr);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Please enter a numerical amount");
        return getTransAmt();
    }
 }
 
 /**
  * Adds new check Transaction to CheckingAccount with accompanying service
  * charge Transactions. Displays Transaction and CheckingAccount balance
  * and service charge information as well as a warning when the balance goes
  * below $50.00.
  * 
  * @param accnt  the checking account to be modified and displayed
  * @see          Transaction
  * @see          CheckingAccount
  */
 public static void processCheck(CheckingAccount accnt)
 {
    double transAmt = getTransAmt();
    
    // add check transaction
    accnt.addTrans(new Transaction(accnt.getTransCount(), 1, transAmt));
    
    // add check service charge
    accnt.addTrans(new Transaction(accnt.getTransCount(), 3, 0.15));
    
    String message, transStr = formatBalance(transAmt),
           balanceStr = formatBalance(accnt.getBalance());
    message = "Transaction: Check in amount of " + transStr + '\n' +
              "Current Balance: " + balanceStr + '\n' +
              "Service Charge: Check --- charge $0.15\n";
    double balance = accnt.getBalance();
    if (balance < 500) {
        // add service charge if balance < 500 and this is the first time
        if (accnt.getFirstCharge()) {
            accnt.addTrans(new Transaction(accnt.getTransCount(), 3, 5));
            message += "Service Charge: Below $500 --- charge $5.00\n";
            accnt.setFirstCharge(false);
        }
        if (balance < 50) {
            message += "Warning: Balance below $50\n";
            // add service charge for negative balance
            if (balance < 0) {
                accnt.addTrans(new Transaction(accnt.getTransCount(), 3, 10));
                message += "Service Charge: Below $0 --- charge $10.00\n";
            }
        }
    }
    String serviceStr = formatBalance(myAcc.getServiceCharge());
    message += "Total Service Charge: " + serviceStr + '\n';
    
    JOptionPane.showMessageDialog(null, message);
 }
 
 /**
  * Add new deposit Transaction to CheckingAccount account with accompanying
  * service charge Transactions. Displays Transaction and CheckingAccount
  * balance and service charge information.
  * 
  * @param account the checking account to be modified and displayed
  * @see           Transaction
  * @see           CheckingAccount
  */
 public static void processDeposit(CheckingAccount account)
 {
    double transAmt = getTransAmt();
    
    // add deposit transaction
    account.addTrans(new Transaction(account.getTransCount(), 2, transAmt));
    
    // add deposit service charge
    account.addTrans(new Transaction(account.getTransCount(), 3, 0.10));
    
    String message, transStr = formatBalance(transAmt),
           balanceStr = formatBalance(account.getBalance()),
           serviceStr = formatBalance(account.getServiceCharge());
    message = "Transaction: Deposit in amount of " + transStr + '\n' +
              "Current Balance: " + balanceStr + '\n' +
              "Service Charge: Deposit --- charge $0.10\n" +
              "Total Service Charge: " + serviceStr + '\n';
    JOptionPane.showMessageDialog(null, message);
 }
 
 /**
  * Display CheckingAccount account balance, service charge, and final balance
  * information.
  * 
  * @param account the checking account to be displayed
  * @see           CheckingAccount
  */
 public static void showFinal(CheckingAccount account)
 {
    String balanceStr = formatBalance(account.getBalance());
    String serviceStr = formatBalance(account.getServiceCharge()),
           finalBalanceStr = formatBalance(account.getFinalBalance());
    String message = "Transaction: End\n" +
                     "Current Balance: " + balanceStr + '\n' +
                     "Total Service Charge: " + serviceStr + '\n' +
                     "Final Balance: " + finalBalanceStr + '\n';

    JOptionPane.showMessageDialog(null, message);
 }
 
 /**
  * Prompts user for new Transaction, then adds it to global CheckingAccount
  * myAcc.
  * 
  * @see Transaction
  * @see CheckingAccount
  */
 public static void addTrans()
 {
    int transCode = getTransCode();
    
    // process transaction depending on transaction code
    switch (transCode) {
        case 0:
            showFinal(myAcc);
            break;
        case 1:
            processCheck(myAcc);
            break;
        default:
            processDeposit(myAcc);
    }
 }
 
/**
 * Display global CheckingAccount myAcc general Transaction data, check
 * withdrawals Transaction data, or deposits Transaction data.
 * 
 * @param listType the list data to be shown
 * @see            CheckingAccount
 */
 public static void showList(int listType)
 {
    String message;
    int num, transId, width = 15, transCount = myAcc.getTransCount();
     
    // display list category
    switch (listType) {
        case 1:
            message = "Checks Cashed\n\n\nID\t\t\tAmount\n";
            break;
        case 2:
            message = "Deposits Made\n\n\nID\t\t\tAmount\n";
            break;
        default:
            width = 25;
            message = "List All Transactions\n\n\nID\tType\t\t\t\tAmount\n";
    }
     
    for (num = 0; num < transCount; num++) {
        Transaction trans = myAcc.getTrans(num);
        transId = trans.getTransId();

        if (transId == listType || listType == 3) {
            message += num + "\t";
            // display transaction type if listing all transaction
            if (listType == 3) {
                switch (transId) {
                    case 1:
                        message += "Check\t\t";
                        break;
                    case 2:
                        message += "Deposit\t\t";
                        break;
                    default:
                        message += "Svc. Chrg.\t";
                }
            }
            message += "\t\t" + formatBalance(trans.getTransAmount()) + "\n";
        }
    }
    // display message with appropriate tab formatting
    JTextArea text = new JTextArea(message, transCount + 4, width);
    text.setEditable(false);
    text.setTabSize(4);
    text.setBorder(null);
    text.setOpaque(false);
    JOptionPane.showMessageDialog(null, text);
 }
 
 /**
  * Returns formatted String dollar amount from double balance value.
  * 
  * @param  balance the balance to be formatted
  * @return         the formatted dollar amount
  */
 public static String formatBalance(double balance)
 {
    // add dollar sign to balance and make it positive
    String balanceStr = "$" + fmt.format(Math.abs(balance));
    
    // represent negative values by adding parentheses
    if (balance < 0) {
        balanceStr = "(".concat(balanceStr).concat(")");
    }

    return balanceStr;
 }
}