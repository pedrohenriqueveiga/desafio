package br.com.magalu.desafio.api.gateways;

import br.com.magalu.desafio.api.dto.QuoteOutput;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Pedro Henrique Veiga
 * @created 06/06/24
 * @lastModified 06/06/24
 */

public interface QuoteGateway {

    List<QuoteOutput> process(MultipartFile file);

    List<QuoteOutput> search(Integer id, String dateStart, String dateEnd);

}
