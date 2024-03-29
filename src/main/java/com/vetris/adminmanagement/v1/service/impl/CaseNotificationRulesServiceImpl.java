package com.vetris.adminmanagement.v1.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetris.adminmanagement.v1.dto.request.CaseNotificationRulesRequestDTO;
import com.vetris.adminmanagement.v1.dto.response.CaseNotificationRulesResponseDTO;
import com.vetris.adminmanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.adminmanagement.v1.dto.response.UserResponseDTO;
import com.vetris.adminmanagement.v1.exception.ResourceNotFoundException;
import com.vetris.adminmanagement.v1.repository.CaseNotificationRulesRepository;
import com.vetris.adminmanagement.v1.service.CaseNotificationRulesService;
import com.vetris.entity.CaseNotificationRules;
import com.vetris.enums.ErrorCodes;
import com.vetris.enums.StatusType;
import com.vetris.utils.JWTSecurityContextUtil;

/**
 * Service Implementation for Case Notification Rules
 * 
 * @author Jose Eldhose
 *
 */
@Service
public class CaseNotificationRulesServiceImpl implements CaseNotificationRulesService {

	@Autowired
	private CaseNotificationRulesRepository caseNotificationRulesRepo;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private JWTSecurityContextUtil jwtSecurityContextUtil;

	/**
	 * Getting Case notification rule by no
	 */
	@Override
	public CommonResponseDTO getCaseNotificationRulesByRuleNo(Integer id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		List<CaseNotificationRulesResponseDTO> notificationRuleRespDTO = new ArrayList<CaseNotificationRulesResponseDTO>();
		List<CaseNotificationRules> existingNotificationRule = caseNotificationRulesRepo.findByRuleNo(id);
		if (existingNotificationRule.isEmpty()) {
			resultDto.setStatus(StatusType.FAILURE.toString());
			resultDto.setPayload("");
			resultDto.setMessage("Not found");
		} else {
			existingNotificationRule.stream().forEach(item -> {
				notificationRuleRespDTO.add(objectMapper.convertValue(item, CaseNotificationRulesResponseDTO.class));
			});

			resultDto.setStatus(StatusType.SUCCESS.toString());
			resultDto.setPayload(notificationRuleRespDTO);
			resultDto.setMessage("Fetched case notification rules successfully");
		}
		return resultDto;
	}

	/**
	 * Get all Case notification rules
	 */

	// @Override
	public CommonResponseDTO getAllCaseNotificationRules()
			throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		List<CaseNotificationRulesResponseDTO> caseNotificationRulesResponseDTO = new ArrayList<CaseNotificationRulesResponseDTO>();
		List<CaseNotificationRules> notificationRules = caseNotificationRulesRepo
				.findAll();
		if (notificationRules.isEmpty()) {
			resultDto.setStatus(StatusType.FAILURE.getMessage());
			resultDto.setPayload("");
			resultDto.setMessage("No notification rule found");
		} else {
			notificationRules.stream().forEach(notificationRulesList -> {
				caseNotificationRulesResponseDTO.add(objectMapper.convertValue(notificationRulesList, CaseNotificationRulesResponseDTO.class));
			});
			resultDto.setStatus(StatusType.SUCCESS.getMessage());
			resultDto.setPayload(caseNotificationRulesResponseDTO);
			resultDto.setMessage("Fetched case notification rules successfully");
		}

