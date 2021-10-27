package com.vetris.mastermanagement.v1.service;

import com.vetris.mastermanagement.v1.dto.request.SalesPersonRequestDTO;
import com.vetris.mastermanagement.v1.dto.response.CommonResponseDTO;

/**
 * Service for salesPerson
 * 
 * @author ShekarReddySamreddy
 * 
 */
public interface SalesPersonService {

	public CommonResponseDTO getAllSalesPerson() throws Exception;

	public CommonResponseDTO saveSalesPerson(SalesPersonRequestDTO requestDto) throws Exception;

	public CommonResponseDTO editSalesPerson(SalesPersonRequestDTO requestDto, String id) throws Exception;

	public CommonResponseDTO deleteSalesPerson(String id) throws Exception;

	public CommonResponseDTO getSalesPersonById(String id) throws Exception;

}
