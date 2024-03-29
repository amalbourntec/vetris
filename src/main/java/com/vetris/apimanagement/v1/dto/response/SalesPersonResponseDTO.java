package com.vetris.apimanagement.v1.dto.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * ResponseDTO for salesPerson
 * 
 * @author ShekarReddySamreddy
 * 
 */

@Setter
@Getter
public class SalesPersonResponseDTO {
	private String id;
	private String code;
	private String fName;
	private String iName;
	private String name;
	private String address1;
	private String address2;
	private String city;
	private Integer stateId;
	private Integer countryId;
	private String zip;
	private String emailId;
	private String phoneNo;
	private String mobileNo;
	private String pacsUserId;
	private String pacsPassword;
	private String isActive;
	private String loginUserId;
	private String loginId;
	private String loginPwd;
	private String notificationPref;
	private String createdBy;
	private Date dateCreated;
	private String updateBy;
	private Date dateUpdated;

}
