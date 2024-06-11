package br.com.magalu.desafio.domain.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public final class QuoteInputEntityTest {


    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
    QuoteEntity quote1;
    QuoteEntity quote2;
    @BeforeEach
    public void setUp() throws ParseException {
        quote1 = new QuoteEntity(1,55, "Mauricio Armstrong", 601, 2, 1576.68, sdf.parse("20210702"));
        quote2 = new QuoteEntity(2,75, " Bobbie Batz", 798, 2,  1578.57, sdf.parse("20211116"));

    }

    @Test
    @DisplayName("Should compare objects")
    public void shouldCompareObjects() {
        boolean res = quote1.equals(quote2);
        assertFalse(res);
        assertNotEquals(quote1,quote2);

    }


    @Test
    @DisplayName("Should compare the same objects")
    public void shouldCompareTheSameObjects() {
        boolean res = quote1.equals(quote1);
        assertTrue(res);
        assertEquals(quote1,quote1);
    }

    @Test
    @DisplayName("Should date is null")
    public void shouldDateIsNull() {
        QuoteEntity quoteDateNull = new QuoteEntity(1,55, "Mauricio Armstrong", 601, 2, 1576.68,null);
        Date res = quoteDateNull.getDateBuy();
        assertNull(res);
    }


    @Test
    @DisplayName("Should returned id")
    public void shouldReturnId() {
        Integer id = 1;
        Integer quote = quote1.getId();

        assertEquals(id, quote);
    }

    @Test
    @DisplayName("Should returned name")
    public void shouldReturnName() {
        String name = "Mauricio Armstrong";
        String quote = quote1.getName();

        assertEquals(name, quote);
    }

    @Test
    @DisplayName("Should returned orderId")
    public void shouldReturnedOrderId() {
        Integer orderId = 601;
        Integer q = quote1.getOrderId();

        assertEquals(orderId, q);
    }

    @Test
    @DisplayName("Should returned productId")
    public void shouldBeReturnedProductId() {
        Integer productId = 2;
        Integer q = quote1.getProductId();

        assertEquals(productId, q);
    }

    @Test
    @DisplayName("Should returned productValue")
    public void shouldReturnedProductValue() {
        Double value = 1576.68;
        Double qValue = quote1.getProductValue();

        assertEquals(value, qValue);
    }

    @Test
    @DisplayName("Should returned userId")
    public void shouldReturnedUserId() {
        Integer userId = 55;
        Integer qUserId = quote1.getUserId();

        assertEquals(userId, qUserId);
    }

    @Test
    @DisplayName("Should compare hashcode")
    public void shouldCompareHashCode() {
        int actual = quote1.hashCode();

        assertEquals(-163323895, actual);
    }

    @Test
    @DisplayName("Should test set null in All Object")
    public void shouldTestSetDateBuy() {
        quote1.setUserId(null);
        quote1.setName(null);
        quote1.setOrderId(null);
        quote1.setProductId(null);
        quote1.setProductValue(null);
        quote1.setDateBuy(null);

        assertNull(quote1.getUserId());
        assertNull(quote1.getName());
        assertNull(quote1.getOrderId());
        assertNull(quote1.getProductId());
        assertNull(quote1.getProductValue());
        assertNull(quote1.getDateBuy());
    }

    @Test
    @DisplayName("Should compare toString with expected")
    public void shouldCompareToStringWithExpected() {

        String quoteToString = quote1.toString();

        String expected = "QuoteEntity{id=1, userId=55, name='Mauricio Armstrong', orderId=601, productId=2, productValue=1576.68, dateBuy=Fri Jul 02 00:00:00 BRT 2021}";
        assertEquals(expected, quoteToString);
    }


}
