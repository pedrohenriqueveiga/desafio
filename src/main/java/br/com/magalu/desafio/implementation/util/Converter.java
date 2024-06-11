package br.com.magalu.desafio.implementation.util;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class Converter {

    private static final String TIMESTAMP_FORMAT = "yyyy-MM-dd";

    public static Timestamp convertStringToTimestamp(String strDate) throws ParseException {
            SimpleDateFormat dateFormat = new SimpleDateFormat(TIMESTAMP_FORMAT);
            java.util.Date parsedDate = dateFormat.parse(strDate);
            return new Timestamp(parsedDate.getTime());

    }
}
