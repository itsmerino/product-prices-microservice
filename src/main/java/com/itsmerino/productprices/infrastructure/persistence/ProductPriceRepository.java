package com.itsmerino.productprices.infrastructure.persistence;

import com.itsmerino.productprices.infrastructure.persistence.entity.ProductPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductPriceRepository extends JpaRepository<ProductPriceEntity, Integer> {

    List<ProductPriceEntity> findAllByProductIdAndBrandId(Integer productId,
                                                          Integer brandId);
}
