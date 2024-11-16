package ru.testtask.walletapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.testtask.walletapp.dto.RequestDto;
import ru.testtask.walletapp.exceptions.InsufficientFundsException;
import ru.testtask.walletapp.exceptions.InvalidOperationTypeException;
import ru.testtask.walletapp.exceptions.NotFoundException;
import ru.testtask.walletapp.model.OperationType;
import ru.testtask.walletapp.model.Wallet;
import ru.testtask.walletapp.repository.WalletRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository repository;

    @Override
    public Double findWalletById(UUID id) {
        Wallet wallet = repository.findById(id).orElseThrow(()->new NotFoundException("Кошелек ", "WalletId", id));
        return wallet.getBalance();
    }

    @Override
    public List<Wallet> findAllWallets() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public String updateWalletBalance(RequestDto dto) {
        if (dto.getOperationType() != OperationType.DEPOSIT && dto.getOperationType() != OperationType.WITHDRAW){
            throw new InvalidOperationTypeException("Неверный тип операции! Допустимые типы: 'DEPOSIT' или 'WITHDRAW'.");

        }
        String response;
        Wallet existingWallet = repository.findById(dto.getValletId()).orElseThrow(()->new NotFoundException("Кошелек ", "WalletId", dto.getValletId()));
        Double currentBalance = existingWallet.getBalance();
        if (dto.getOperationType() == OperationType.DEPOSIT){
            existingWallet.setBalance(currentBalance + dto.getAmount());
            response= "Пополнение на %d. Баланс: %s".formatted(dto.getAmount(), currentBalance);
        }else{
            if (currentBalance - dto.getAmount() < 0){
                throw new InsufficientFundsException("Недостаточно средств на счете. Баланс: " + currentBalance);
            }
            existingWallet.setBalance(currentBalance - dto.getAmount());
            response = "Снятие %d. Баланс: %s".formatted(dto.getAmount(), currentBalance);
        }
            repository.save(existingWallet);
        return response;
    }


}