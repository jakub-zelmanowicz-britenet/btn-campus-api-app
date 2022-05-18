package pl.britenet.campus.obj.model.report;

public class CategoryProductsReport {

    private final String name;
    private final int productsAmount;

    public CategoryProductsReport(String name, int productsAmount) {
        this.name = name;
        this.productsAmount = productsAmount;
    }

    public String getName() {
        return name;
    }

    public int getProductsAmount() {
        return productsAmount;
    }

    @Override
    public String toString() {
        return "CategoryProductsReport{" +
                "name='" + name + '\'' +
                ", productsAmount=" + productsAmount +
                '}';
    }
}
