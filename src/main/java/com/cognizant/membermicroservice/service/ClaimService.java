package com.cognizant.membermicroservice.service;

import com.cognizant.membermicroservice.dto.ClaimStatusDTO;
import com.cognizant.membermicroservice.dto.ViewBillsDTO;
import com.cognizant.membermicroservice.model.SubmitClaimRequest;
import org.springframework.http.ResponseEntity;

/**
 * This interface is to declare the viewBills method to get the bill details.
 *
 */

public interface ClaimService {

	/**
	 * @param memberId
	 * @param policyId
	 * @return ViewBillsDTO
	 */

	public ViewBillsDTO viewBills(int memberId, int policyId);


}