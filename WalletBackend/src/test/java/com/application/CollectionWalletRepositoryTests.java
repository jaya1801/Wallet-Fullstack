package com.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CollectionWalletRepositoryTests {

    @Autowired
    private CollectionWalletRepository collectionWalletRepository;

    @Test
    public void createWalletTest() {
        WalletDto wallet = new WalletDto(1,"wallet1",1000.0);
        assertEquals(wallet.toString(),collectionWalletRepository.createWallet(wallet).toString());

    }

    @Test
    public void getWalletByIdTest(){
        WalletDto wallet = new WalletDto(1,"wallet1",1000.0);
        collectionWalletRepository.createWallet(wallet);
        assertEquals(wallet.toString(),collectionWalletRepository.getWalletById(1).toString());

    }

    @Test
    public void updateWalletTest(){
        WalletDto wallet = new WalletDto(1,"wallet1",1000.0);
        collectionWalletRepository.createWallet(wallet);
        assertEquals(wallet.toString(),collectionWalletRepository.updateWallet(wallet).toString());

    }

    @Test
    public void deleteWalletTest(){
        WalletDto wallet = new WalletDto(1,"wallet1",1000.0);
        collectionWalletRepository.createWallet(wallet);
        assertEquals(wallet.toString(),collectionWalletRepository.deleteWallet(1).toString());
    }

    @Test
    public void getAllWalletsTest(){
        List<WalletDto> walletList = new ArrayList<>();
        walletList.add(new WalletDto(1,"wallet1",1000.0));
        walletList.add(new WalletDto(2,"wallet2",2000.0));
        walletList.add(new WalletDto(3,"wallet3",3000.0));

        for (WalletDto wallet:walletList){
            collectionWalletRepository.createWallet(wallet);
        }

        List<WalletDto> walletList1 = collectionWalletRepository.getAllWallets();
        assertEquals(walletList.size(),walletList1.size());

        for(int i=0;i<walletList.size();i++)
        {
            assertEquals(walletList.get(i),walletList1.get(i));
        }

    }


}
