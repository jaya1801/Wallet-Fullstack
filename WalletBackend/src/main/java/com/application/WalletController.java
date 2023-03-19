package com.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

//localhost:8090/swagger-ui.html
@RestController
@RequestMapping(value = "/v1") //
@CrossOrigin(origins = "http://localhost:4200/")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping("/")
    public String greet(){
        return "This is my Wallet Application!";
    }



    @PostMapping("/wallet")
    @ResponseStatus(value = HttpStatus.CREATED)
    public WalletDto addResource(@Valid @RequestBody WalletDto wallet) throws WalletException {
        System.out.println(wallet);
        return walletService.registerWallet(wallet);

    }

    @GetMapping("/wallet/{id}")
    public WalletDto getWalletById(@PathVariable Integer id) throws WalletException {
        WalletDto wallet = walletService.getWalletById(id);
        return wallet;
    }

    @PutMapping("/wallet/{id}")
    public WalletDto replaceResource(@Valid @RequestBody WalletDto wallet, @PathVariable("id") Integer id) throws WalletException
    {
        return walletService.updateWallet(wallet,id);
    }

    @GetMapping("wallets")
    public List<WalletDto> getAllEmployees(){
        return this.walletService.getAllWallets();
    }

    @PatchMapping("/wallet/{walletId}/addFund/{amount}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    Double addFundsToWalletById(@PathVariable("walletId") Integer walletId, @PathVariable("amount") Double amount) throws WalletException {
        return walletService.addFundsToWalletById(walletId, amount);
    }

    @PatchMapping("/wallet/{walletId}/withdrawFund/{amount}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    Double withdrawFundsFromWalletById(@PathVariable Integer walletId, @PathVariable Double amount) throws WalletException {
        return walletService.withdrawFundsFromWalletById(walletId, amount);
    }

    @PatchMapping("/wallet/{fromWalletId}/{toWalletId}/fundTransfer/{amount}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    Boolean fundTransfer(@PathVariable Integer fromWalletId, @PathVariable Integer toWalletId, @PathVariable Double amount) throws WalletException {
        return walletService.fundTransfer(fromWalletId, toWalletId, amount);
    }

    @DeleteMapping("/wallet/{walletId}")
    public WalletDto deleteWalletById(@PathVariable Integer walletId) throws WalletException
    {
        return walletService.deleteWalletById(walletId);
    }


    

    @GetMapping("/wallet/name/{name}")
    public List<WalletDto> getAllWalletsHavingName(@PathVariable("name") String name){
        return this.walletService.getAllWalletsHavingName(name);
    }

    @GetMapping("/wallet/contain/{name}")
    public List<WalletDto> getAllNamesContaining(@PathVariable("name") String name){
        return this.walletService.getAllNamesContaining(name);
    }

    @GetMapping("/wallet/balance/{minBalance}/{maxBalance}/name/{name}/nameDesc ")
    public List<WalletDto> getAllWalletsHavingBalanceOrderByNameDesc(@PathVariable("minBalance") Double minBalance, @PathVariable("maxBalance") Double maxBalance){
        return this.walletService.getAllWalletsHavingBalanceOrderByNameDesc(minBalance,maxBalance);
    }

    @GetMapping("/wallet/balance/{minBalance}/{maxBalance}/name/{name}/nameAsc")
    public List<WalletDto> getAllWalletsHavingBalanceBetweenOrderByNameAsc(@PathVariable("minBalance") Double minBalance, @PathVariable("maxBalance") Double maxBalance){
        return this.walletService.getAllWalletsHavingBalanceBetweenOrderByNameAsc(minBalance,maxBalance);
    }

    @GetMapping("/wallet/balance/balanceDesc/{minBalance}/{maxBalance}")
    public List<WalletDto> getAllWalletsHavingBalanceBetweenOrderByBalanceDesc(@PathVariable("minBalance") Double minBalance, @PathVariable("maxBalance") Double maxBalance){
        return this.walletService. getAllWalletsHavingBalanceBetweenOrderByBalanceDesc(minBalance,maxBalance);
    }

    @GetMapping("/wallet/balance/{minBalance}/greaterThanBalance")
    public List<WalletDto> getAllWalletsHavingBalanceGreaterThanEqual(@PathVariable("minBalance") Double minBalance){
        return this.walletService.getAllWalletsHavingBalanceGreaterThanEqual(minBalance);
    }

    @GetMapping("/wallet/id/{id}/name/{name}")
    public List<WalletDto> getAllWalletsHavingIdOrName(@PathVariable("id")Integer id, @PathVariable("name")String name){
        return this.walletService.getAllWalletsHavingIdOrName(id,name);
    }

    @GetMapping("/wallet/name/{name}/startingWith")
    public List<WalletDto> getAllWalletsHavingNameStartingWith(@PathVariable("name") String name){
        return this.walletService.getAllWalletsHavingNameStartingWith(name);
    }

    @GetMapping("/wallet/name/{name}/endingWith")
    public List<WalletDto> getAllWalletsHavingNameEndingWith(@PathVariable("name")String name){
        return this.walletService.getAllWalletsHavingNameEndingWith(name);
    }

    @GetMapping("/wallet/name/{name}/isNot")
    public List<WalletDto> getAllWalletsHavingNameIsNot(@PathVariable("name")String name){
        return this.walletService.getAllWalletsHavingNameIsNot(name);
    }

    @GetMapping("/custom/wallets")
    public List<WalletDto> getAllWallets(){

        return this.walletService.getAllWallets();
    }

    @GetMapping("/custom/wallets/name/{name}")
    public List<WalletDto> getAllWalletsHavingNameLike(@PathVariable("name") String name){

        return this.walletService.getAllWalletsHavingNameLike("%" + name + "%");
    }

    @GetMapping("/custom/wallets/name/sorted")
    public List<WalletDto> getAllWalletsHavingOrderByName(){
        return this.walletService.getAllWalletsHavingOrderByName();
    }

    @GetMapping("/custom/wallets/balance/sorted")
    public List<WalletDto> getAllWalletsHavingBalanceOrderByBalance(){
        return this.walletService.getAllWalletsHavingBalanceOrderByBalance();
    }

    @GetMapping("/custom/wallets/{id}/greaterThanId")
    public List<WalletDto> getAllWalletsHavingIdGreaterThan(@PathVariable("id")Integer id){
        return this.walletService.getAllWalletsHavingIdGreaterThan(id);
    }
    
}
