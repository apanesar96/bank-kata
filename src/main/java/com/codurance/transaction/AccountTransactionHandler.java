package com.codurance.transaction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.codurance.calculator.AccountBalanceCalculator.calculateRemainingBalance;
import static java.lang.String.format;

public class AccountTransactionHandler {

    private final TimestampProvider timestampProvider;

    private final List<Transaction> recordedTransactions = new ArrayList<>();

    public AccountTransactionHandler(TimestampProvider timestampProvider) {
        this.timestampProvider = timestampProvider;
    }

    public void recordDeposit(int amount) {
        LocalDateTime transactionTimestamp = timestampProvider.now();
        DepositTransaction transaction = new DepositTransaction(transactionTimestamp, amount);
        recordedTransactions.add(transaction);
    }

    public void recordWithdrawal(int amount) {
        int remainingBalance = calculateRemainingBalance(0, recordedTransactions);
        if (amount > remainingBalance) throw new InsufficientFundsException(format("Insufficient funds to withdraw from %d", remainingBalance));

        LocalDateTime transactionTimestamp = timestampProvider.now();
        WithdrawalTransaction transaction = new WithdrawalTransaction(transactionTimestamp, amount);
        recordedTransactions.add(transaction);
    }

    public List<Transaction> getRecordedTransactions() {
        return recordedTransactions;
    }
}
