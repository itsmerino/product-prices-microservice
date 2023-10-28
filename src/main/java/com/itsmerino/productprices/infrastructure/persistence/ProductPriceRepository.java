package com.itsmerino.productprices.infrastructure.persistence;

import com.itsmerino.productprices.domain.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice, Integer> {

    @Query("SELECT p " +
            "FROM ProductPrice p " +
            "WHERE p.productId = :productId AND p.brandId = :brandId AND :date BETWEEN p.startDate AND p.endDate")
    List<ProductPrice> findAllByProductIdAndBrandIdAndDate(Integer productId,
                                                           Integer brandId,
                                                           LocalDateTime date);
}
