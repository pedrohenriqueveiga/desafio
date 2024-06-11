package br.com.magalu.desafio.implementation.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.sql.Timestamp;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({MockitoExtension.class})
@MockitoSettings(strictness = Strictness.LENIENT)
class ConverterTest {

    @Test
    @DisplayName("Should convert String date to TimeStamp")
    void ShouldConvertStringDateInTimeStamp() throws ParseException {
        String strDate = "2021-08-03";
        Timestamp expectedTimestamp = Timestamp.valueOf("2021-08-03 00:00:00.0");
        var output = Converter.convertStringToTimestamp(strDate);
        assertEquals(expectedTimestamp, output);

    }

    @Test
    @DisplayName("Should Fail Invalid Date")
    void ShouldFailInvalidDate() {
        String invalidDate = "invalid-date";

        assertThrows(ParseException.class, () -> {
            Converter.convertStringToTimestamp(invalidDate);
        });
    }

    @Test
    @DisplayName("Should Fail By Null Date")
    void ShouldFailByNullDate() {
        String nullDate = null;

        assertThrows(NullPointerException.class, () -> {
            Converter.convertStringToTimestamp(nullDate);
        });

    }
}