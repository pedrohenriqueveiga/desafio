package br.com.magalu.desafio.implementation.service;

import br.com.magalu.desafio.api.dto.QuoteInput;
import br.com.magalu.desafio.api.dto.QuoteOutput;
import br.com.magalu.desafio.domain.entity.QuoteEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Pedro Henrique Veiga
 * @created 06/06/24
 * @lastModified 06/06/24
 */


@Component
public class QuoteEntityMapper {

    public static QuoteEntity toEntity(QuoteInput quoteInput){
        return new QuoteEntity(null, quoteInput.getUserId(), quoteInput.getName(), quoteInput.getOrderId(), quoteInput.getProductId(), quoteInput.getProductValue(), quoteInput.getDateBuy());
    }

    public static QuoteInput toDomainObject(QuoteEntity entity){
        return new QuoteInput(entity.getUserId(), entity.getName(), entity.getOrderId(), entity.getProductId(), entity.getProductValue(),entity.getDateBuy());
    }

    public static List<QuoteEntity> toEntityList(List<QuoteInput> list){
        return list.stream().map(QuoteEntityMapper :: toEntity).collect(Collectors.toList());
    }

    public static List<QuoteInput> toDomainObjectList(List<QuoteEntity> list){
        return list.stream().map(QuoteEntityMapper :: toDomainObject).collect(Collectors.toList());
    }

    public static List<QuoteOutput> groupByOrderId(List<QuoteInput> listQuote) {
        Map<Integer, QuoteOutput> groupByOrderId = new HashMap<>();
        for (QuoteInput quote : listQuote) {
            groupByOrderId
                    .computeIfAbsent(quote.getUserId(), k -> new QuoteOutput(quote.getUserId(), quote.getName()))
                    .addQuote(quote);
        }

        return new ArrayList<>(groupByOrderId.values());
    }

}
