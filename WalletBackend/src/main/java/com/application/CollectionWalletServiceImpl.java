package com.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionWalletServiceImpl implements CollectionWalletService {
    @Autowired
    private CollectionWalletRepository collectionWalletRepository;


    @Override
    public WalletDto registerWallet(WalletDto wallet) throws WalletException {
        return collectionWalletRepository.createWallet(wallet);

    }

    @Override
    public WalletDto getWalletById(Integer walletId) throws WalletException {
        WalletDto foundWallet = collectionWalletRepository.getWalletById(walletId);
        if(foundWallet == null)
            throw new WalletException("Wallet Id does not exists.");
        return foundWallet;
    }


    @Override
    public WalletDto updateWallet(WalletDto wallet) throws WalletException {
        return collectionWalletRepository.updateWallet(wallet);


    }
    @Override
    public WalletDto deleteWalletById(Integer walletId) throws WalletException {
        WalletDto foundWallet = collectionWalletRepository.getWalletById(walletId);
        if(foundWallet==null)
            throw new WalletException("Wallet couldn't be deleted, Id not found: "+walletId);
        return collectionWalletRepository.deleteWallet(walletId);

    }

    @Override
    public Double addFundsToWalletById(Integer walletId, Double amount) throws WalletException {
        WalletDto wallet = getWalletById(walletId);
        Double balance = wallet.getBalance();
        wallet.setBalance(balance+amount);

        return balance+amount;

    }

    @Override
    public Double withdrawFundsFromWalletById(Integer walletById, Double amount) throws WalletException {

        if(amount>getWalletById(walletById).getBalance())
            throw new WalletException("Please enter amount lesser than your balance!");


        Double balance = getWalletById(walletById).getBalance() - amount;
        return balance;
    }

    @Override
    public Boolean fundTransfer(Integer fromWalletId, Integer toWalletId, Double amount) throws WalletException {

        try{
            addFundsToWalletById(toWalletId,amount);
            withdrawFundsFromWalletById(fromWalletId,amount);

            return true;
        }
        catch (Exception e)
        {
            throw e;
        }

    }

    @Override
    public List<WalletDto> getAllWallets() {
        return collectionWalletRepository.getAllWallets();
    }
}
