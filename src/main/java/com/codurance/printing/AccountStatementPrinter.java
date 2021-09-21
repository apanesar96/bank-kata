package com.codurance.printing;

import com.codurance.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static java.util.Collections.reverse;

public class AccountStatementPrinter {

    private final Printer printer;

    public AccountStatementPrinter(Printer printer) {
        this.printer = printer;
    }

    public void printFormattedStatement(List<Transaction> transactions) {
        printHeader();
        printTransactions(transactions);
    }

    private void printHeader() {
        printer.printLine("Date       || Amount || Balance");
    }

    private void printTransactions(List<Transaction> transactions) {
        List<Transaction> reversedTransactions = getMostRecentTransactions(transactions);
        for (Transaction transaction : reversedTransactions) {
            String formattedDate = transaction.getFormattedDate();
            String formattedAmount = transaction.getFormattedAmount();
            int totalBalance = calculateRemainingBalance(transaction, reversedTransactions);
            String line = format("%s || %s   || %d", formattedDate, formattedAmount, totalBalance);
            printer.printLine(line);
        }
    }

    private List<Transaction> getMostRecentTransactions(List<Transaction> transactions) {
        List<Transaction> mostRecentTransactions = new ArrayList<>(transactions);
        reverse(mostRecentTransactions);

        return mostRecentTransactions;
    }

    private int calculateRemainingBalance(Transaction transaction, List<Transaction> transactions) {
        int currentTransaction = transactions.indexOf(transaction);
        int totalTransactions = transactions.size();
        List<Transaction> remainingTransactions = transactions.subList(currentTransaction, totalTransactions);
        int remainingBalance = 0;
        for (Transaction remainingTransaction : remainingTransactions) {
            remainingBalance = remainingTransaction.getTransactionalAmount(remainingBalance);
        }

        return remainingBalance;
    }

}