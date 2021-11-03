package com.vetris.adminmanagement.v1.dto.request;

import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * CaseNotificationRuleRadiologistDtls Request DTO class
 * 
 * @author Jini
 *
 */

@Getter
@Setter
@Validated
public class CaseNotificationRuleRadiologistDtlsRequestDTO {

	@NotNull
	private String radiologistId;

	private String userId;

	@Size(max = 1, message = "notify_if_scheduled must be atmost 1 characters")
	private String notifyIfScheduled;

	@Size(max = 1, message = "notify_always must be atmost 1 characters")
	private String notifyAlways;

}