		return resultDto;
	}

	/**
	 * Adding the existing Case notification rule
	 */
	@Override
	public CommonResponseDTO addCaseNotificationRules(CaseNotificationRulesRequestDTO notificationRuleRequest)
			throws Exception {
		CaseNotificationRulesResponseDTO notificationRuleRespDTO = new CaseNotificationRulesResponseDTO();
		CommonResponseDTO resultDto = new CommonResponseDTO();
		CaseNotificationRules resultNotificationRule = objectMapper.convertValue(notificationRuleRequest,
				CaseNotificationRules.class);
		resultNotificationRule.setCreatedBy(jwtSecurityContextUtil.getId());
		resultNotificationRule = caseNotificationRulesRepo.save(resultNotificationRule);
		BeanUtils.copyProperties(resultNotificationRule, notificationRuleRespDTO);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setPayload(notificationRuleRespDTO);
		resultDto.setMessage("Saved case notification rule successfully");

		return resultDto;
	}

	/**
	 * Updating a Case notification rule using request DTO
	 */

	@Override
	public CommonResponseDTO updateCaseNotificationRules(CaseNotificationRulesRequestDTO notificationRuleDto,
			Integer ruleNo, Integer pacsStatusId, Integer priorityId) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		CaseNotificationRules resultNotificationRule = caseNotificationRulesRepo
				.findByRuleNoANDPacsStatusIdANDPriorityId(ruleNo, pacsStatusId, priorityId)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Case notiication rules" + ErrorCodes.DATA_NOT_FOUND.getMessage()));
			BeanUtils.copyProperties(notificationRuleDto, resultNotificationRule);
			resultNotificationRule.setUpdateBy(jwtSecurityContextUtil.getId());
			resultNotificationRule = caseNotificationRulesRepo.save(resultNotificationRule);
			CaseNotificationRulesResponseDTO notificationRuleRespDTO = objectMapper.convertValue(resultNotificationRule,
					CaseNotificationRulesResponseDTO.class);
			resultDto.setStatus(StatusType.SUCCESS.getMessage());
			resultDto.setPayload(notificationRuleRespDTO);
			resultDto.setMessage("Fetched case notification rules successfully");

			return resultDto;
		}

	/**
	 * Deleting the Case notification rule by id
	 */

	@Override
	public CommonResponseDTO deleteCaseNotificationRules(Integer ruleNo, Integer pacsStatusId, Integer priorityId)
			throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		CaseNotificationRules caseNotificationRules = caseNotificationRulesRepo
				.findByRuleNoANDPacsStatusIdANDPriorityId(ruleNo, pacsStatusId, priorityId)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Case notification rules" + ErrorCodes.DATA_NOT_FOUND.getMessage()));
		caseNotificationRulesRepo.delete(caseNotificationRules);
		resultDto.setStatus(StatusType.SUCCESS.getMessage());
		resultDto.setMessage("Deleted case notiication rule successfully");
		return resultDto;
	}

	/**
	 * get case notification rule by PacsStatusId
	 */
	@Override
	public CommonResponseDTO getCaseNotificationRulesByPacsStatusId(Integer id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		List<CaseNotificationRulesResponseDTO> notificationRuleRespDTO = new ArrayList<CaseNotificationRulesResponseDTO>();
		List<CaseNotificationRules> existingNotificationRule = caseNotificationRulesRepo.findByPacsStatusId(id);
		if (existingNotificationRule.isEmpty()) {
			resultDto.setStatus(StatusType.FAILURE.toString());
			resultDto.setPayload("");
			resultDto.setMessage("Not found");
		} else {
			existingNotificationRule.stream().forEach(item -> {
				notificationRuleRespDTO.add(objectMapper.convertValue(item, CaseNotificationRulesResponseDTO.class));
			});

			resultDto.setStatus(StatusType.SUCCESS.toString());
			resultDto.setPayload(notificationRuleRespDTO);
			resultDto.setMessage("Fetched case notification rules successfully");
		}
		return resultDto;
	}

	/**
	 * get case notification rule by PriorityId
	 */
	@Override
	public CommonResponseDTO getCaseNotificationRulesByPriorityId(Integer id) throws Exception {
		CommonResponseDTO resultDto = new CommonResponseDTO();
		List<CaseNotificationRulesResponseDTO> notificationRuleRespDTO = new ArrayList<CaseNotificationRulesResponseDTO>();
		List<CaseNotificationRules> existingNotificationRule = caseNotificationRulesRepo.findByPriorityId(id);

		if (existingNotificationRule.isEmpty()) {
			resultDto.setStatus(StatusType.FAILURE.toString());
			resultDto.setPayload("");
			resultDto.setMessage("Not found");
		} else {
			existingNotificationRule.stream().forEach(item -> {
				notificationRuleRespDTO.add(objectMapper.convertValue(item, CaseNotificationRulesResponseDTO.class));
			});

			resultDto.setStatus(StatusType.SUCCESS.toString());
			resultDto.setPayload(notificationRuleRespDTO);
			resultDto.setMessage("Fetched case notification rules successfully");
		}

		return resultDto;
	}

	public CommonResponseDTO getAllCaseNotificationRules(int i, int j, int k) {
		// TODO Auto-generated method stub
		return null;
	}

}
