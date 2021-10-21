package com.vetris.usermanagement.v1.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetris.entity.InstitutionAltNameLink;
import com.vetris.enums.ErrorCodes;
import com.vetris.enums.StatusType;
import com.vetris.usermanagement.v1.dto.request.InstitutionAltNameLinkRequestDTO;
import com.vetris.usermanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.usermanagement.v1.dto.response.InstitutionAltNameLinkResponseDTO;
import com.vetris.usermanagement.v1.exception.ResourceNotFoundException;
import com.vetris.usermanagement.v1.repository.InstitutionAltNameLinkRepository;
import com.vetris.usermanagement.v1.service.InstitutionAltNameLinkService;
import com.vetris.utils.JWTSecurityContextUtil;

/**
 * Service Implementation for InstitutionAltNameLink
 * @author Aldrin
 *
 */
@Service
public class InstitutionAltNameLinkServiceImpl implements InstitutionAltNameLinkService{

	@Autowired
	InstitutionAltNameLinkRepository institutionAltNameLinkRepo;
	
	@Autowired
	private JWTSecurityContextUtil jwtSecurityContextUtil;
	
	@Autowired
	ObjectMapper objectMapper;
	
	/**
	 * Getting institution alternate name by id
	 */
	@Override
	public CommonResponseDTO getInstitutionAltNameById(Integer id) throws Exception {
		
		CommonResponseDTO resultDto = new CommonResponseDTO();
		InstitutionAltNameLink existingAltName=institutionAltNameLinkRepo.findById(id).orElseThrow(()->new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));
		
		    InstitutionAltNameLinkResponseDTO altNameRespDTO=objectMapper.convertValue(existingAltName, InstitutionAltNameLinkResponseDTO.class);
			resultDto.setStatus(StatusType.Success.toString());
			resultDto.setPayload(altNameRespDTO);
			resultDto.setMessage("Fetched institution alternate name successfully");
		
		return resultDto;
		
	}

	/**
	 * Getting all institution alternate name 
	 */
	@Override
	public CommonResponseDTO getAllInstitutionAltName() throws Exception {
		
		CommonResponseDTO resultDto = new CommonResponseDTO();
		List<InstitutionAltNameLink> institutionAltNameLinkList=institutionAltNameLinkRepo.findAll();
		List<InstitutionAltNameLinkResponseDTO> altNameRespDTO=new ArrayList<>();
		if(institutionAltNameLinkList.isEmpty()) {
			resultDto.setStatus(StatusType.Failure.toString());
			resultDto.setMessage("Institution alternate name not found");
		}else {
			institutionAltNameLinkList.stream()
			.forEach(altName->{altNameRespDTO
				.add(objectMapper.convertValue(altName, InstitutionAltNameLinkResponseDTO.class));
		});
		}
		resultDto.setStatus(StatusType.Success.toString());
		resultDto.setPayload(altNameRespDTO);
		resultDto.setMessage("Fetched list of institution alternate name successfully");
		return resultDto;
	}
	/**
	 * Adding a institution alternate name using request DTO
	 */
	@Override
	public CommonResponseDTO addInstitutionAltName(InstitutionAltNameLinkRequestDTO data) throws Exception {
		
		CommonResponseDTO resultDto = new CommonResponseDTO();
		InstitutionAltNameLink altName=objectMapper.convertValue(data, InstitutionAltNameLink.class);
		altName.setCreatedBy(jwtSecurityContextUtil.getId());
		institutionAltNameLinkRepo.save(altName);
		
		InstitutionAltNameLinkResponseDTO altNameRespDTO=objectMapper.convertValue(altName, InstitutionAltNameLinkResponseDTO.class);
		resultDto.setStatus(StatusType.Success.toString());
		resultDto.setPayload(altNameRespDTO);
		resultDto.setMessage("institution alternate name added successfully");
		return resultDto;
	}
	/**
	 * updating the existing institution alternate name
	 */
	@Override
	public CommonResponseDTO updateInstitutionAltName(InstitutionAltNameLinkRequestDTO data, Integer id)
			throws Exception {
		
		CommonResponseDTO resultDto = new CommonResponseDTO();
		institutionAltNameLinkRepo.findById(id).orElseThrow(()->new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));
			
			    InstitutionAltNameLink altName=objectMapper.convertValue(data, InstitutionAltNameLink.class);
				altName.setId(id);
				altName.setUpdateBy(jwtSecurityContextUtil.getId());
				institutionAltNameLinkRepo.save(altName);
				
				InstitutionAltNameLinkResponseDTO altNameRespDTO=objectMapper.convertValue(altName, InstitutionAltNameLinkResponseDTO.class);
				resultDto.setStatus(StatusType.Success.toString());
				resultDto.setPayload(altNameRespDTO);
				resultDto.setMessage("Institution alternate name updated successfully");
			
		return resultDto;
	}
	/**
	 * Deleting the institution alternate name by id
	 */
	@Override
	public CommonResponseDTO deleteInstitutionAltName(Integer id) throws Exception {
		
		CommonResponseDTO resultDto = new CommonResponseDTO();
		institutionAltNameLinkRepo.findById(id).orElseThrow(()->new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));
		
		institutionAltNameLinkRepo.deleteById(id);
		resultDto.setStatus(StatusType.Success.toString());
		resultDto.setMessage("Institution alternate name deleated successfully");
		
		return resultDto;
	}

}