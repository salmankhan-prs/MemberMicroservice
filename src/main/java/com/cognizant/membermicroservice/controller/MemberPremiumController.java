package com.cognizant.membermicroservice.controller;

import com.cognizant.membermicroservice.model.SubmitClaimRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cognizant.membermicroservice.client.AuthClient;
import com.cognizant.membermicroservice.client.ClaimsClient;
import com.cognizant.membermicroservice.dto.ClaimStatusDTO;
import com.cognizant.membermicroservice.dto.ViewBillsDTO;
import com.cognizant.membermicroservice.service.ClaimService;

import feign.FeignException;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * This class is handling all the end points for MemberMicroservice. This
 * controller has mappings as- getmapping-viewBills()
 * get mapping-getClaimStatus()
 * post mapping-submitClaim()
 *
 */

@Slf4j
@Api(value = "Member Premium Controller")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MemberPremiumController {

	@Autowired
	private ClaimService claimServiceImpl;

	@Autowired
	private AuthClient authClient;

	@Autowired
	private ClaimsClient claimsClient;
	private ResponseEntity<ClaimStatusDTO> claimResponse;
	private ResponseEntity<ClaimStatusDTO> claimStatus;

	/**
	 * 
	 * @param token    - used to verify the token with jwtAuthentication
	 *                 microservice
	 * @param memberId - used to find the member of particular memberId
	 * @param policyId - used to find the policy of the particular member
	 * @return ResponseEntity<Response>
	 * 
	 *         returns the details of the members having the particular memberId and
	 *         policyId.
	 *
	 */

	@GetMapping(value = "/viewBills/{memberId}/{policyId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> viewBills(@PathVariable Integer memberId, @PathVariable Integer policyId,
			@RequestHeader(value = "Authorization", required = false) String token) {
		log.info("Start - viewBills()");
		
		try {
			if (!authClient.getValidity(token).getValid()) {
				
				return new ResponseEntity<>("Token is either expired or invalid...", HttpStatus.FORBIDDEN);
			}

		} catch (FeignException e) {
			return new ResponseEntity<>("Token is either expired or invalid...", HttpStatus.FORBIDDEN);

		}
		log.info("End - viewBills()");
		return new ResponseEntity<ViewBillsDTO>(claimServiceImpl.viewBills(memberId, policyId), HttpStatus.OK);
	}

	/**
	 * 
	 * @param token    - used to verify the token with jwtAuthentication
	 *                 microservice
	 * @param claimId  - used to get the claim of the particular claimId
	 * @param policyId - used to find the policy of the particular member
	 * @param memberId - used to find the member of particular memberId
	 * @return ResponseEntity<Response> - returns the status of the claim from the
	 *         claimId, policyId and memberId.
	 *
	 */

	@GetMapping(value = "/getClaimStatus/{claimId}/{policyId}/{memberId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getClaimStatus(@PathVariable int claimId, @PathVariable int policyId,
			@PathVariable int memberId, @RequestHeader(value = "Authorization", required = false) String token) {
		log.info("Start - getClaimStatus()");
		
		try {
			if (!authClient.getValidity(token).getValid()) {

				return new ResponseEntity<>("Token is either expired or invalid...", HttpStatus.BAD_REQUEST);
			}
			log.info("token validated");

		} catch (FeignException e) {
			return new ResponseEntity<>("Token is either expired or invalid...", HttpStatus.BAD_REQUEST);

		}
        try {
			 claimStatus = claimsClient.getClaimStatus(claimId, policyId, memberId, token);
		}
		catch (FeignException feignException){
			return new ResponseEntity<>("request is not success.Please enter valid  data and try again",HttpStatus.BAD_REQUEST);
		}

		log.info("End - getClaimStatus()");
		return claimStatus;

	}

	/**
	 * 
	 * @param token         - used to verify the token with jwtAuthentication
	 *                      microservice

	 *  SubmitClaimRequest - used to take the details
	 * @return ResponseEntity<Response> - returns the status of the claim and its
	 *         description after submitting the claim.
	 *
	 */

	@PostMapping("/submitClaim")
	public ResponseEntity<?> submitClaim(@RequestBody SubmitClaimRequest submitClaimRequest,
										 @RequestHeader(value = "Authorization", required = false) String token) {
		log.info("Start - submitClaim()");
		try {
			if (!authClient.getValidity(token).getValid()) {

				return new ResponseEntity<>("Token is either expired or invalid...", HttpStatus.BAD_REQUEST);
			}

		} catch (FeignException e) {
			return new ResponseEntity<>("Token is either expired or invalid...", HttpStatus.BAD_REQUEST);

		}

		try {
			 claimResponse = claimsClient.submitClaim(submitClaimRequest, token);
		}
		catch (FeignException feignException){
			return new ResponseEntity<>("request is not success.Please enter valid  data and try again",HttpStatus.BAD_REQUEST);
		}
		log.info("End - submitClaim()");
		return claimResponse;
	}

}
