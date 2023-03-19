package com.application;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<WalletDto,Integer> {

    List<WalletDto> findByName(String name);

    Optional<WalletDto> findById(Integer id);
    List<WalletDto> findByNameContaining(String name);
    List<WalletDto> findByBalanceBetweenOrderByNameDesc(Double minBalance, Double maxBalance);
    List<WalletDto> findByBalanceBetweenOrderByNameAsc(Double minBalance, Double maxBalance);
    List<WalletDto> findByBalanceBetweenOrderByBalanceDesc(Double minBalance, Double maxBalance);
    List<WalletDto> findByBalanceGreaterThanEqual(Double minBalance);
    List<WalletDto> findByIdOrName(Integer id, String name);
    List<WalletDto> findByNameStartingWith(String name);
    List<WalletDto> findByNameEndingWith(String name);
    List<WalletDto> findByNameIsNot(String name);

    // By writing JPQL Queries
    @Query("SELECT wallet FROM WalletDto wallet")
    List<WalletDto> getAllWallets();

    @Query("SELECT wallet FROM WalletDto wallet WHERE wallet.name LIKE :name")
    List<WalletDto> getAllWalletsHavingNameLike(String name);

    @Query("SELECT wallet FROM WalletDto wallet ORDER BY wallet.name ASC")
    List<WalletDto> getAllWalletsHavingOrderByName();

    @Query("SELECT wallet FROM WalletDto wallet ORDER BY wallet.balance ASC")
    List<WalletDto> getAllWalletsHavingBalanceOrderByBalance();

    @Query("SELECT wallet FROM WalletDto wallet WHERE wallet.id > :id")
    List<WalletDto> getAllWalletsHavingIdGreaterThan(Integer id);
}