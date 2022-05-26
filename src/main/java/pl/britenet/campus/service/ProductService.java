package pl.britenet.campus.service;

import pl.britenet.campus.builder.CategoryBuilder;
import pl.britenet.campus.builder.ProductBuilder;
import pl.britenet.campus.obj.model.Category;
import pl.britenet.campus.obj.model.Product;
import pl.britenet.campus.service.database.DatabaseService;

import java.sql.SQLException;
import java.util.*;

public class ProductService {

    private final DatabaseService databaseService;

    public ProductService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public List<Product> retrieveAll() {
        String sqlQuery = "SELECT * FROM product";

        try {
            return this.databaseService.performQuery(sqlQuery, resultSet -> {

                List<Product> productList = new ArrayList<>();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String description = resultSet.getString("description");
                    int categoryId = resultSet.getInt("categoryId");

                    Product product = new ProductBuilder(id)
                            .setName(name)
                            .setDescription(description)
                            .setCategoryId(categoryId)
                            .getProduct();

                    productList.add(product);
                }

                return productList;

            });
        } catch (RuntimeException exception) {
            System.out.println("ERROR!");
            System.out.println(exception.getMessage());

            return new ArrayList<>();
        }
    }

    public Optional<Product> retrieve(int id) {
        String sqlQuery = String.format("SELECT p.id, p.name, p.description, p.categoryId, c.name AS categoryName FROM product p INNER JOIN category c ON p.categoryId = c.id WHERE p.id=%d", id);

        try {
            Product product = this.databaseService.performQuery(sqlQuery, resultSet -> {

                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String description = resultSet.getString("description");
                    int categoryId = resultSet.getInt("categoryId");
                    String categoryName = resultSet.getString("categoryName");

                    Category category = new CategoryBuilder(categoryId)
                            .setName(categoryName)
                            .getCategory();

                    return new ProductBuilder(id)
                            .setName(name)
                            .setDescription(description)
                            .setCategoryId(categoryId)
                            .setCategory(category)
                            .getProduct();
                }
                return null;

            });

            return Optional.of(product);

        } catch (RuntimeException exception) {
            System.out.println("ERROR!");
            System.out.println(exception.getMessage());

            return Optional.empty();
        }
    }

    public Product create(Product product) {
        String dml = String.format("INSERT INTO product (name, description, categoryId) VALUES ('%s', '%s', %d)",
                product.getName(),
                product.getDescription(),
                product.getCategoryId());

        try {
            this.databaseService.performDML(dml);
        } catch (RuntimeException exception) {

        }

        return product;
    }

    public Product update(Product product) {
        String dml = String.format("UPDATE product SET name='%s', description='%s', categoryId='%d' WHERE id=%d",
                product.getName(),
                product.getDescription(),
                product.getCategoryId(),
                product.getId());

        try {
            this.databaseService.performDML(dml);
        } catch (RuntimeException exception) {
            System.out.println("ERROR!");
            System.out.println(exception.getMessage());
        }

        return product;
    }

    public void remove(int id) {
        String dml = String.format("DELETE FROM product WHERE id=%d", id);

        try {
            this.databaseService.performDML(dml);
        } catch (RuntimeException exception) {
            System.out.println("ERROR!");
            System.out.println(exception.getMessage());
        }
    }
}
