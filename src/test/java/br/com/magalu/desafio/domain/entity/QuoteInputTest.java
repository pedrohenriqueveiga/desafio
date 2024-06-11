package br.com.magalu.desafio.domain.entity;

import br.com.magalu.desafio.api.dto.QuoteInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Pedro Henrique Veiga
 * @created 07/06/24
 * @lastModified 07/06/24
 */

@ExtendWith({MockitoExtension.class})
public class QuoteInputTest {


    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
    QuoteInput quoteInput1;
    QuoteInput quoteInput2;
    @BeforeEach
    public void setUp() throws ParseException {
        quoteInput1 = new QuoteInput(55, "Mauricio Armstrong", 601, 2, 1576.68, sdf.parse("20210702"));
        quoteInput2 = new QuoteInput(75, " Bobbie Batz", 798, 2,  1578.57, sdf.parse("20211116"));

    }
    @Test
    public void testQuoteInitialization() {

        assertEquals(55, quoteInput1.getUserId());
        assertEquals("Mauricio Armstrong", quoteInput1.getName());
        assertEquals(601, quoteInput1.getOrderId());
        assertEquals(2, quoteInput1.getProductId());
        assertEquals(1576.68, quoteInput1.getProductValue());
        assertEquals("20210702", sdf.format(quoteInput1.getDateBuy()));
    }

    @Test
    public void testCreateNewQuote() throws ParseException {

        QuoteInput nQuoteInput = new QuoteInput();
        nQuoteInput.setUserId(80);
        nQuoteInput.setName("Tabitha Kuhn");
        nQuoteInput.setOrderId(877);
        nQuoteInput.setProductId(3);
        nQuoteInput.setProductValue(817.13);
        nQuoteInput.setDateBuy(sdf.parse("20210612"));

        assertEquals(80, nQuoteInput.getUserId());
        assertEquals("Tabitha Kuhn", nQuoteInput.getName());
        assertEquals(877, nQuoteInput.getOrderId());
        assertEquals(3, nQuoteInput.getProductId());
        assertEquals(817.13, nQuoteInput.getProductValue());
        assertEquals("20210612", sdf.format(nQuoteInput.getDateBuy()));


    }
    @Test
    public void testUpdateQuote() throws ParseException {

        quoteInput1.setName("Pedro Henrique");
        quoteInput1.setProductId(3);
        quoteInput1.setProductValue(1550.30);

        assertEquals(55, quoteInput1.getUserId());
        assertNotEquals("Mauricio Armstrong", quoteInput1.getName());
        assertEquals(601, quoteInput1.getOrderId());
        assertNotEquals(2, quoteInput1.getProductId());
        assertNotEquals(1576.68, quoteInput1.getProductValue());
        assertEquals("20210702", sdf.format(quoteInput1.getDateBuy()));

    }



    @Test
    public void testQuoteEquals() {
        assertEquals(quoteInput1, quoteInput1);
        assertEquals(quoteInput1.hashCode(), quoteInput1.hashCode());
    }

    @Test
    public void testQuoteNotEquals() {
        assertNotEquals(quoteInput1, quoteInput2);
        assertNotEquals(quoteInput1.hashCode(), quoteInput2.hashCode());
    }

    @Test
    public void testQuoteToString() {
        String expected = "QuoteInput{userId=55, name='Mauricio Armstrong', orderId=601, productId=2, productValue=1576.68, dateBuy=Fri Jul 02 00:00:00 BRT 2021}";
        assertEquals(expected, quoteInput1.toString());
    }
    @Test
    public void testQuoteToStringNotEquals() {
        String expected = "QuoteInput{userId=51, name='Mauricio Armstrong', orderId=600, productId=2, productValue=1576.68, dateBuy=Fri Jul 02 00:00:00 BRT 2021}";
        assertNotEquals(expected, quoteInput1.toString());
    }

}
