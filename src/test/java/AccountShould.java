import com.codurance.Account;
import com.codurance.AccountStatementPrinter;
import com.codurance.AccountTransactionHandler;
import com.codurance.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.List.of;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AccountShould {

    @Mock
    AccountTransactionHandler transactionHandler;

    @Mock
    AccountStatementPrinter statementPrinter;

    private Account target;

    @BeforeEach
    void setUp() {
        target  = new Account(transactionHandler, statementPrinter);
    }

    @Test
    void deposit_amount() {
        target.deposit(1000);

        verify(transactionHandler).deposit(1000);
    }

    @Test
    void withdraw_amount() {
        target.withdraw(1000);

        verify(transactionHandler).withdraw(1000);
    }

    @Test
    void print_bank_statement() {
        List<Transaction> recordedTransactions = of(new Transaction());
        given(transactionHandler.getRecordedTransactions()).willReturn(recordedTransactions);

        target.printStatement();

        verify(statementPrinter).printFormattedStatement(recordedTransactions);
    }

}
