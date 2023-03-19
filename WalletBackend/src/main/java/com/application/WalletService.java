package com.application;

import java.util.List;
import java.util.Optional;

public interface WalletService {

    WalletDto registerWallet(WalletDto wallet);
    WalletDto getWalletById(Integer walletId) throws WalletException;
    WalletDto updateWallet(WalletDto wallet,Integer id)throws WalletException;
    WalletDto deleteWalletById(Integer walletId)throws WalletException;



    Double addFundsToWalletById(Integer walletId,Double amount)throws WalletException;
    Double withdrawFundsFromWalletById(Integer walletId,Double amount) throws WalletException;
    Boolean fundTransfer(Integer fromWalletId,Integer toWalletId,Double amount)throws WalletException;

    List<WalletDto> getAllWallets();


    List<WalletDto> getAllWalletsHavingIdGreaterThan(Integer id);

    List<WalletDto> getAllWalletsHavingBalanceOrderByBalance();

    List<WalletDto> getAllWalletsHavingOrderByName();

    List<WalletDto> getAllWalletsHavingNameLike(String s);



    List<WalletDto> getAllWalletsHavingName(String name);

    List<WalletDto> getAllNamesContaining(String name);

    List<WalletDto> getAllWalletsHavingBalanceOrderByNameDesc(Double minBalance, Double maxBalance);

    List<WalletDto> getAllWalletsHavingBalanceBetweenOrderByNameAsc(Double minBalance, Double maxBalance);

    List<WalletDto>  getAllWalletsHavingBalanceBetweenOrderByBalanceDesc(Double minBalance, Double maxBalance);

    List<WalletDto> getAllWalletsHavingBalanceGreaterThanEqual(Double minBalance);

    List<WalletDto> getAllWalletsHavingIdOrName(Integer id, String name);

    List<WalletDto> getAllWalletsHavingNameStartingWith(String name);

    List<WalletDto> getAllWalletsHavingNameEndingWith(String name);

    List<WalletDto> getAllWalletsHavingNameIsNot(String name);
}
