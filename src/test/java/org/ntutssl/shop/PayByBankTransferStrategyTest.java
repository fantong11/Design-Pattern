package org.ntutssl.shop;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PayByBankTransferStrategyTest {
    private PayByBankTransferStrategy payByBankTransferStrategy;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;

    @Before
    public void init() {
        payByBankTransferStrategy = new PayByBankTransferStrategy();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void reset() {
        EventManager.getInstance().reset();
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    public void inputValidTest() {
        final String bankCode = "123\n";
        final String accountNumber = "123456123456\n";
        final String input = bankCode + accountNumber;

        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);
        payByBankTransferStrategy.onEvent(new StringEvent(EventType.CALCULATE, "100.5"));

        assertTrue(outContent.toString().contains("Pay successfully!"));
    }

    @Test
    public void bankCodeInValidTest() {
        final String bankCode = "12345\n";
        final String accountNumber = "123456123456\n";
        final String input = bankCode + accountNumber;

        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);
        payByBankTransferStrategy.onEvent(new StringEvent(EventType.CALCULATE, "100.5"));

        assertTrue(outContent.toString().contains("Pay failed."));
    }

    @Test
    public void accountNumberInValidTest() {
        final String bankCode = "123\n";
        final String accountNumber = "12345612345678\n";
        final String input = bankCode + accountNumber;

        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);
        payByBankTransferStrategy.onEvent(new StringEvent(EventType.CALCULATE, "100.5"));

        assertTrue(outContent.toString().contains("Pay failed."));
    }

    @Test 
    public void calPriceTest() {
        new ShoppingCart();
        final String bankCode = "123\n";
        final String accountNumber = "123456123456\n";
        final String input = bankCode + accountNumber;

        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);
        payByBankTransferStrategy.onEvent(new StringEvent(EventType.CALCULATE, "100.5"));

        assertTrue(outContent.toString().contains("Total Price: $100.99"));
    }
}
