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

import com.vetris.apimanagement.v1.dto.request.InstitutionsRegRequestDTO;
import com.vetris.apimanagement.v1.dto.response.CommonResponseDTO;
import com.vetris.apimanagement.v1.service.InstitutionsRegService;

/**
 * Controller for InstitutionsReg
 * 
 * @author Dhanesh
 *
 */

@RestController("InstitutionsRegController")
@RequestMapping("/v1/institutionsRegController")
@CrossOrigin(origins = "*")
public class InstitutionsRegController {

	@Autowired
	InstitutionsRegService institutionsRegService;

	public InstitutionsRegController() {
	}

	@GetMapping("/{id}")
	public ResponseEntity<CommonResponseDTO> fetchInstitutionsRegById(@PathVariable("id") String id) throws Exception {
		CommonResponseDTO resultDto = institutionsRegService.getInstitutionsRegById(id);
		return ResponseEntity.ok(resultDto);
	}

	@GetMapping("")
	public CommonResponseDTO fetchAllInstitutionsReg() throws Exception {
		return this.institutionsRegService.getAllInstitutionsReg();
	}

	@PostMapping("")
	public ResponseEntity<CommonResponseDTO> createInstitutionsReg(
			@Valid @RequestBody InstitutionsRegRequestDTO institutionsRegRequestDTO) throws Exception {
		CommonResponseDTO resultDto = institutionsRegService.addInstitutionsReg(institutionsRegRequestDTO);
		return ResponseEntity.ok(resultDto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CommonResponseDTO> updateInstitutionsReg(
			@Valid @RequestBody InstitutionsRegRequestDTO institutionsRegRequestDTO, @PathVariable("id") String id)
			throws Exception {
		CommonResponseDTO resultDto = institutionsRegService.updateInstitutionsReg(institutionsRegRequestDTO, id);
		return ResponseEntity.ok(resultDto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<CommonResponseDTO> deleteInstitutionsReg(@PathVariable("id") String id) throws Exception {
		CommonResponseDTO resultDto = institutionsRegService.deleteInstitutionsReg(id);
		return ResponseEntity.ok(resultDto);
	}
}
