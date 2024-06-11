package br.com.magalu.desafio.implementation.service;


import br.com.magalu.desafio.api.dto.QuoteInput;
import br.com.magalu.desafio.api.dto.QuoteOutput;
import br.com.magalu.desafio.implementation.persistence.QuoteRepositoryGateway;
import br.com.magalu.desafio.infra.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FileService {


    @Autowired
    QuoteRepositoryGateway quoteRepositoryGateway;

    public FileService(QuoteRepositoryGateway quoteRepositoryGateway) {
        this.quoteRepositoryGateway = quoteRepositoryGateway;
    }

    public List<QuoteOutput> readMultipart(MultipartFile file) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            String line;
            Pattern pattern = Pattern.compile("(.{10})(.{45})(.{10})(.{10})(.{12})(.{8})");
            String regx = "^0+(?!$)";

            List<QuoteInput> quoteInputList = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);

                if (matcher.matches()) {
                    String userId = matcher.group(1).trim().replaceFirst(regx,"");
                    String userName = matcher.group(2).trim();
                    String orderId = matcher.group(3).trim().replaceFirst(regx,"");
                    String prodId = matcher.group(4).trim().replaceFirst(regx,"");
                    String value = matcher.group(5).trim();
                    String date = matcher.group(6).trim();


                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());

                    Date dateFormated = sdf.parse(date);
                    QuoteInput quoteInput = new QuoteInput(Integer.parseInt(userId),userName,Integer.parseInt(orderId),
                            Integer.parseInt(prodId),Double.parseDouble(value),dateFormated);
                    quoteInputList.add(quoteInput);
                }
            }
                   quoteRepositoryGateway.saveQuote(quoteInputList);

            return QuoteEntityMapper.groupByOrderId(quoteInputList);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
            throw new BadRequestException("Falha ao processar");
        }
    }

}
