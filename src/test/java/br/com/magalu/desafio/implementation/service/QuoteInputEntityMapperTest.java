package br.com.magalu.desafio.implementation.service;

import br.com.magalu.desafio.api.dto.QuoteInput;
import br.com.magalu.desafio.domain.entity.QuoteEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Pedro Henrique Veiga
 * @created 07/06/24
 * @lastModified 07/06/24
 */
class QuoteInputEntityMapperTest {



    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    QuoteEntity quoteEntity1;
    QuoteEntity quoteEntity2;
    QuoteInput quoteInput1;
    QuoteInput quoteInput2;

    @BeforeEach
    void setUp() throws ParseException {
         quoteEntity1 = new QuoteEntity(1,55, "Mauricio Armstrong", 601, 2, 1576.68, sdf.parse("20210702"));
         quoteEntity2 = new QuoteEntity(2,75, "Bobbie Batz", 798, 2,  1578.57, sdf.parse("20211116"));

         quoteInput1 = new QuoteInput(55, "Mauricio Armstrong", 601, 2, 1576.68, sdf.parse("20210702"));
         quoteInput2 = new QuoteInput(75, "Bobbie Batz", 798, 2,  1578.57, sdf.parse("20211116"));
    }
    @Test
    @DisplayName("Should convert Object QuoteInput for Object QuoteEntity")
    void shouldConvertObjectQuoteForObjectQuoteEntity(){
      QuoteEntity converted = QuoteEntityMapper.toEntity(quoteInput1);

        assertNotNull(converted);
        assertNull(converted.getId());
        assertEquals(quoteInput1.getUserId(), converted.getUserId());
        assertEquals(quoteInput1.getName(), converted.getName());
        assertEquals(quoteInput1.getOrderId(), converted.getOrderId());
        assertEquals(quoteInput1.getProductId(), converted.getProductId());
        assertEquals(quoteInput1.getProductValue(), converted.getProductValue());
        assertEquals(quoteInput1.getDateBuy(), converted.getDateBuy());

    }

    @Test
    @DisplayName("Should convert Object QuoteEntity for Object QuoteInput")
    void shouldConvertObjectQuoteEntityForObjectQuote() {
        QuoteInput converted = QuoteEntityMapper.toDomainObject(quoteEntity1);

        assertNotNull(converted);
        assertEquals(quoteEntity1.getUserId(), converted.getUserId());
        assertEquals(quoteEntity1.getName(), converted.getName());
        assertEquals(quoteEntity1.getOrderId(), converted.getOrderId());
        assertEquals(quoteEntity1.getProductId(), converted.getProductId());
        assertEquals(quoteEntity1.getProductValue(), converted.getProductValue());
        assertEquals(quoteEntity1.getDateBuy(), converted.getDateBuy());
    }

    @Test
    @DisplayName("Should convert List of Object QuoteInput for List of Object QuoteEntity")
    void shouldConvertListOfObjectQuoteForListOfObjectQuoteEntity() {
        List<QuoteInput> listQuoteInputs = List.of(quoteInput1, quoteInput2);
        List<QuoteEntity> listConverted = QuoteEntityMapper.toEntityList(listQuoteInputs);


        for (int i = 0; i < listConverted.size(); i++) {
            QuoteInput quoteInput = listQuoteInputs.get(i);
            QuoteEntity entity = listConverted.get(i);
            assertEquals(quoteInput.getUserId(), entity.getUserId());
            assertEquals(quoteInput.getName(), entity.getName());
            assertEquals(quoteInput.getOrderId(), entity.getOrderId());
            assertEquals(quoteInput.getProductId(), entity.getProductId());
            assertEquals(quoteInput.getProductValue(), entity.getProductValue());
            assertEquals(quoteInput.getDateBuy(), entity.getDateBuy());
        }

        assertNotNull(listConverted);
        assertEquals(2, listConverted.size());



    }

    @Test
    @DisplayName("Should convert List of Object QuoteEntity for List of Object QuoteInput")
    void shouldConvertListOfObjectQuoteEntityForListOfObjectQuote() {

        List<QuoteEntity> listQuotesEntity = List.of(quoteEntity1,quoteEntity2);
        List<QuoteInput> listConverted = QuoteEntityMapper.toDomainObjectList(List.of(quoteEntity1,quoteEntity2));

        for (int i = 0; i < listConverted.size(); i++) {
            QuoteEntity entity = listQuotesEntity.get(i);
            QuoteInput quoteInput = listConverted.get(i);
            assertEquals(quoteInput.getUserId(), entity.getUserId());
            assertEquals(quoteInput.getName(), entity.getName());
            assertEquals(quoteInput.getOrderId(), entity.getOrderId());
            assertEquals(quoteInput.getProductId(), entity.getProductId());
            assertEquals(quoteInput.getProductValue(), entity.getProductValue());
            assertEquals(quoteInput.getDateBuy(), entity.getDateBuy());
        }

        assertNotNull(listConverted);
        assertEquals(2, listConverted.size());
    }
}