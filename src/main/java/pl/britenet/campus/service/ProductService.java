package pl.britenet.campus.service;

import pl.britenet.campus.builder.ProductBuilder;
import pl.britenet.campus.obj.model.Product;
import pl.britenet.campus.service.database.DatabaseService;

import java.sql.SQLException;
import java.util.*;

public class ProductService {

    private final DatabaseService databaseService;

    private final Map<Integer, Product> products;

    public ProductService(DatabaseService databaseService) {
        this.databaseService = databaseService;
        this.products = new HashMap<>();
    }

    public Optional<Product> retrieve(int id) {
        Product product = this.databaseService.performQuery("SELECT * FROM product WHERE id=" + id, resultSet -> {

            try {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int categoryId = resultSet.getInt("categoryId");

                return new ProductBuilder(id)
                        .setName(name)
                        .setDescription(description)
                        .setCategoryId(categoryId)
                        .getProduct();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        });

        return Optional.of(product);
    }

    public Product create(Product product) {
        this.products.put(product.getId(), product);
        return product;
    }

    public Product update(Product product) {
        if (this.products.containsKey(product.getId())) {
            this.products.replace(product.getId(), product);
            return product;
        }
        else {
            throw new IllegalStateException("No such element under the given ID");
        }
    }

    public void remove(int id) {
        this.products.remove(id);
    }
}
