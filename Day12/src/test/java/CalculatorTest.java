import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculatorTest {
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void addsNumbers() {
        assertEquals(15.0, calculator.add(10, 5));
    }

    @Test
    void subtractsNumbers() {
        assertEquals(5.0, calculator.subtract(10, 5));
    }

    @Test
    void multipliesNumbers() {
        assertEquals(50.0, calculator.multiply(10, 5));
    }

    @Test
    void dividesNumbers() {
        assertEquals(2.0, calculator.divide(10, 5));
    }

    @Test
    void supportsDecimalResults() {
        assertEquals(2.5, calculator.divide(5, 2));
    }
    @Test
    void rejectsDivisionByZero() {
        assertThrows(IllegalArgumentException.class, () -> calculator.divide(10, 0));
    }
}
