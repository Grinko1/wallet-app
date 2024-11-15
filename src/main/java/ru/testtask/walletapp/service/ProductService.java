package ru.testtask.walletapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.testtask.walletapp.model.Product;
import ru.testtask.walletapp.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    public List<Product> findAll(){
        return repository.findAll();
    }
    public String save(Product product){
        repository.save(product);
        return "saved";
    }
}
