package com.crud.prices.controller;


import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateConverter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crud.prices.model.Prices;
import com.crud.prices.service.PricesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name= "Prices API")
@RestController
@RequestMapping("/prices")
@CrossOrigin(origins = "*")
public class PricesController {
	
	@Autowired  
	PricesService pricesService;  
    
	@Operation(summary = "Retorna los precios en base a los filtros de Fecha, BrandId y ProductId")
    @ApiResponse(responseCode = "200", description = "ok")
	@ApiResponse(responseCode = "204", description = "No existen registros")
	@ApiResponse(responseCode = "404", description = "Not found")
	@ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping("/rate")
    public ResponseEntity<List<Prices>> getPricesByParameters(@RequestParam(value="fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  LocalDateTime fecha,@RequestParam(value="productId") long productId,@RequestParam(value="brandId", required = true) long brandId) {
    	List<Prices> cPrices = pricesService.getPricesByParameters(fecha, productId, brandId);
		if (cPrices == null || cPrices.isEmpty())
		{
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.ok(cPrices);
		}
		
    }
    
    @Operation(summary = "Retorna todos los precios sin filtro")
    @ApiResponse(responseCode = "200", description = "ok")
	@ApiResponse(responseCode = "204", description = "No existen registros")
	@ApiResponse(responseCode = "404", description = "Not found")
	@ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping()
    public ResponseEntity<List<Prices>> getAllPrices() {
    	List<Prices> cPrices = pricesService.getAllPrices();  
		if (cPrices == null || cPrices.isEmpty())
		{
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.ok(cPrices);
		}
    }
    
    @Operation(summary = "Guarda un Price List")
    @ApiResponse(responseCode = "200", description = "Registro se guardó satisfactoriamente")
    @ApiResponse(responseCode = "201", description = "Registro se guardó satisfactoriamente")
	@ApiResponse(responseCode = "404", description = "Not found")
	@ApiResponse(responseCode = "500", description = "Internal server error")
    @PostMapping
    public ResponseEntity<?> post(@RequestBody Prices input){
    	Prices save = pricesService.save(input); 
    	return new ResponseEntity<>(save,HttpStatus.CREATED);
    }
	
}
