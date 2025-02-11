package com.inditex.price;

import com.inditex.price.domain.model.PriceCriteria;
import com.inditex.price.domain.model.Price;
import com.inditex.price.domain.repository.IPriceRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceMysqlRepository extends JpaRepository<Price, Long>, IPriceRepository {

    @Query("""
            SELECT p 
            FROM Price p 
            WHERE p.productId = :#{#filter.productId} AND p.brandId = :#{#filter.brandId} 
            AND  :#{#filter.applyDate} BETWEEN p.startDate AND p.endDate 
            """
    )
    List<Price> findPriceByCriteria(@Param("filter") PriceCriteria priceCriteria);
}
