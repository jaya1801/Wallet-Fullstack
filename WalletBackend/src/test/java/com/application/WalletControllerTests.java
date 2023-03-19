package com.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WalletControllerTests {
    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach //("addFundsToWalletByIdTest")
    public void setUp() {
        restTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    }

    @BeforeEach
    public void init(){
        this.restTemplate.postForObject("http://localhost:" + port + "/v1/wallet",new WalletDto(1,"Wallet1",1000.0), WalletDto.class);
    }

//    @Test
//    public void addFundsToWalletByIdTest() throws Exception {
//        this.restTemplate.postForObject("http://localhost:" + port + "/v1/wallet",new WalletDto(2,"Wallet1",1000.0), WalletDto.class);
//
//        Double amount = 500.0;
//        Double responseEntity = this.restTemplate.patchForObject("http://localhost:" + port + "/v1/wallet/2/addFund/" + amount,null , Double.class);
//        assertEquals(1500.0, responseEntity);
//    }
}
