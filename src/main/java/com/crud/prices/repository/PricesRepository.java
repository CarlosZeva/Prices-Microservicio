package com.crud.prices.repository;


import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.crud.prices.model.Prices;

public interface PricesRepository extends JpaRepository<Prices, Long> {

	 List<Prices> findTop2ByProductIdAndBrandIdAndEndDateGreaterThanEqualAndStartDateLessThanEqualOrderByPriorityDesc(long productId,long brandId, LocalDateTime fecha, LocalDateTime fecha2);

}
