package com.cognizant.membermicroservice.service;

import com.cognizant.membermicroservice.client.ClaimsClient;
import com.cognizant.membermicroservice.dto.ClaimStatusDTO;
import com.cognizant.membermicroservice.model.SubmitClaimRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cognizant.membermicroservice.dto.ViewBillsDTO;
import com.cognizant.membermicroservice.exception.PolicyNotFoundException;
import com.cognizant.membermicroservice.model.MemberPremium;
import com.cognizant.membermicroservice.repository.PremiumRepository;

@Service
/**
 * This class is used to load the bill details from the database.
 *
 */
public class ClaimServiceImpl implements ClaimService {
	@Autowired
	private ClaimsClient claimsClient;

	@Autowired
	private PremiumRepository premiumRepository;

	@Autowired
	private ViewBillsDTO viewBillsDto;

	@Autowired
	private MemberPremium memberPremium;
	private  ClaimStatusDTO claimStatusDTO;

	/**
	 * 
	 * @param memberId
	 * @param policyId
	 * @return viewBillsDto
	 * @throws PolicyNotFoundException
	 */

	@Override
	public ViewBillsDTO viewBills(int memberId, int policyId) throws PolicyNotFoundException {
		MemberPremium premium = premiumRepository.findByMemberIdAndPolicyId(memberId,policyId);

		if (premium!=null) {
			viewBillsDto.setPaidDate(premium.getPaidDate());
			viewBillsDto.setPremium(premium.getPremium());
			viewBillsDto.setLatePayment(premium.isLatePayment());
			viewBillsDto.setLatePaymentCharges(premium.getLatePaymentCharges());
			viewBillsDto.setDueDate(premium.getDueDate());
		} else {
			throw new PolicyNotFoundException("policy Not Found!!!");
		}
		return viewBillsDto;

	}


}
