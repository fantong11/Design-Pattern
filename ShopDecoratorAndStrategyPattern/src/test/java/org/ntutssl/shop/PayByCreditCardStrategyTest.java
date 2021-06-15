package org.ntutssl.shop;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PayByCreditCardStrategyTest {
    private PayByCreditCardStrategy payByCreditCardStrategy;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;

    @Before
    public void init() {
        payByCreditCardStrategy = new PayByCreditCardStrategy();
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
        final String cardNumber = "1234567812345678\n";
        final String expiredDate = "10/22\n";
        final String cvv = "123\n";
        final String input = cardNumber + expiredDate + cvv;

        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);
        payByCreditCardStrategy.onEvent(new StringEvent(EventType.CALCULATE, "100.5"));

        assertTrue(outContent.toString().contains("Pay successfully!"));
    }

    @Test
    public void cardNumberInValidTest() {
        final String cardNumber = "12345678123456789\n";
        final String expiredDate = "10/22\n";
        final String cvv = "123\n";
        final String input = cardNumber + expiredDate + cvv;

        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);
        payByCreditCardStrategy.onEvent(new StringEvent(EventType.CALCULATE, "100.5"));

        assertTrue(outContent.toString().contains("Pay failed."));
    }

    @Test
    public void expiredDateInValidTest() {
        final String cardNumber = "1234567812345678\n";
        final String expiredDate = "13/22\n";
        final String cvv = "123\n";
        final String input = cardNumber + expiredDate + cvv;

        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);
        payByCreditCardStrategy.onEvent(new StringEvent(EventType.CALCULATE, "100.5"));

        assertTrue(outContent.toString().contains("Pay failed."));
    }

    @Test
    public void cvvDateInValidTest() {
        final String cardNumber = "1234567812345678\n";
        final String expiredDate = "13/22\n";
        final String cvv = "1234\n";
        final String input = cardNumber + expiredDate + cvv;

        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);
        payByCreditCardStrategy.onEvent(new StringEvent(EventType.CALCULATE, "100.5"));

        assertTrue(outContent.toString().contains("Pay failed."));
    }

    @Test 
    public void calPriceTest() {
        new ShoppingCart();
        final String cardNumber = "1234567812345678\n";
        final String expiredDate = "10/22\n";
        final String cvv = "123\n";
        final String input = cardNumber + expiredDate + cvv;

        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);
        payByCreditCardStrategy.onEvent(new StringEvent(EventType.CALCULATE, "100.5"));

        assertTrue(outContent.toString().contains("Total Price: $90.45"));
    }
}
