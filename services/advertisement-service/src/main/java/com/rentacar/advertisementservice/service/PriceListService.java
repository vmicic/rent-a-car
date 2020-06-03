package com.rentacar.advertisementservice.service;

import java.util.List;

import com.rentacar.advertisementservice.domain.Discount;
import com.rentacar.advertisementservice.domain.PriceList;
import com.rentacar.advertisementservice.domain.dto.PriceListDTO;

public interface PriceListService {
	PriceList createPriceList(PriceListDTO priceListDTO);
	
	void deletePriceList(Long id);

    List<PriceList> getAllPriceLists();
}
