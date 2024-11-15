package ru.testtask.walletapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.testtask.walletapp.model.Product;
import ru.testtask.walletapp.repository.ProductRepository;

import java.util.List;

@SpringBootApplication
public class WalletappApplication implements CommandLineRunner {
	@Autowired
	private ProductRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(WalletappApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
//		Product product = new Product(null, "first product");
//		repository.save(product);
//		List<Product> products = repository.findAll();
//		System.out.println(products);

	}
}
