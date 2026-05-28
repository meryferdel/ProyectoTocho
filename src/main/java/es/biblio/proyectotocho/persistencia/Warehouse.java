package es.biblio.proyectotocho.persistencia;

/**
 * Entidad que representa la tabla warehouses.
 */
public class Warehouse {

    private Integer warehouseId;
    private String warehouseName;
    private int locationId;

    public Warehouse() {
    }

    public Warehouse(Integer warehouseId, String warehouseName, int locationId) {
        this.warehouseId = warehouseId;
        this.warehouseName = warehouseName;
        this.locationId = locationId;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    @Override
    public String toString() {
        return warehouseName;
    }
}
