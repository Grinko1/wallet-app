package ru.testtask.walletapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.testtask.walletapp.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
