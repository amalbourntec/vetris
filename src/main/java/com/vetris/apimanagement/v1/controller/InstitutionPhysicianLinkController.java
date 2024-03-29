package com.vetris.apimanagement.v1.controller;

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

import com.vetris.apimanagement.v1.dto.request.InstitutionPhysicianLinkRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.apimanagement.v1.service.InstitutionPhysicianLinkService;

/**
 * Controller for Institution Physician Link
 * 
 * @author Jini
 *
 */

@RestController(value = "InstitutionPhysicianLinkController")
@RequestMapping("/v1/institution_physician_link")
@CrossOrigin(origins = "*")
public class InstitutionPhysicianLinkController {

	Logger logger = LoggerFactory.getLogger(InstitutionPhysicianLinkController.class);

	@Autowired
	InstitutionPhysicianLinkService institutionPhysicianLinkService;

	/**
	 * 
	 * @return all Institution Physician
	 * @throws Exception
	 */
	@GetMapping("")
	public CommonResponseDTO fetchAllInstitutionPhysicianLink() throws Exception {
		logger.info("[fetchAllInstitutionPhysicianLink] InstitutionPhysicianLinkController called");
		return institutionPhysicianLinkService.getAllInstitutionPhysicianLink();
	}

	/**
	 * @param physicianId
	 * @return list of Institution Physician
	 * @throws Exception
	 */
	@GetMapping("{physician_id}")
	public ResponseEntity<CommonResponseDTO> fetchInstitutionPhysicianLinkById(
			@PathVariable("physician_id") String physicianId) throws Exception {
		logger.info("[fetchInstitutionPhysicianLinkById] InstitutionPhysicianLinkController called by physicianId");
		CommonResponseDTO resultDto = institutionPhysicianLinkService.getInstitutionPhysicianLinkById(physicianId);
		return ResponseEntity.ok(resultDto);
	}

	/**
	 * @param Institution
	 *            Physician Request
	 * @return String message
	 * @throws Exception
	 */
	@PostMapping("")
	public ResponseEntity<CommonResponseDTO> createInstitutionPhysicianLink(
			@Valid @RequestBody InstitutionPhysicianLinkRequestDTO institutionPhysicianLinkRequest) throws Exception {
		logger.info("[createInstitutionPhysicianLink] InstitutionPhysicianLinkController called");
		CommonResponseDTO resultDto = institutionPhysicianLinkService
				.addInstitutionPhysicianLink(institutionPhysicianLinkRequest);
		return ResponseEntity.ok(resultDto);
	}

	/**
	 * @param Institution
	 *            Physician
	 * @param physicianId
	 * @return Institution Physician
	 * @throws Exception
	 */
	@PutMapping("/{physician_id}")
	public ResponseEntity<CommonResponseDTO> updateInstitutionPhysicianLink(
			@Valid @RequestBody InstitutionPhysicianLinkRequestDTO institutionPhysicianLinkReqDto,
			@PathVariable(value = "physician_id") String physicianId) throws Exception {
		logger.info("[updateInstitutionPhysicianLink] InstitutionPhysicianLinkController called by physicianId");
		CommonResponseDTO resultDto = institutionPhysicianLinkService
				.updateInstitutionPhysicianLink(institutionPhysicianLinkReqDto, physicianId);
		return ResponseEntity.ok(resultDto);
	}

	/**
	 * @param physicianId
	 * @return string
	 * @throws Exception
	 */
	@DeleteMapping("/{physician_id}")
	public ResponseEntity<CommonResponseDTO> deleteInstitutionPhysicianLink(
			@PathVariable(value = "physician_id") String physicianId) throws Exception {
		logger.info("[deleteInstitutionPhysicianLink] InstitutionPhysicianLinkController called by physicianId");
		CommonResponseDTO resultDto = institutionPhysicianLinkService.deleteInstitutionPhysicianLink(physicianId);
		return ResponseEntity.ok(resultDto);
	}

}
