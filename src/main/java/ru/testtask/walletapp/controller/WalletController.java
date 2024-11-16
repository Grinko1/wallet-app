package ru.testtask.walletapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.testtask.walletapp.dto.RequestDto;
import ru.testtask.walletapp.dto.ResponseDto;
import ru.testtask.walletapp.service.WalletServiceImpl;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/")
public class WalletController {
    private final WalletServiceImpl service;

    @GetMapping
    public String mainInfo() {
        System.out.println(service.findAllWallets());
        return "Wallet get method!!";
    }
    @GetMapping("wallets/{WALLET_UUID}")
    public ResponseEntity<ResponseDto> getWalletBalance(@PathVariable("WALLET_UUID") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(service.findWalletById(id)));
    }
    @PostMapping("wallet")
    public ResponseEntity<String> updateBalance(@RequestBody RequestDto dto){
        service.updateWalletBalance(dto);
        return ResponseEntity.status(HttpStatus.OK).body("Баланс кошелька обновлён");
    }
}