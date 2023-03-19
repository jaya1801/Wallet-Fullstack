package com.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CollectionWalletServiceIntegrationTests {

    @Autowired
    private CollectionWalletService collectionWalletService;
    @Test
    void registerWalletTest() throws WalletException {
        WalletDto wallet = new WalletDto(1,"wallet1",1000.0);
        assertEquals(wallet.toString(),this.collectionWalletService.registerWallet(wallet).toString());
    }

    @Test
    void getWalletByIdThrowsExceptionTest() throws WalletException {
        assertThrows(WalletException.class,()->this.collectionWalletService.getWalletById(1000));
    }

    @Test
    void getWalletByIdWithoutExceptionTest() throws WalletException {
        WalletDto wallet = new WalletDto(1,"wallet1",1000.0);
        this.collectionWalletService.registerWallet(wallet);
        assertEquals(wallet.toString(),this.collectionWalletService.getWalletById(wallet.getId()).toString());
    }

    @Test
    void updateWalletTest() throws WalletException{
        WalletDto wallet = new WalletDto(1,"wallet1",1000.0);
        this.collectionWalletService.registerWallet(wallet);
        assertEquals(wallet.toString(),this.collectionWalletService.updateWallet(wallet).toString());
    }



    @Test
    void deleteWalletByIdThrowsExceptionTest() throws WalletException{
        assertThrows(WalletException.class,()->this.collectionWalletService.deleteWalletById(1000));
    }

    @Test
    void deleteWalletByIdWithoutExceptionTest() throws WalletException {
        WalletDto wallet = new WalletDto(1,"wallet1",1000.0);
        this.collectionWalletService.registerWallet(wallet);
        assertEquals(wallet.toString(),this.collectionWalletService.deleteWalletById(wallet.getId()).toString());
    }

    @Test
    void addFundsToWalletByIdWithoutExceptionTest() throws WalletException{
        WalletDto wallet = new WalletDto(1,"wallet1",1000.0);
        this.collectionWalletService.registerWallet(wallet);

        assertEquals(1500.0,this.collectionWalletService.addFundsToWalletById(1, 500.0));
    }

    @Test
    void addFundsToWalletByIdThrowsExceptionTest() throws WalletException{
        assertThrows(WalletException.class,()->this.collectionWalletService.addFundsToWalletById(1000, 500.0));
    }

    @Test
    void withdrawFundsFromWalletByIdThrowsExceptionTest() throws Exception {
        assertThrows(Exception.class,()->this.collectionWalletService.withdrawFundsFromWalletById(1000, 500.0));
    }

    @Test
    void withdrawFundsFromWalletByIdLessAmountTest() throws WalletException {
        WalletDto wallet = new WalletDto(1,"wallet1",10.0);
        this.collectionWalletService.registerWallet(wallet);

        assertThrows(WalletException.class,()->this.collectionWalletService.withdrawFundsFromWalletById(1, 500.0));
    }

    @Test
    void withdrawFundsFromWalletByIdWithoutExceptionTest() throws WalletException{
        WalletDto wallet = new WalletDto(1,"wallet1",1000.0);
        this.collectionWalletService.registerWallet(wallet);

        assertEquals(500.0,this.collectionWalletService.withdrawFundsFromWalletById(1, 500.0));
    }

    @Test
    void fundTransferThrowsExceptionFromIdTest() throws Exception {
        assertThrows(Exception.class,()->this.collectionWalletService.fundTransfer(1000,2,1000.0));
    }

    @Test
    void fundTransferThrowsExceptionToIdTest() throws WalletException {
        assertThrows(WalletException.class,()->this.collectionWalletService.fundTransfer(1,2000,1000.0));
    }

    @Test
    void fundTransferThrowsExceptionLessBalanceTest() throws WalletException{
        WalletDto wallet1 = new WalletDto(1,"wallet1",10.0);
        WalletDto wallet2 = new WalletDto(2,"wallet2",500.0);
        this.collectionWalletService.registerWallet(wallet1);
        this.collectionWalletService.registerWallet(wallet2);

        assertThrows(WalletException.class,()->this.collectionWalletService.fundTransfer(1,2,1000.0));
    }

    @Test
    void fundTransferWithoutExceptionTest() throws WalletException{
        WalletDto wallet1 = new WalletDto(1,"wallet1",1000.0);
        WalletDto wallet2 = new WalletDto(2,"wallet2",500.0);
        this.collectionWalletService.registerWallet(wallet1);
        this.collectionWalletService.registerWallet(wallet2);

        assertEquals(true,this.collectionWalletService.fundTransfer(1, 2,500.0));
    }

}
