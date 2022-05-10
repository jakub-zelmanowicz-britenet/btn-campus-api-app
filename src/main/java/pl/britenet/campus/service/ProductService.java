package pl.britenet.campus.service;

import pl.britenet.campus.obj.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductService {

    private final List<Product> products;

    public ProductService() {
        this.products = new ArrayList<>();
    }

    public Optional<Product> retrieve(int id) {
        return this.products.stream()
                .filter( product -> product.getId() == id )
                .findFirst();
    }

    public Product create(int id, String name, String description, int categoryId) {
        Product product = new Product(id);
        product.setName(name);
        product.setDescription(description);
        product.setCategoryId(categoryId);
        this.products.add(product);
        return product;
    }

    public Product update(int id, String name, String description, int categoryId) {
        Product product = this.retrieve(id).orElseThrow();
        product.setName(name);
        product.setDescription(description);
        product.setCategoryId(categoryId);
        return product;
    }

    public void remove(int id) {
        this.products.removeIf( product -> product.getId() == id );
    }
}
