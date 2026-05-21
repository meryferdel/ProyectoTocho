package es.biblio.proyectotocho.persistencia;

/**
 * Entidad que representa la tabla inventories.
 * Clave primaria compuesta: (productId, warehouseId)
 */
public class Inventory {

    private int productId;
    private String productName;
    private int warehouseId;
    private int quantity;

    public Inventory() {
    }

    public Inventory(int productId, int warehouseId, int quantity) {
        this.productId = productId;
        this.warehouseId = warehouseId;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    
    
    
}
