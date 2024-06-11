package br.com.magalu.desafio.implementation.service;

import br.com.magalu.desafio.api.dto.OrderDTO;
import br.com.magalu.desafio.api.dto.ProductDTO;
import br.com.magalu.desafio.api.dto.QuoteInput;
import br.com.magalu.desafio.api.dto.QuoteOutput;
import br.com.magalu.desafio.domain.entity.QuoteEntity;
import br.com.magalu.desafio.implementation.persistence.QuoteRepositoryGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
@MockitoSettings(strictness = Strictness.LENIENT)
class FileServiceTest {

    @Mock
    QuoteRepositoryGateway quoteRepositoryGateway;

    @InjectMocks
    FileService fileService;



    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    @Test
    @DisplayName("Should Read Multipart File With Success")
    void ShouldReadMultipartFileWithSuccess() throws IOException, ParseException {


        List<QuoteOutput> responseList = new ArrayList<>();

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


        String filePath = "src/main/resources/data_1.txt";
        byte[] fileContent = Files.readAllBytes(Paths.get(filePath));
        MultipartFile mut = new MockMultipartFile(
                "data_1",
                "data_1.txt",  "text/plain",
                fileContent);


        QuoteInput sendler = new QuoteInput(75, "Bobbie Batz", 798, 2,  1578.57, sdf.parse("20211116"));
        QuoteEntity receiver = new QuoteEntity(1, 75,"Bobbie Batz", 798, 2,  1578.57, sdf.parse("20211116"));
        doNothing().when(quoteRepositoryGateway).saveQuote(List.of(sendler));


        var output = fileService.readMultipart(mut);
        assertEquals(100, output.size());
        assertEquals("Sammie Baumbach",output.get(0).getName());

        verify(quoteRepositoryGateway,times(1)).saveQuote(any());

    }


}