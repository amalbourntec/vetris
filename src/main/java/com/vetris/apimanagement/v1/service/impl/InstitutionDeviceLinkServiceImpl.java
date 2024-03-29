package com.vetris.apimanagement.v1.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetris.apimanagement.v1.dto.request.InstitutionDeviceLinkRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.apimanagement.v1.dto.response.InstitutionDeviceLinkResponseDTO;
import com.vetris.apimanagement.v1.exception.ResourceNotFoundException;
import com.vetris.apimanagement.v1.repository.InstitutionDeviceLinkRepository;
import com.vetris.apimanagement.v1.service.InstitutionDeviceLinkService;
import com.vetris.entity.InstitutionDeviceLink;
import com.vetris.enums.ErrorCodes;
import com.vetris.enums.StatusType;
import com.vetris.utils.JWTSecurityContextUtil;

/**
 * Service Implementation for InstitutionDeviceLink
 * 
 * @author Aldrin
 *
 */
@Service
public class InstitutionDeviceLinkServiceImpl implements InstitutionDeviceLinkService {

	@Autowired
	private InstitutionDeviceLinkRepository institutionDeviceLinkRepository;

	@Autowired
	private JWTSecurityContextUtil jwtSecurityContextUtil;

	@Autowired
	ObjectMapper objectMapper;

	/**
	 * Getting institution device link by id
	 */
	@Override
	public CommonResponseDTO getDeviceById(String id) throws Exception {

		CommonResponseDTO resultDto = new CommonResponseDTO();
		InstitutionDeviceLink existingDevice = institutionDeviceLinkRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));

		InstitutionDeviceLinkResponseDTO deviceRespDTO = objectMapper.convertValue(existingDevice,
				InstitutionDeviceLinkResponseDTO.class);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(deviceRespDTO);
		resultDto.setMessage("Fetched device successfully");

		return resultDto;
	}

	/**
	 * Getting all institution device link
	 */
	@Override
	public CommonResponseDTO getAllDevice() throws Exception {

		CommonResponseDTO resultDto = new CommonResponseDTO();
		List<InstitutionDeviceLink> institutionDeviceLinkList = institutionDeviceLinkRepository.findAll();
		List<InstitutionDeviceLinkResponseDTO> deviceRespDTO = new ArrayList<>();
		if (institutionDeviceLinkList.isEmpty()) {
			 throw new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage());
		} else {
			institutionDeviceLinkList.stream().forEach(device -> {
				deviceRespDTO.add(objectMapper.convertValue(device, InstitutionDeviceLinkResponseDTO.class));
			});
		}
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(deviceRespDTO);
		resultDto.setMessage("Fetched list of device successfully");
		return resultDto;
	}

	/**
	 * Adding a institution device link using request DTO
	 */
	@Override
	public CommonResponseDTO addDevice(InstitutionDeviceLinkRequestDTO data) throws Exception {

		CommonResponseDTO resultDto = new CommonResponseDTO();
		UUID uuid = UUID.randomUUID();
		InstitutionDeviceLink device = objectMapper.convertValue(data, InstitutionDeviceLink.class);
		device.setDeviceId(uuid.toString());
		device.setCreatedBy(jwtSecurityContextUtil.getId());
		device = institutionDeviceLinkRepository.save(device);

		InstitutionDeviceLinkResponseDTO deviceRespDTO = objectMapper.convertValue(device,
				InstitutionDeviceLinkResponseDTO.class);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(deviceRespDTO);
		resultDto.setMessage("Device added successfully");
		return resultDto;
	}

	/**
	 * updating the existing institution device link
	 */
	@Override
	public CommonResponseDTO updateDevice(InstitutionDeviceLinkRequestDTO data, String id) throws Exception {

		CommonResponseDTO resultDto = new CommonResponseDTO();
		InstitutionDeviceLink device = institutionDeviceLinkRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));

		BeanUtils.copyProperties(data, device);
		device.setUpdateBy(jwtSecurityContextUtil.getId());
		device = institutionDeviceLinkRepository.save(device);

		InstitutionDeviceLinkResponseDTO deviceRespDTO = objectMapper.convertValue(device,
				InstitutionDeviceLinkResponseDTO.class);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(deviceRespDTO);
		resultDto.setMessage("Device updated successfully");

		return resultDto;
	}

	/**
	 * Deleting the institution device link by device id
	 */
	@Override
	public CommonResponseDTO deleteDevice(String id) throws Exception {

		CommonResponseDTO resultDto = new CommonResponseDTO();
		institutionDeviceLinkRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));

		institutionDeviceLinkRepository.deleteById(id);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setMessage("Device deleated successfully");

		return resultDto;
	}

}
