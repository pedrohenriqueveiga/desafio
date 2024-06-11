package br.com.magalu.desafio.implementation.persistence;


import br.com.magalu.desafio.api.dto.QuoteInput;
import br.com.magalu.desafio.domain.entity.QuoteEntity;
import br.com.magalu.desafio.domain.repository.QuoteRepository;
import br.com.magalu.desafio.implementation.service.QuoteEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteRepositoryGateway {


    @Autowired
    private final QuoteRepository quoteRepository;

    public QuoteRepositoryGateway(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public void saveQuote(List<QuoteInput> list){
      quoteRepository.saveAll(QuoteEntityMapper.toEntityList(list));
    }


}
