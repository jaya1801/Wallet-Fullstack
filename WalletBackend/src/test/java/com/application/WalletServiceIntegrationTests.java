package com.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class WalletServiceIntegrationTests {


    @Autowired
    private WalletService walletService;

    @Transactional(readOnly = true)
    @Test
    void registerWalletTest() throws WalletException {
        WalletDto wallet = new WalletDto(null,"wallet1",1000.0);
        WalletDto wallet1 = this.walletService.registerWallet(wallet);
        assertEquals(wallet.toString(),wallet1.toString());
    }

    @Transactional(readOnly = true)
    @Test
    void getWalletByIdThrowsExceptionTest() throws WalletException {
        assertThrows(WalletException.class,()->this.walletService.getWalletById(1000));
    }


    @Transactional(readOnly = true)
    @Test
    void getWalletByIdWithoutExceptionTest() throws WalletException {
        WalletDto wallet = new WalletDto(null,"wallet1",1000.0);
        this.walletService.registerWallet(wallet);
        assertEquals(wallet.toString(),this.walletService.getWalletById(wallet.getId()).toString());
    }

    @Transactional(readOnly = true)
    @Test
    void updateWalletTest() throws WalletException {
        WalletDto wallet = new WalletDto(null,"wallet1",1000.0);
        this.walletService.registerWallet(wallet);
        assertEquals(wallet.toString(),this.walletService.updateWallet(wallet, wallet.getId()).toString());
    }

    @Transactional(readOnly = true)
    @Test
    void deleteWalletByIdThrowsExceptionTest() throws WalletException {
        assertThrows(WalletException.class,()->this.walletService.deleteWalletById(1000));
    }

    @Transactional(readOnly = true)
    @Test
    void deleteWalletByIdWithoutExceptionTest() throws WalletException {
        WalletDto wallet = new WalletDto(null,"wallet1",1000.0);
        this.walletService.registerWallet(wallet);
        assertEquals(wallet.toString(),this.walletService.deleteWalletById(wallet.getId()).toString());
    }

    @Transactional(readOnly = true)
    @Test
    void addFundsToWalletByIdWithoutExceptionTest() throws WalletException {
        WalletDto wallet = new WalletDto(null,"wallet1",1000.0);
        this.walletService.registerWallet(wallet);

        assertEquals(1500.0,this.walletService.addFundsToWalletById(wallet.getId(), 500.0));
    }


    @Transactional(readOnly = true)
    @Test
    void addFundsToWalletByIdThrowsExceptionTest() throws WalletException {
        assertThrows(WalletException.class,()->this.walletService.addFundsToWalletById(1000, 500.0));
    }

    @Transactional(readOnly = true)
    @Test
    void withdrawFundsFromWalletByIdThrowsExceptionTest() throws WalletException, Exception {
        assertThrows(Exception.class,()->this.walletService.withdrawFundsFromWalletById(1000, 500.0));
    }

    @Transactional(readOnly = true)
    @Test
    void withdrawFundsFromWalletByIdLessAmountTest() throws WalletException {
        WalletDto wallet = new WalletDto(null,"wallet1",10.0);
        this.walletService.registerWallet(wallet);

        assertThrows(WalletException.class,()->this.walletService.withdrawFundsFromWalletById(wallet.getId(), 500.0));
    }

    @Transactional(readOnly = true)
    @Test
    void withdrawFundsFromWalletByIdWithoutExceptionTest() throws WalletException {
        WalletDto wallet = new WalletDto(null,"wallet1",1000.0);
        this.walletService.registerWallet(wallet);

        assertEquals(500.0,this.walletService.withdrawFundsFromWalletById(wallet.getId(), 500.0));
    }

    @Transactional(readOnly = true)
    @Test
    void fundTransferThrowsExceptionFromIdTest() throws WalletException, Exception {
        WalletDto wallet =  new WalletDto(null,"wallet1",1000.0);
        assertThrows(Exception.class,()->this.walletService.fundTransfer(1000,wallet.getId(),1000.0));
    }

    @Transactional(readOnly = true)
    @Test
    void fundTransferThrowsExceptionToIdTest() throws WalletException, Exception {
        WalletDto wallet =  new WalletDto(null,"wallet1",1000.0);
        assertThrows(Exception.class,()->this.walletService.fundTransfer(wallet.getId(),2000,1000.0));
    }

    @Transactional(readOnly = true)
    @Test
    void fundTransferThrowsExceptionLessBalanceTest() throws WalletException {
        WalletDto wallet1 = new WalletDto(null,"wallet1",10.0);
        WalletDto wallet2 = new WalletDto(null,"wallet2",500.0);
        this.walletService.registerWallet(wallet1);
        this.walletService.registerWallet(wallet2);

        assertThrows(WalletException.class,()->this.walletService.fundTransfer(wallet1.getId(),wallet2.getId(),1000.0));
    }

    @Transactional(readOnly = true)
    @Test
    void fundTransferWithoutExceptionTest() throws WalletException {
        WalletDto wallet1 = new WalletDto(null,"wallet1",1000.0);
        WalletDto wallet2 = new WalletDto(null,"wallet2",500.0);
        this.walletService.registerWallet(wallet1);
        this.walletService.registerWallet(wallet2);

        assertEquals(true,this.walletService.fundTransfer(wallet1.getId(), wallet2.getId(), 500.0));
    }
}
