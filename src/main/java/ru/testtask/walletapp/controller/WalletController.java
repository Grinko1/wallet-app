package ru.testtask.walletapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.testtask.walletapp.service.WalletService;

@RestController
@RequiredArgsConstructor
public class WalletController {
    private final WalletService service;

    @GetMapping
    public String mainInfo(){
        System.out.println(service.findAll());
        return "Wallet get method!!";
    }
}
