package com.itsmerino.productprices.infrastructure.persistence;

import com.itsmerino.productprices.infrastructure.persistence.entity.ProductPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPriceEntity, Integer> {

    List<ProductPriceEntity> findAllByProductIdAndBrandId(Integer productId,
                                                          Integer brandId);
}
