package com.codurance;

import java.util.List;

public class Account implements AccountService {

    private final AccountTransactionHandler transactionHandler;
    private final AccountStatementPrinter statementPrinter;

    public Account(AccountTransactionHandler transactionHandler, AccountStatementPrinter statementPrinter) {
        this.transactionHandler = transactionHandler;
        this.statementPrinter = statementPrinter;
    }

    @Override
    public void deposit(int amount) {
        transactionHandler.deposit(amount);
    }

    @Override
    public void withdraw(int amount) {
        transactionHandler.withdraw(amount);
    }

    @Override
    public void printStatement() {
        List<Transaction> recordedTransactions = transactionHandler.getRecordedTransactions();
        statementPrinter.printFormattedStatement(recordedTransactions);
    }
}
