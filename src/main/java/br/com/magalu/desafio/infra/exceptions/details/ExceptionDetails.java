package br.com.magalu.desafio.infra.exceptions.details;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

/**
 * @author Pedro Henrique Veiga
 * @created 05/06/24
 * @lastModified 05/06/24
 */
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionDetails {

    private String title;

    private int status;

    private Instant timestamp;

    private String methodService;

    private String message;

}
