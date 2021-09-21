package com.codurance.printing;

import com.codurance.calculator.AccountBalanceCalculator;
import com.codurance.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

import static com.codurance.calculator.AccountBalanceCalculator.calculateRemainingBalance;
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
            int transactionIndex = reversedTransactions.indexOf(transaction);
            int totalBalance = calculateRemainingBalance(transactionIndex, reversedTransactions);
            String line = format("%s || %s   || %d", formattedDate, formattedAmount, totalBalance);
            printer.printLine(line);
        }
    }

    private List<Transaction> getMostRecentTransactions(List<Transaction> transactions) {
        List<Transaction> mostRecentTransactions = new ArrayList<>(transactions);
        reverse(mostRecentTransactions);

        return mostRecentTransactions;
    }

}