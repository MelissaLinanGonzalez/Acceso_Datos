package cursoSpringBoot.service;

import cursoSpringBoot.domain.Product;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Lazy
@Service
@ConditionalOnProperty(name = "service.products", havingValue = "list")
public class ProductsServiceImpl implements ProductService{

    public ProductsServiceImpl(){
        System.out.println("Instancia de la clase ProductsServiceImpl");
    }

    List<Product> products = new ArrayList<>(Arrays.asList(
            new Product(1, "Portatil", 805.45, 10),
            new Product(2, "S24 Ultra", 1855.50, 5),
            new Product(3, "Monitor Acer", 100.00, 32),
            new Product(4, "Teclado Logi", 25.30, 22)
    ));

    public List<Product> getProducts(){
        return products;
    }



}
