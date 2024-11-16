package ru.testtask.walletapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.testtask.walletapp.dto.RequestDto;
import ru.testtask.walletapp.dto.ResponseDto;
import ru.testtask.walletapp.model.Wallet;
import ru.testtask.walletapp.service.WalletServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class WalletController {
    private final WalletServiceImpl service;

    @GetMapping("wallets")
    public ResponseEntity<List<Wallet>> getAllWallets() {
       return ResponseEntity.status(HttpStatus.OK).body(service.findAllWallets());
    }
    @GetMapping("wallets/{WALLET_UUID}")
    public ResponseEntity<ResponseDto> getWalletBalance(@PathVariable("WALLET_UUID") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(service.findWalletById(id)));
    }
    @PostMapping("wallet")
    public ResponseEntity<String> updateBalance(@Valid @RequestBody RequestDto dto){
        return ResponseEntity.status(HttpStatus.OK).body( service.updateWalletBalance(dto));
    }
}