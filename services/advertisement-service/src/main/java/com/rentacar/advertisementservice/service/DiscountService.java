package com.rentacar.advertisementservice.service;

import java.util.List;

import com.rentacar.advertisementservice.domain.Discount;
import com.rentacar.advertisementservice.domain.dto.DiscountDTO;

public interface DiscountService {
	Discount createDiscount(DiscountDTO discountDTO);
	
	void deleteDiscount(Long id);

    List<Discount> getAllDiscounts();
}
