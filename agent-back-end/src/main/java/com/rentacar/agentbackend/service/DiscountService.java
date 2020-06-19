package com.rentacar.agentbackend.service;

import java.util.List;

import com.rentacar.agentbackend.domain.Discount;
import com.rentacar.agentbackend.domain.dto.DiscountDTO;

public interface DiscountService {
	Discount createDiscount(DiscountDTO discountDTO);
	
	void deleteDiscount(Long id);

    List<Discount> getAllDiscounts();
}
