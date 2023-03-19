package com.application;

import java.util.List;

public interface CollectionWalletRepository {

    WalletDto createWallet(WalletDto newWallet);
    WalletDto getWalletById(Integer  walletId);
    WalletDto updateWallet(WalletDto wallet);
    WalletDto deleteWallet(Integer walletId);

    List<WalletDto> getAllWallets();
}
