package br.com.magalu.desafio.implementation.service;

import br.com.magalu.desafio.api.dto.OrderDTO;
import br.com.magalu.desafio.api.dto.ProductDTO;
import br.com.magalu.desafio.api.dto.QuoteInput;
import br.com.magalu.desafio.api.dto.QuoteOutput;
import br.com.magalu.desafio.domain.entity.QuoteEntity;
import br.com.magalu.desafio.domain.repository.QuoteRepository;
import br.com.magalu.desafio.infra.exceptions.BadRequestException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * @author Pedro Henrique Veiga
 * @created 07/06/24
 * @lastModified 07/06/24
 */

@ExtendWith({MockitoExtension.class})
@MockitoSettings(strictness = Strictness.LENIENT)
class QuoteServiceGatewayTest {


    @Mock
    QuoteRepository quoteRepository;

    @Mock
    QuoteEntityMapper quoteEntityMapper;

    @Mock
    FileService fileService;


    @Mock
    EntityManager entityManager;

    @InjectMocks
    QuoteServiceGateway quoteServiceGateway;



    @Mock
    private CriteriaBuilder criteriaBuilder;

    @Mock
    private CriteriaQuery<QuoteEntity> criteriaQuery;

    @Mock
    private Root<QuoteEntity> root;

    @Mock
    private TypedQuery<QuoteEntity> typedQuery;




    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


        @Test
        @DisplayName(value = "Shoud Process File With Success")
        void ShouldProcessFileWIthSuccess() throws IOException, ParseException {

            List<QuoteOutput>  responseList = new ArrayList<>();

                List<ProductDTO> products1 = new ArrayList<>();
                products1.add(new ProductDTO(2, 1000.01));
                products1.add(new ProductDTO(3, 500.49));

                List<ProductDTO> products2 = new ArrayList<>();
                products2.add(new ProductDTO(3, 200.49));

                OrderDTO order1 = new OrderDTO(753, sdf.parse("20210308"));
                order1.addProduct(2,1000.01);
                order1.addProduct(3,500.49);

                OrderDTO order2 = new OrderDTO(754, sdf.parse("20210309"));
                order2.addProduct(2,400.01);
                order2.addProduct(3,500.49);

                List<OrderDTO> orders = new ArrayList<>();
                orders.add(order1);
                orders.add(order2);

                QuoteOutput order = new QuoteOutput(70, "Palmer Prosaccog");
                order.addOrders(orders);
                responseList.add(order);



            QuoteInput input = new QuoteInput(70,"Palmer Prosaccog",754,2,400.01,sdf.parse("20210309"));

            String filePath = "src/main/resources/data_1.txt";
            byte[] fileContent = Files.readAllBytes(Paths.get(filePath));
            MultipartFile mut = new MockMultipartFile(
                    "data_1",
                    "data_1.txt",  "text/plain",
                    fileContent);



            when(fileService.readMultipart(mut)).thenReturn(responseList);
            List<QuoteOutput> output = fileService.readMultipart(mut);


            assertEquals(1, output.size());
            assertEquals(70, output.get(0).getUserId());
            assertEquals("Palmer Prosaccog",output.get(0).getName());
            assertEquals(1500.50,output.get(0).getOrders().get(0).getTotal());
            assertEquals(900.50, output.get(0).getOrders().get(1).getTotal());


        }

        @Test
        @DisplayName(value = "Shoud Not Process File With File Null")
        void ShouldNotProcessFileWithFileNull(){

            BadRequestException brE = assertThrows(BadRequestException.class, () -> {
                List<QuoteOutput> response = quoteServiceGateway.process(null);
            });

            assertThat(brE, notNullValue());
            assertThat(brE.getMessage(),is("Arquivo não pode ser nulo."));
            assertThat(brE.getCause(), nullValue());

        }

        @Test
        @DisplayName(value = "Shoud Not Process File With File Empty")
        void ShouldNotProcessFileWithFileEmpty(){
            MultipartFile emptyFile = new MockMultipartFile("file", new byte[0]);

            BadRequestException brE = assertThrows(BadRequestException.class, () -> {
                List<QuoteOutput> response = quoteServiceGateway.process(emptyFile);
            });

            assertThat(brE, notNullValue());
            assertThat(brE.getMessage(),is("Arquivo vazio."));
            assertThat(brE.getCause(), nullValue());

        }


        @Test
        @DisplayName(value = "Should Search Quote By Filters")
        void ShouldSearchQuoteByIdAndFilters() {

            Integer orderId = 123;
            String dateStart = "2024-06-01 00:00:00.000";
            String dateEnd = "2024-06-10 23:59:59.999";
            List<QuoteEntity> quoteEntities = List.of(new QuoteEntity());
            List<QuoteOutput> expectedQuotes =List.of(new QuoteOutput());

            when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
            when(criteriaBuilder.createQuery(QuoteEntity.class)).thenReturn(criteriaQuery);
            when(criteriaQuery.from(QuoteEntity.class)).thenReturn(root);
            when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);
            when(typedQuery.getResultList()).thenReturn(quoteEntities);

            try (var mockedStatic = mockStatic(QuoteEntityMapper.class)) {

                List<QuoteOutput> result = quoteServiceGateway.search(orderId, dateStart, dateEnd);

                assertEquals(new ArrayList<>(), result);

                verify(entityManager, times(1)).getCriteriaBuilder();
                verify(criteriaBuilder, times(1)).createQuery(QuoteEntity.class);
                verify(criteriaQuery, times(1)).from(QuoteEntity.class);
                verify(entityManager, times(1)).createQuery(criteriaQuery);
                verify(typedQuery, times(1)).getResultList();
            }


        }
}