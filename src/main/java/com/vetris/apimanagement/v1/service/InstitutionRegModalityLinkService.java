package com.vetris.apimanagement.v1.service;

import com.vetris.apimanagement.v1.dto.request.InstitutionRegModalityLinkRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;

/**
 * Service for InstitutionRegModalityLink
 * 
 * @author Jose Eldhose
 *
 */
public interface InstitutionRegModalityLinkService {

	public CommonResponseDTO addRegModality(InstitutionRegModalityLinkRequestDTO regModalityRequest) throws Exception;

	public CommonResponseDTO getAllRegModality() throws Exception;

	public CommonResponseDTO getRegModalityById(String id) throws Exception;

	public CommonResponseDTO updateInstitutionRegModality(InstitutionRegModalityLinkRequestDTO regModalityRequest,
			String id) throws Exception;

	public CommonResponseDTO deleteInstitutionRegModality(String id) throws Exception;

}