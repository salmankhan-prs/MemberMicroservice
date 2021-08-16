package com.cognizant.membermicroservice.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
 class SubmitClaimRequestTest {
@Mock
private SubmitClaimRequest submitClaimRequest;

    @Test
    @DisplayName("Checking if SubmitClaimRequest class is loading or not.")
    void SubmitClaimRequestIsLoadedOrNot() {
        assertThat(submitClaimRequest).isNotNull();
    }


    @DisplayName("Checking if SubmitClaimRequest class is responding correctly or not.")
    @Test
    void testingSubmitClaimRequest() {
        submitClaimRequest =new SubmitClaimRequest(1,2,3,100,200,290,100);
        submitClaimRequest.setClaimId(1);
        submitClaimRequest.setPolicyId(2);
        submitClaimRequest.setMemberId(3);
        submitClaimRequest.setProviderId(200);
        submitClaimRequest.setBenefitId(100);
        submitClaimRequest.setTotalAmount(290);
        submitClaimRequest.setClaimedAmount(100);



        assertEquals(1,submitClaimRequest.getClaimId());
        assertEquals(2,submitClaimRequest.getPolicyId());
        assertEquals(3,submitClaimRequest.getMemberId());
        assertEquals(200,submitClaimRequest.getProviderId());
        assertEquals(100,submitClaimRequest.getBenefitId());
        assertEquals(290,submitClaimRequest.getTotalAmount());
        assertEquals(100,submitClaimRequest.getClaimedAmount());

    }


}


