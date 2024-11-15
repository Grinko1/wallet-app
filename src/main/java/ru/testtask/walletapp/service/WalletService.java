package ru.testtask.walletapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.testtask.walletapp.model.Wallet;
import ru.testtask.walletapp.repository.WalletRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletRepository repository;

    public List<Wallet> findAll(){
        return repository.findAll();
    }
}
