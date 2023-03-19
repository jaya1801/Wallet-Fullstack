package com.application;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CollectionWalletRepositoryImpl implements CollectionWalletRepository {

    private static Map<Integer,WalletDto> walletDtoMap = new HashMap<>();


    @Override
    public WalletDto createWallet(WalletDto newWallet) {
        walletDtoMap.put(newWallet.getId(),newWallet);
        return newWallet;
    }



    @Override
    public WalletDto getWalletById(Integer walletId) {
        return walletDtoMap.get(walletId);
    }

    @Override
    public WalletDto updateWallet(WalletDto wallet) {
        return walletDtoMap.replace(wallet.getId(),wallet);

    }

    @Override
    public WalletDto deleteWallet(Integer walletId) {
        return walletDtoMap.remove(walletId);

    }

    @Override
    public List<WalletDto> getAllWallets(){
        List<WalletDto> walletList = new ArrayList<>();
        for(Map.Entry<Integer,WalletDto> wallet: walletDtoMap.entrySet()){
            walletList.add(wallet.getValue());
        }
        return walletList;

    }
}
