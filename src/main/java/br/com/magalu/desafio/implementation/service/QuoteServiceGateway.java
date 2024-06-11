package br.com.magalu.desafio.implementation.service;

import br.com.magalu.desafio.api.dto.QuoteOutput;
import br.com.magalu.desafio.api.dto.QuoteInput;
import br.com.magalu.desafio.api.gateways.QuoteGateway;
import br.com.magalu.desafio.domain.entity.QuoteEntity;
import br.com.magalu.desafio.domain.repository.QuoteRepository;
import br.com.magalu.desafio.infra.exceptions.BadRequestException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

import static br.com.magalu.desafio.implementation.util.Converter.convertStringToTimestamp;

/**
 * @author Pedro Henrique Veiga
 * @created 06/06/24
 * @lastModified 06/06/24
 */

@Service
public class QuoteServiceGateway implements QuoteGateway {



    @Autowired
    private final QuoteEntityMapper quoteEntityMapper;

    @Autowired
    private final FileService fileService;

    @Autowired
    EntityManager entityManager;

    public QuoteServiceGateway( QuoteEntityMapper quoteEntityMapper,
                               EntityManager entityManager, FileService fileService) {
        this.quoteEntityMapper = quoteEntityMapper;
        this.fileService = fileService;
        this.entityManager = entityManager;
    }

    @Override
    public List<QuoteOutput> process (MultipartFile file){

            if(file == null) throw new BadRequestException("Arquivo não pode ser nulo.");
            if(file.isEmpty()) throw new BadRequestException("Arquivo vazio.");
            return this.fileService.readMultipart(file);
    }



    @Override
    public List<QuoteOutput> search(Integer id, String dateInit, String dateEnd) {

        try {
            CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
            CriteriaQuery<QuoteEntity> query = criteriaBuilder.createQuery(QuoteEntity.class);
            Root<QuoteEntity> quote = query.from(QuoteEntity.class);
            List<Predicate> predicates = new ArrayList<>();

            if(id != null){
                predicates.add(criteriaBuilder.equal(quote.get("orderId"),id));
            }

            if (dateInit != null && dateEnd != null) {
                predicates.add(criteriaBuilder.between(quote.get("dateBuy"),
                        convertStringToTimestamp(dateInit),
                        convertStringToTimestamp(dateEnd)));
            }

            if(!predicates.isEmpty()){
                query.where(predicates.stream().toArray(Predicate[]::new));
            }

            TypedQuery<QuoteEntity> queryResult  = this.entityManager.createQuery(query);
            List<QuoteOutput> list = QuoteEntityMapper.groupByOrderId(QuoteEntityMapper.toDomainObjectList(queryResult.getResultList()));
            return list;

        }catch(Exception e){
            e.printStackTrace();
            throw new BadRequestException("Não foi possível filtrar os dados.");

        }
    }


}
