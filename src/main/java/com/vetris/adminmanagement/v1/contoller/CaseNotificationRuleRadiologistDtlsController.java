package com.vetris.adminmanagement.v1.contoller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vetris.adminmanagement.v1.dto.request.CaseNotificationRuleRadiologistDtlsRequestDTO;
import com.vetris.adminmanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.adminmanagement.v1.service.CaseNotificationRuleRadiologistDtlsService;

/**
 * Controller for CaseNotificationRuleRadiologistDtls
 * 
 * @author Jini
 *
 */

@RestController(value = "CaseNotificationRuleRadiologistDtlsController")
@RequestMapping("/v1/case_notification_rule_radiologist_dtls")
@CrossOrigin(origins = "*")
public class CaseNotificationRuleRadiologistDtlsController {

	Logger logger = LoggerFactory.getLogger(CaseNotificationRuleRadiologistDtlsController.class);

	@Autowired
	CaseNotificationRuleRadiologistDtlsService caseNotificationRuleRadiologistDtlsService;

	/**
	 * 
	 * @return all Case Notification Rule Radiologist Dtls
	 * @throws Exception
	 */
	@GetMapping("")
	public CommonResponseDTO fetchAllCaseNotificationRuleRadiologistDtls() throws Exception {
		logger.info(
				"[fetchAllCaseNotificationRuleRadiologistDtls] CaseNotificationRuleRadiologistDtlsController called");
		return caseNotificationRuleRadiologistDtlsService.getAllCaseNotificationRuleRadiologistDtls();
	}

	/**
	 * @param ruleNo
	 * @return list of Case Notification Rule Radiologist Dtls
	 * @throws Exception
	 */
	@GetMapping("/{rule_no}")
	public ResponseEntity<CommonResponseDTO> fetchCaseNotificationRuleRadiologistDtlsById(
			@PathVariable(value = "rule_no") Integer ruleNo) throws Exception {
		logger.info(
				"[fetchCaseNotificationRuleRadiologistDtlsById] CaseNotificationRuleRadiologistDtlsController called by ruleNo");
		CommonResponseDTO caseNotificationRuleRadiologistDtlsRespDto = caseNotificationRuleRadiologistDtlsService
				.getCaseNotificationRuleRadiologistDtlsById(ruleNo);
		return ResponseEntity.ok(caseNotificationRuleRadiologistDtlsRespDto);
	}

	/**
	 * @param Case
	 *            Notification Rule Radiologist Dtls Request
	 * @return String message
	 * @throws Exception
	 */
	@PostMapping("")
	public ResponseEntity<CommonResponseDTO> createCaseNotificationRuleRadiologistDtls(
			@Valid @RequestBody CaseNotificationRuleRadiologistDtlsRequestDTO caseNotificationRuleRadiologistDtlsRequestDTO)
			throws Exception {
		logger.info("[createCaseNotificationRuleRadiologistDtls] CaseNotificationRuleRadiologistDtlsController called");
		CommonResponseDTO resultDto = caseNotificationRuleRadiologistDtlsService
				.addCaseNotificationRuleRadiologistDtls(caseNotificationRuleRadiologistDtlsRequestDTO);
		return ResponseEntity.ok(resultDto);
	}

	/**
	 * @param Case
	 *            Notification Rule Radiologist Dtls
	 * @param ruleNo
	 * @return Case Notification Rule Radiologist Dtls
	 * @throws Exception
	 */
	@PutMapping("/{rule_no}")
	public ResponseEntity<CommonResponseDTO> updateCaseNotificationRuleRadiologistDtls(
			@Valid @RequestBody CaseNotificationRuleRadiologistDtlsRequestDTO caseNotificationRuleRadiologistDtlsRequestDTO,
			@PathVariable(value = "rule_no") Integer ruleNo) throws Exception {
		logger.info(
				"[updateCaseNotificationRuleRadiologistDtls] CaseNotificationRuleRadiologistDtlsController called by ruleNo");
		CommonResponseDTO resultDto = caseNotificationRuleRadiologistDtlsService
				.updateCaseNotificationRuleRadiologistDtls(caseNotificationRuleRadiologistDtlsRequestDTO, ruleNo);
		return ResponseEntity.ok(resultDto);
	}

	/**
	 * @param ruleNo
	 * @return string
	 * @throws Exception
	 */
	@DeleteMapping("/{rule_no}")
	public ResponseEntity<CommonResponseDTO> deleteCaseNotificationRuleRadiologistDtls(
			@PathVariable(value = "rule_no") Integer ruleNo) throws Exception {
		logger.info("[deleteInstitutionPhysicianLink] CaseNotificationRuleRadiologistDtlsController called by ruleNo");
		CommonResponseDTO resultDto = caseNotificationRuleRadiologistDtlsService
				.deleteCaseNotificationRuleRadiologistDtls(ruleNo);
		return ResponseEntity.ok(resultDto);
	}

}
