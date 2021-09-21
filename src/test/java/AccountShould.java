import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AccountShould {

    @Mock
    AccountStatementPrinter statementPrinter;

    @Test
    void print_bank_statement() {
        Account target = new Account(statementPrinter);

        target.printStatement();

        verify(statementPrinter).printFormattedStatement();
    }
}
