package br.com.magalu.desafio.domain.repository;

import br.com.magalu.desafio.domain.entity.QuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Pedro Henrique Veiga
 * @created 06/06/24
 * @lastModified 06/06/24
 */

@Repository
public interface QuoteRepository extends JpaRepository<QuoteEntity, Integer> {

    @Query(nativeQuery = true, value = "select * from QUOTE q " +
            "where q.DATE_BUY between :dateStart and :dateEnd and q.ORDER_ID = :id order by q.ORDER_ID desc")
    List<QuoteEntity> findAllWithFilters(Integer id, String dateStart, String dateEnd);

    @Query(nativeQuery = true, value = "select * from QUOTE q " +
            "where q.ORDER_ID = :id order by q.ORDER_ID desc")
    List<QuoteEntity> findAllByOrderId(Integer id);
}
