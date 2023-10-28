package com.itsmerino.productprices.infrastructure.persistence;

import com.itsmerino.productprices.domain.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface H2ProductPriceRepository extends JpaRepository<ProductPrice, Integer> {

    @Query("SELECT p " +
            "FROM ProductPrice p " +
            "WHERE p.productId = :productId AND p.brandId = :brandId AND :date BETWEEN p.startDate AND p.endDate " +
            "ORDER BY p.rate DESC " +
            "LIMIT 1")
    Optional<ProductPrice> findByProductIdAndBrandIdAndDate(Integer productId,
                                                            Integer brandId,
                                                            LocalDateTime date);
}
