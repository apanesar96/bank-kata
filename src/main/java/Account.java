import com.codurance.AccountService;

public class Account implements AccountService {

    private final AccountStatementPrinter statementPrinter;

    public Account(AccountStatementPrinter statementPrinter) {

        this.statementPrinter = statementPrinter;
    }

    @Override
    public void deposit(int amount) {

    }

    @Override
    public void withdraw(int amount) {

    }

    @Override
    public void printStatement() {
        statementPrinter.printFormattedStatement();
    }
}
