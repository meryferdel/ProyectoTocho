package es.biblio.proyectotocho.persistencia;

/**
 * Entidad que representa la tabla locations.
 */
public class Location {

    private Integer locationId;
    private String address;
    private String postalCode;
    private String city;
    private String state;
    private String countryId;

    public Location() {
    }

    public Location(Integer locationId, String address, String postalCode, String city,
                    String state, String countryId) {
        this.locationId = locationId;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.state = state;
        this.countryId = countryId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return address;
    }
}

