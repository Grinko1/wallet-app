package ru.testtask.walletapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.testtask.walletapp.service.ProductService;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping
    public String mainPage(){
        System.out.println(service.findAll());
        return "Works!!!!!!!!!!!!!!!!!";
    }

}
