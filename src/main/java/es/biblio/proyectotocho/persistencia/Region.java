package es.biblio.proyectotocho.persistencia;

import java.util.Objects;

/**
 *
 * @author Alberto
 */
public class Region implements Comparable {

    private Integer regionId;
    private String regionName;

    public Region(Integer regionId, String regionName) {
        this.regionId = regionId;
        this.regionName = regionName;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.regionId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Region other = (Region) obj;
        return Objects.equals(this.regionId, other.regionId);
    }

    @Override
    public int compareTo(Object o) {
        Region other = (Region) o;
        return this.regionId = other.regionId;
    }

    @Override
    public String toString() {
        return regionName;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
}
