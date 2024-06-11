package br.com.magalu.desafio.api.resource;

import br.com.magalu.desafio.api.dto.QuoteOutput;
import br.com.magalu.desafio.api.usecases.CreateQuoteInteractor;
import br.com.magalu.desafio.infra.exceptions.BadRequestException;
import jakarta.validation.Valid;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Pedro Henrique Veiga
 * @created 05/06/24
 * @lastModified 05/06/24
 */
@RestController
@RequestMapping(value = "/v1/quote")
@Scope("prototype")
@CrossOrigin(origins = "*", maxAge = 3600)
public class QuoteController {

    private CreateQuoteInteractor createQuoteInteractor;

    public QuoteController(CreateQuoteInteractor createQuoteInteractor){
        this.createQuoteInteractor = createQuoteInteractor;

    }

    @PostMapping("/process")
    public List<QuoteOutput> processFile(
           @RequestParam(value = "file" ,required = false) MultipartFile file) {

            return createQuoteInteractor.process(file);
    }

    @GetMapping("/search")
    public List<QuoteOutput> search(
            @RequestParam(value = "order-id",required = false)Integer orderId,
            @RequestParam(value = "dateStart",required = false)String dateStart,
            @RequestParam(value = "dateEnd",required = false)String dateEnd) {

        return createQuoteInteractor.search(orderId,dateStart,dateEnd);
    }



}
