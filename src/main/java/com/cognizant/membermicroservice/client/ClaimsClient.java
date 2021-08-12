package com.cognizant.membermicroservice.client;

import com.cognizant.membermicroservice.model.SubmitClaimRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import com.cognizant.membermicroservice.dto.ClaimStatusDTO;

/**
 * 
 *
 * This class communicates with claimsMicroservice to verify the token. using
 * Feign client With provided URL and name of microservice it interact with that
 * microservice.
 *
 */
@Component
@FeignClient(name = "claims-microservice", url = "http://localhost:8090/claims")
public interface ClaimsClient {

	@GetMapping("/getClaimStatus/{claimId}/{policyId}/{memberId}")
	 ResponseEntity<ClaimStatusDTO> getClaimStatus(@PathVariable int claimId, @PathVariable int policyId,
			@PathVariable int memberId, @RequestHeader(value = "Authorization", required = false) String token);

	@PostMapping("/submitClaim")
	 ResponseEntity<ClaimStatusDTO> submitClaim(@RequestBody SubmitClaimRequest submitClaimRequest,
												@RequestHeader(value = "Authorization", required = false) String token);

}
