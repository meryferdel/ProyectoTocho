package es.biblio.proyectotocho.persistencia;

/**
 * Entidad que representa la tabla products.
 */
public class Product {

    private Integer productId;
    private String productName;
    private String description;
    private double standardCost;
    private double listPrice;
    private int categoryId;

    public Product() {
    }

    public Product(Integer productId, String productName, String description,
            double standardCost, double listPrice, int categoryId) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.standardCost = standardCost;
        this.listPrice = listPrice;
        this.categoryId = categoryId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getStandardCost() {
        return standardCost;
    }

    public void setStandardCost(double standardCost) {
        this.standardCost = standardCost;
    }

    public double getListPrice() {
        return listPrice;
    }

    public void setListPrice(double listPrice) {
        this.listPrice = listPrice;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Product{"
                + "productId=" + productId
                + ", productName='" + productName + '\''
                + ", description='" + description + '\''
                + ", standardCost=" + standardCost
                + ", listPrice=" + listPrice
                + ", categoryId=" + categoryId
                + '}';
    }
}
