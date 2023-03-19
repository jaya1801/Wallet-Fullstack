package com.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Override
    public WalletDto registerWallet(WalletDto wallet){
        return walletRepository.save(wallet);

    }

    @Override
    public WalletDto getWalletById(Integer walletId) throws WalletException {
        Optional<WalletDto> walletOptional = walletRepository.findById(walletId);
        if(walletOptional.isEmpty())
            throw new WalletException("Wallet Id does not exists.");
        return walletOptional.get();
    }


    @Override
    public WalletDto updateWallet(WalletDto wallet,Integer id) throws WalletException {
        Optional<WalletDto> walletOptional = this.walletRepository.findById(id);
        if(walletOptional.isEmpty())
            throw new WalletException("Wallet can not be updated!Id not found:"+wallet.getId());
        System.out.println(walletOptional.get());
         this.walletRepository.save(wallet);
         return wallet;
    }
    @Override
    public WalletDto deleteWalletById(Integer walletId) throws WalletException {
        Optional<WalletDto> walletOptional = walletRepository.findById(walletId);
        if(walletOptional.isEmpty())
            throw new WalletException("Wallet couldn't be deleted, Id not found: "+walletId);
        WalletDto foundWallet = walletOptional.get();
        this.walletRepository.delete(foundWallet);
        return foundWallet;
    }

    @Override
    public Double addFundsToWalletById(Integer walletId, Double amount) throws WalletException {
        //WalletDto wallet = getWalletById(walletId);
        Optional<WalletDto> walletOptional = walletRepository.findById(walletId);
        if(walletOptional.isEmpty())
            throw new WalletException("Funds con not be added to wallet!Id not found: "+walletId);
        Double balance = walletOptional.get().getBalance();
        //walletOptional.setBalance(balance+amount);
        walletOptional.get().setBalance(balance+amount);
        this.walletRepository.save(walletOptional.get());

        return balance+amount;

    }

    @Override
    public Double withdrawFundsFromWalletById(Integer walletId, Double amount) throws WalletException {

//        if(amount>getWalletById(walletById).getBalance())
//            throw new WalletException("Please enter amount lesser than your balance!");
        Optional<WalletDto> walletOptional = walletRepository.findById(walletId);
        if(amount>walletOptional.get().getBalance())
            throw new WalletException("Please enter amount lesser than your balance! Your current balance is: "+walletOptional.get().getBalance());


//        Double balance = getWalletById(walletById).getBalance() - amount;
//        return balance;
        Double balance = walletOptional.get().getBalance()-amount;
        walletOptional.get().setBalance(balance);
        this.walletRepository.save(walletOptional.get());
        return balance;
    }

    @Override
    public Boolean fundTransfer(Integer fromWalletId, Integer toWalletId, Double amount) throws WalletException {

        try{
            getWalletById(toWalletId);
            withdrawFundsFromWalletById(fromWalletId,amount);
            addFundsToWalletById(toWalletId,amount);

            return true;
        }
        catch (Exception e)
        {
            throw e;
        }

    }


    public List<WalletDto> getAllWalletsHavingName(String name){

        return this.walletRepository.findByName(name);
    }


    public List<WalletDto> getAllNamesContaining(String name){
        return this.walletRepository.findByNameContaining(name);
    }


    public List<WalletDto> getAllWalletsHavingBalanceOrderByNameDesc(Double minBalance, Double maxBalance){
        return this.walletRepository.findByBalanceBetweenOrderByNameDesc(minBalance,maxBalance);
    }


    public List<WalletDto> getAllWalletsHavingBalanceBetweenOrderByNameAsc(Double minBalance,Double maxBalance){
        return this.walletRepository.findByBalanceBetweenOrderByNameAsc(minBalance,maxBalance);
    }


    public List<WalletDto> getAllWalletsHavingBalanceBetweenOrderByBalanceDesc(Double minBalance,Double maxBalance){
        return this.walletRepository.findByBalanceBetweenOrderByBalanceDesc(minBalance,maxBalance);
    }


    public List<WalletDto> getAllWalletsHavingBalanceGreaterThanEqual(Double minBalance){
        return this.walletRepository.findByBalanceGreaterThanEqual(minBalance);
    }


    public List<WalletDto> getAllWalletsHavingIdOrName(Integer id, String name){
        return this.walletRepository.findByIdOrName(id,name);
    }


    public List<WalletDto> getAllWalletsHavingNameStartingWith( String name){
        return this.walletRepository.findByNameStartingWith(name);
    }


    public List<WalletDto> getAllWalletsHavingNameEndingWith(String name){
        return this.walletRepository.findByNameEndingWith(name);
    }


    public List<WalletDto> getAllWalletsHavingNameIsNot(String name){
        return this.walletRepository.findByNameIsNot(name);
    }

    @Override
    public List<WalletDto> getAllWallets(){

        return this.walletRepository.getAllWallets();
    }

    public List<WalletDto> getAllWalletsHavingNameLike(String name){

        return this.walletRepository.getAllWalletsHavingNameLike("%" + name + "%");
    }


    public List<WalletDto> getAllWalletsHavingOrderByName(){
        return this.walletRepository.getAllWalletsHavingOrderByName();
    }

    
    public List<WalletDto> getAllWalletsHavingBalanceOrderByBalance(){
        return this.walletRepository.getAllWalletsHavingBalanceOrderByBalance();
    }

    
    public List<WalletDto> getAllWalletsHavingIdGreaterThan(Integer id){
        return this.walletRepository.getAllWalletsHavingIdGreaterThan(id);
    }


}
