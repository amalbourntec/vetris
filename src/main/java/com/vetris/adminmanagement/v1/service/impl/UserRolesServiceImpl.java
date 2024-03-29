package com.vetris.adminmanagement.v1.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetris.adminmanagement.v1.dto.request.UserRolesRequestDTO;
import com.vetris.adminmanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.adminmanagement.v1.dto.response.UserRolesResponseDTO;
import com.vetris.adminmanagement.v1.exception.ResourceNotFoundException;
import com.vetris.adminmanagement.v1.repository.UserRolesRepostitory;
import com.vetris.adminmanagement.v1.service.UserRolesService;
import com.vetris.entity.UserRoles;
import com.vetris.enums.ErrorCodes;
import com.vetris.enums.StatusType;
import com.vetris.utils.JWTSecurityContextUtil;

/**
 * Service Impementation for UserRoles
 *
 * @author Jose Eldhose
 *
 */
@Service
public class UserRolesServiceImpl implements UserRolesService {

	@Autowired
	private UserRolesRepostitory userRoleRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private JWTSecurityContextUtil jwtSecurityContextUtil;
	
	/**
	 * Getting user roles by id
	 *
	 * @throws ResourceNotFoundException
	 */
	@Override
	public CommonResponseDTO getUserRolesById(int id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		UserRoles userroles = userRoleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User" + ErrorCodes.DATA_NOT_FOUND.getMessage()));

		UserRolesResponseDTO userRoleRespDTO = objectMapper.convertValue(userroles, UserRolesResponseDTO.class);
		BeanUtils.copyProperties(userroles, userRoleRespDTO);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(userRoleRespDTO);
		resultDto.setMessage("Fetched user role successfully");
		return resultDto;
	}

	/**
	 * Get all user roles
	 */
	@Override
	public CommonResponseDTO getAllUserRoles() throws Exception {
		List<UserRoles> userRolesList = this.userRoleRepository.findAll();
		CommonResponseDTO resultDto = new CommonResponseDTO();
		List<UserRolesResponseDTO> resultresponseDto = new ArrayList<>();
		try {
			if (userRolesList.isEmpty()) {
				resultDto.setStatus(StatusType.FAILURE.getMessage());
				resultDto.setPayload("");
				resultDto.setMessage("No user role found");
			} else {
				userRolesList.stream().forEach(userItem -> {
					resultresponseDto.add(objectMapper.convertValue(userItem, UserRolesResponseDTO.class));
				});
				resultDto.setStatus(StatusType.SUCCESS.getMessage());
				resultDto.setPayload(resultresponseDto);
				resultDto.setMessage("Fetched user roles successfully");
			}
		} catch (BeansException e) {
			e.printStackTrace();
		}
		return resultDto;
	}

	/**
	 * Insert user roles using request DTO
	 */
	@Override
	public CommonResponseDTO addUserRoles(UserRolesRequestDTO userRoleDto) throws Exception {
		UserRoles resultUserRoles = userRoleDto.toUserRolesRequestModel(userRoleDto);
		
		CommonResponseDTO resultDto = new CommonResponseDTO();
		if(!(resultUserRoles.getIsActive().equalsIgnoreCase("y")|| resultUserRoles.getIsActive().equalsIgnoreCase("n"))) {
			throw new DataIntegrityViolationException("isActive must be Y or N");
		}
		if(!(resultUserRoles.getIsVisible().equalsIgnoreCase("y")|| resultUserRoles.getIsVisible().equalsIgnoreCase("n"))) {
				throw new DataIntegrityViolationException("isVisible must be Y or N");
		}
		resultUserRoles.setCreatedBy(jwtSecurityContextUtil.getId());
//		resultUserRoles.setUpdateBy(jwtSecurityContextUtil.getId());
		resultUserRoles = userRoleRepository.save(resultUserRoles);
		UserRolesResponseDTO userRoleRespDto = objectMapper.convertValue(resultUserRoles, UserRolesResponseDTO.class);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(userRoleRespDto);
		resultDto.setMessage("Saved User Role successfully");
		
		return resultDto;
	}

	/**
	 * Update user roles by id
	 */
	@Override
	public CommonResponseDTO updateUserRoles(UserRolesRequestDTO userRoleDto, int id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		UserRoles resultUserRole1 = userRoleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));
		UserRoles resultUserRole = userRoleDto.toUserRolesRequestModel(userRoleDto);
		resultUserRole.setId(id);
		resultUserRole.setUpdateBy(jwtSecurityContextUtil.getId());
		
		resultUserRole = userRoleRepository.save(resultUserRole);
		UserRolesResponseDTO userRoleRespDto = objectMapper.convertValue(resultUserRole, UserRolesResponseDTO.class);
		userRoleRespDto.setCreatedBy(resultUserRole1.getCreatedBy());
		userRoleRespDto.setDateCreated(resultUserRole1.getDateCreated());
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(userRoleRespDto);
		resultDto.setMessage("Updated User Role Succesfully");

		return resultDto;
	}

	/**
	 * Delete the user role by id
	 */
	@Override
	public CommonResponseDTO deleteUserRoles(int id) throws Exception {
		UserRolesResponseDTO userRoleRespDto = new UserRolesResponseDTO();
		CommonResponseDTO resultDto = new CommonResponseDTO();
		userRoleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.DATA_NOT_FOUND.getMessage()));
		userRoleRepository.deleteById(id);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(userRoleRespDto);
		resultDto.setMessage("Deleted user role successfully");
		return resultDto;

	}

}