package br.com.magalu.desafio.api.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class QuoteInputTest {


    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    QuoteInput input1;
    QuoteInput input2;

    @BeforeEach
    void setUp() throws ParseException {
         input1 = new QuoteInput(70,"Pedro Henrique",556,3,100.40,sdf.parse("2021-05-25"));
         input2 = new QuoteInput(71,"Leticia",667,1,500.30,sdf.parse("2021-05-27"));
    }

    @Test
    @DisplayName("Should be Get all Data")
    void ShouldGetAllData() throws ParseException {

        String date = "2021-05-25";
        QuoteInput input = new QuoteInput(70,"Pedro Henrique",556,3,100.40,sdf.parse(date));

        assertEquals(input.getUserId(),70);
        assertEquals(input.getOrderId(),556);
        assertEquals(input.getProductId(),3);
        assertEquals(input.getProductValue(),100.40);
        assertEquals(input.getDateBuy(),sdf.parse(date));
    }

    @Test
    @DisplayName("Should Set all Data of QuoteInput")
    void ShouldSetAllADataOfQuoteInput() throws ParseException {
        String date = "2021-05-25";
        String date2 = "2021-05-27";
        QuoteInput input = new QuoteInput(70,"Pedro Henrique",556,3,100.40,sdf.parse(date));

        input.setName("Letícia Amorim");
        input.setUserId(55);
        input.setOrderId(667);
        input.setDateBuy(sdf.parse(date2));
        input.setProductId(1);
        input.setProductValue(500.00);


        assertNotEquals(input.getUserId(),70);
        assertNotEquals(input.getOrderId(),556);
        assertNotEquals(input.getProductId(),3);
        assertNotEquals(input.getProductValue(),100.40);
        assertNotEquals(input.getDateBuy(),sdf.parse(date));

    }

    @Test
    @DisplayName("Should Compare Equals And HashCode")
    void ShouldCompareEqualsAndHashCode() {
        assertEquals(input1, input1);
        assertEquals(input1.hashCode(), input1.hashCode());
    }

    @Test
    @DisplayName("Should Compare Not Equals And HashCode")
    void ShouldCompareNotEqualsAndHashCode() {
        assertNotEquals(input1, input2);
        assertNotEquals(input1.hashCode(), input2.hashCode());
    }

    @Test
    @DisplayName("Should Compare To String")
    public void ShouldCompareToString() {
        String expected = "QuoteInput{userId=70, name='Pedro Henrique', orderId=556, productId=3, productValue=100.4, dateBuy=Tue May 25 00:00:00 BRT 2021}";
        assertEquals(expected, input1.toString());
    }

    @Test
    @DisplayName("Should Compare To String Not Equals")
    public void ShouldCompareToStringNotEquals() {
        String expected = "QuoteInput{userId=70, name='Pedro Henrique', orderId=556, productId=3, productValue=100.4, dateBuy=Tue May 25 00:00:00 BRT 2021}";
        assertNotEquals(expected, input2.toString());
    }
}