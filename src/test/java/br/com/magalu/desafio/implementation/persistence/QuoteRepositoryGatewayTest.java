package br.com.magalu.desafio.implementation.persistence;

import br.com.magalu.desafio.api.dto.QuoteInput;
import br.com.magalu.desafio.domain.entity.QuoteEntity;
import br.com.magalu.desafio.domain.repository.QuoteRepository;
import br.com.magalu.desafio.implementation.service.QuoteEntityMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
@MockitoSettings(strictness = Strictness.LENIENT)
class QuoteRepositoryGatewayTest {

    @Mock
    QuoteRepository  quoteRepository;


    @InjectMocks
    QuoteRepositoryGateway quoteRepositoryGateway;


    @Test
    void saveQuote() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        QuoteInput sendler = new QuoteInput(75, "Bobbie Batz", 798, 2,  1578.57, sdf.parse("20211116"));

        QuoteEntity entity = QuoteEntityMapper.toEntity(sendler);

        when(quoteRepository.saveAll(List.of(entity))).thenReturn(any());

        quoteRepositoryGateway.saveQuote(List.of(sendler));

        verify(quoteRepository,times(1)).saveAll(any());


    }
}