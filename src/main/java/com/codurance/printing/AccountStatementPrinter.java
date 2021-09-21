package com.codurance.printing;

import com.codurance.transaction.Transaction;

import java.util.List;

import static java.lang.String.format;

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
        int runningBalance = 0;
        for(Transaction transaction : transactions) {
            String formattedDate = transaction.getFormattedDate();
            String formattedAmount = transaction.getFormattedAmount();
            runningBalance = transaction.calculateNewBalance(runningBalance);
            String line = format("%s || %s   || %d", formattedDate, formattedAmount, runningBalance);
            printer.printLine(line);
        }
    }

}
