package pl.britenet.campus.builder;

import pl.britenet.campus.obj.model.Product;

public class ProductBuilder {

    private final Product product;

    public ProductBuilder(int id) {
        this.product = new Product(id);
    }

    public ProductBuilder setName(String name) {
        this.product.setName(name);
        return this;
    }

    public ProductBuilder setDescription(String description) {
        this.product.setDescription(description);
        return this;
    }

    public ProductBuilder setCategoryId(int categoryId) {
        this.product.setCategoryId(categoryId);
        return this;
    }

    public Product getProduct() {
        return this.product;
    }

}
