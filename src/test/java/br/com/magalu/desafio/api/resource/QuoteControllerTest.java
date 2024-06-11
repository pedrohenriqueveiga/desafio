package br.com.magalu.desafio.api.resource;


import br.com.magalu.desafio.api.dto.QuoteOutput;
import br.com.magalu.desafio.api.usecases.CreateQuoteInteractor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(QuoteController.class)
public class QuoteControllerTest {


    @Mock
    CreateQuoteInteractor createQuoteInteractor;

    @MockBean
    private QuoteController quoteController;


    @Autowired
    WebApplicationContext wContext;

    @Autowired
    private MockMvc mockMvc;

    MockMultipartFile mut;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());


    @BeforeEach
    void setUP() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wContext)
                .alwaysDo(MockMvcResultHandlers.print())
                .build();



    }

    @Test
    @DisplayName("Should make Request with Success")
    void processFile() throws Exception {

        String filePath = "src/main/resources/data_2.txt";
        File file = new File(filePath);
        byte[] fileContent = new byte[(int) file.length()];
        FileInputStream inputStream = new FileInputStream(file);
        inputStream.read(fileContent);

        mut = new MockMultipartFile(
                "file",
                "data_2.txt",  "text/plain",
                fileContent);


        mockMvc.perform(MockMvcRequestBuilders.multipart("/v1/quote/process")
                .file(mut))
                .andExpect(status().isOk())
                .andDo(print());

    }



    @Test
    @DisplayName("Should Make Request With Success")
    void search() throws Exception {

        Integer orderId = 756;
        String dateStart = "2024-06-01";
        String dateEnd = "2024-06-10";
        List<QuoteOutput> expectedQuotes = Arrays.asList(new QuoteOutput(),new QuoteOutput());

        when(quoteController.search(orderId, dateStart, dateEnd)).thenReturn(expectedQuotes);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/quote/search")
                        .param("order-id", orderId.toString())
                        .param("dateStart", dateStart)
                        .param("dateEnd", dateEnd))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());

        verify(quoteController, times(1)).search(orderId, dateStart, dateEnd);

    }
}
