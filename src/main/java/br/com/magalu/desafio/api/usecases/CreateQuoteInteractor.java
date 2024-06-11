package br.com.magalu.desafio.api.usecases;

import br.com.magalu.desafio.api.dto.QuoteOutput;
import br.com.magalu.desafio.api.gateways.QuoteGateway;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Pedro Henrique Veiga
 * @created 06/06/24
 * @lastModified 06/06/24
 */

public class CreateQuoteInteractor {

    private QuoteGateway quoteGateway;

    public CreateQuoteInteractor(QuoteGateway quoteGateway){
        this.quoteGateway = quoteGateway;
    }


    public List<QuoteOutput> process(MultipartFile file){
        return quoteGateway.process(file);
    }

    public List<QuoteOutput> search(Integer id, String dateStart, String dateEnd){
        return quoteGateway.search(id,dateStart,dateEnd);

    }

}
