package com.vetris.apimanagement.v1.controller;

import javax.validation.Valid;

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

import com.vetris.apimanagement.v1.dto.request.InstitutionRegPhysicianLinkRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.apimanagement.v1.service.InstitutionRegPhysicianLinkService;

/**
 * Controller for Physician
 * 
 * @author Jose Eldhose
 *
 */

@RestController("InstitutionRegPhysicianLinkController")
@RequestMapping("/v1/physician")
@CrossOrigin(origins = "*")
public class InstitutionRegPhysicianLinkController {
	@Autowired
	private InstitutionRegPhysicianLinkService institutionRegPhysicianLinkService;

	/**
	 * @param id
	 * @return list of physicians
	 * @throws Exception
	 */
	@GetMapping("{id}")
	public ResponseEntity<CommonResponseDTO> fetchPhysicianById(@PathVariable("id") String id) throws Throwable {
		CommonResponseDTO resultDto = institutionRegPhysicianLinkService.getPhysicianById(id);
		return ResponseEntity.ok(resultDto);
	}

	/**
	 * 
	 * @return all Physicians
	 * @throws Exception
	 */
	@GetMapping("")
	public CommonResponseDTO fetchAllPhysicians() throws Throwable {
		return this.institutionRegPhysicianLinkService.getAllPhysicians();
	}

	/**
	 * @param physicianRequest
	 * @return String message
	 * @throws Exception
	 */
	@PostMapping("")
	public ResponseEntity<CommonResponseDTO> createPhysician(@Valid
			@RequestBody InstitutionRegPhysicianLinkRequestDTO physicianRequest) throws Throwable {
		CommonResponseDTO resultDto = institutionRegPhysicianLinkService.addPhysician(physicianRequest);
		return ResponseEntity.ok(resultDto);
	}

	/**
	 * @param physician
	 * @param id
	 * @return physician
	 * @throws Exception
	 */
	@PutMapping("{id}")
	public ResponseEntity<CommonResponseDTO> updatePhysician(@Valid
			@RequestBody InstitutionRegPhysicianLinkRequestDTO physician, @PathVariable("id") String id)
			throws Throwable {
		CommonResponseDTO resultDto = institutionRegPhysicianLinkService.updatePhysician(physician, id);
		return ResponseEntity.ok(resultDto);
	}

	/**
	 * @param id
	 * @return string
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<CommonResponseDTO> deletePhysician(@PathVariable("id") String id) throws Throwable {
		CommonResponseDTO resultDto = institutionRegPhysicianLinkService.deletePhysician(id);
		return ResponseEntity.ok(resultDto);
	}

}
