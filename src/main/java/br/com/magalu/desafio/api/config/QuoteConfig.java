package br.com.magalu.desafio.api.config;

import br.com.magalu.desafio.api.gateways.QuoteGateway;
import br.com.magalu.desafio.api.usecases.CreateQuoteInteractor;
import br.com.magalu.desafio.implementation.persistence.QuoteRepositoryGateway;
import br.com.magalu.desafio.implementation.service.FileService;
import br.com.magalu.desafio.implementation.service.QuoteEntityMapper;
import br.com.magalu.desafio.implementation.service.QuoteServiceGateway;
import br.com.magalu.desafio.domain.repository.QuoteRepository;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Pedro Henrique Veiga
 * @created 06/06/24
 * @lastModified 06/06/24
 */

@Configuration
public class QuoteConfig {

    @Bean
    CreateQuoteInteractor createQuoteCase(QuoteGateway quoteGateway){
        return new CreateQuoteInteractor(quoteGateway);
    }

    @Bean
    QuoteGateway quoteGateway( QuoteEntityMapper quoteEntityMapper, FileService fileService, EntityManager entityManager){
        return new QuoteServiceGateway(quoteEntityMapper,entityManager,fileService);
    }

    @Bean
    QuoteRepositoryGateway QuoteRepositoryGateway (QuoteRepository quoteRepository) {
        return new QuoteRepositoryGateway(quoteRepository);
    }

    @Bean
    FileService createFileService(QuoteRepositoryGateway quoteRepositoryGateway){
        return new FileService(quoteRepositoryGateway);
    }

    @Bean
    QuoteEntityMapper quoteEntityMapper(){
        return new QuoteEntityMapper();
    }




}
