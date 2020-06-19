package com.rentacar.agentbackend.service;

import java.util.List;

import com.rentacar.agentbackend.domain.PriceList;
import com.rentacar.agentbackend.domain.dto.PriceListDTO;

public interface PriceListService {
	PriceList createPriceList(PriceListDTO priceListDTO);
	
	void deletePriceList(Long id);

    List<PriceList> getAllPriceLists();
}
