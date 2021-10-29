package com.vetris.mastermanagement.v1.dto.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstitutionUserLinkResponseDTO {

	private String userId;

	private String institutionId;

	private String userLoginId;
	
	private String userPwd;
	
	private String userPacsUserId;
	
	private String userPacsPassword;
	
	private String userEmail;
	
	private String grantedRightsPacs;

	private Character updatedInPacs;
	
	private Date dateUpdatedInPacs;
	
	private String userContactNo;
	
	private String createdBy;

	private Date dateCreated;
	
	private String updateBy;
	
	private Date dateUpdated;
}