package com.codurance.calculator;

import com.codurance.transaction.Transaction;

import java.util.List;

public class AccountBalanceCalculator {

    public static int calculateRemainingBalance(int startingTransaction, List<Transaction> transactions) {
        int totalTransactions = transactions.size();
        List<Transaction> remainingTransactions = transactions.subList(startingTransaction, totalTransactions);
        int remainingBalance = 0;
        for (Transaction remainingTransaction : remainingTransactions) {
            remainingBalance = remainingTransaction.getTransactionalAmount(remainingBalance);
        }

        return remainingBalance;
    }

}
