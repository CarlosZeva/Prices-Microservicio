package com.crud.prices.service;
import java.time.LocalDateTime;
import java.util.ArrayList;  
import java.util.List;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crud.prices.model.Prices;
import com.crud.prices.repository.PricesRepository;  

@Service  
public class PricesService {

	@Autowired  
	PricesRepository pricesRepository;  

	public List<Prices> getAllPrices()   
	{  
		List<Prices> cPrices = new ArrayList<Prices>();  
		pricesRepository.findAll().forEach(priceS -> cPrices.add(priceS));  
		return cPrices;  
	}  

	public List<Prices> getPricesByParameters(LocalDateTime fecha, long productId, long brandId) {
		List<Prices> cPrices = pricesRepository.findTop2ByProductIdAndBrandIdAndEndDateGreaterThanEqualAndStartDateLessThanEqualOrderByPriorityDesc(productId, brandId, fecha, fecha);
		
		return cPrices;
	}
	
	public Prices save(Prices price)
	{  		
		Prices save = new Prices();
		save = pricesRepository.save(price);
        return save;
	}  
	
}


