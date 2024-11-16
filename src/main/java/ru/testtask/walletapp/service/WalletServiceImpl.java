package ru.testtask.walletapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.testtask.walletapp.dto.RequestDto;
import ru.testtask.walletapp.model.OperationType;
import ru.testtask.walletapp.model.Wallet;
import ru.testtask.walletapp.repository.WalletRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository repository;

    @Override
    public Double findWalletById(UUID id) {
        //Not found exp
        Wallet wallet = repository.findById(id).orElseThrow();
        return wallet.getBalance();
    }

    @Override
    public List<Wallet> findAllWallets() {
        return repository.findAll();
    }


    @Override
    @Transactional
    public void updateWalletBalance(RequestDto dto) {
        Wallet existingWallet = repository.findById(dto.getWalletId()).orElseThrow();
        Double currentBalance = existingWallet.getBalance();
        if (dto.getOperation() == OperationType.DEPOSIT){
            existingWallet.setBalance(currentBalance + dto.getAmount());
        }else{
            if (currentBalance - dto.getAmount() < 0){
                System.out.println("Не достаточно средств!" + " Баланс: "+ currentBalance);;
            }
            existingWallet.setBalance(currentBalance + dto.getAmount());
        }
            repository.save(existingWallet);
    }


}