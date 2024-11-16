package ru.testtask.walletapp.service;

import ru.testtask.walletapp.dto.RequestDto;
import ru.testtask.walletapp.model.Wallet;

import java.util.List;
import java.util.UUID;

public interface WalletService {
    Double findWalletById(UUID id);
    List<Wallet> findAllWallets();
    void updateWalletBalance(RequestDto wallet);

}
