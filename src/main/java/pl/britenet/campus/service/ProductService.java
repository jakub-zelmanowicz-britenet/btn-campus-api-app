package pl.britenet.campus.service;

import pl.britenet.campus.builder.ProductBuilder;
import pl.britenet.campus.obj.model.Product;
import pl.britenet.campus.service.database.DatabaseService;

import java.sql.SQLException;
import java.util.*;

public class ProductService {

    private final DatabaseService databaseService;

    public ProductService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public Optional<Product> retrieve(int id) {
        String sqlQuery = String.format("SELECT * FROM product WHERE id=%d", id);
        Product product = this.databaseService.performQuery(sqlQuery, resultSet -> {

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int categoryId = resultSet.getInt("categoryId");

                return new ProductBuilder(id)
                        .setName(name)
                        .setDescription(description)
                        .setCategoryId(categoryId)
                        .getProduct();
            }
            return null;

        });

        return Optional.of(product);
    }

    public Product create(Product product) {
        String dml = String.format("INSERT INTO product (name, description, categoryId) VALUES ('%s', '%s', %d)",
                product.getName(),
                product.getDescription(),
                product.getCategoryId());

        this.databaseService.performDML(dml);
        return product;
    }

    public Product update(Product product) {
        String dml = String.format("UPDATE product SET name='%s', description='%s', categoryId='%d' WHERE id=%d",
                product.getName(),
                product.getDescription(),
                product.getCategoryId(),
                product.getId());

        this.databaseService.performDML(dml);
        return product;
    }

    public void remove(int id) {
        String dml = String.format("DELETE FROM product WHERE id=%d", id);
        this.databaseService.performDML(dml);
    }
}
