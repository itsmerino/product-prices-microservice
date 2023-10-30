package com.itsmerino.productprices.infrastructure.persistence;

import com.itsmerino.productprices.domain.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice, Integer> {

    List<ProductPrice> findAllByProductIdAndBrandId(Integer productId,
                                                    Integer brandId);
}
