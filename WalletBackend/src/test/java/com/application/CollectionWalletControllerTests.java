package com.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CollectionWalletControllerTests {

    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void init(){
        this.restTemplate.postForObject("http://localhost:" + port + "/v1/wallet",new WalletDto(1,"Wallet1",1000.0),WalletDto.class);
    }

    @Test
    public void getWalletByIdTests() throws Exception{
        WalletDto foundWallet =this.restTemplate.getForObject("http://localhost:" + port + "/v1/wallet/1", WalletDto.class);
        assertEquals("Wallet1",foundWallet.getName());

    }

    @Test
    public void getWalletByIdExceptionTest() throws Exception {
        String walletExceptionMessage =this.restTemplate.getForObject("http://localhost:" + port + "/v1/wallet/2", String.class);
        assertEquals("Wallet Id does not exists.",walletExceptionMessage);
    }

    @Test
    public void addResourceTest() throws Exception{
        WalletDto wallet = new WalletDto(2,"Wallet2",2000.0);
        WalletDto foundWallet = this.restTemplate.postForObject("http://localhost:" + port + "/v1/wallet",wallet ,WalletDto.class);
        assertEquals(wallet.toString(),foundWallet.toString());
    }

    @Test
    public void replaceResourceTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        WalletDto wallet = new WalletDto(1,"Wallet1",1000.0);
        HttpEntity<WalletDto> httpEntity = new HttpEntity<WalletDto>(wallet, headers);

        ResponseEntity<WalletDto> responseEntity = this.restTemplate.exchange("http://localhost:" + port + "/v1/wallet", HttpMethod.PUT, httpEntity ,WalletDto.class);
        assertEquals(wallet.toString(),responseEntity.getBody().toString());
    }


//    @Test
//    public void addFundsToWalletByIdTest() throws Exception{
//        Double amount = 500.0;
//        Double newBal = this.restTemplate.patchForObject("http://localhost:" + port + "v1/wallet/1/addFund/"+amount,null,Double.class);
//        assertEquals(1500.0,newBal);
//    }
//
//    @Test
//    public void withdrawFundsByIdTest() throws Exception{
//        Double amount = 200.0;
//        Double newBal = this.restTemplate.patchForObject("http://localhost:" + port + "/v1/wallet/1/withdraw/" + amount, null, Double.class);
//        assertEquals(amount,newBal);
//
//    }
//
//    @Test
//    public void fundTransferTest() throws Exception{
//        Double amount = 200.0;
//        Double newBal = this.restTemplate.patchForObject("http://localhost:" + port + "/v1/wallet/1" + amount, null, Double.class);
//        assertEquals(amount,newBal);
//    }

}
