package Appl.project.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString
@NoArgsConstructor
@Getter
@Setter
@Table(name = "mobiles")
public class Mobile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mobileId")
    private Integer mobileId;
    @Column(name="hasBluetoothConnectivity")
    private boolean hasBluetoothConnectivity;
    @Column(name="hasNetworkConnectivity")
    private boolean hasNetworkConnectivity;
    @Column(name="modelName")
    private String modelName;
    @Column(name="osType")
    private String osType;
    @Column(name="price")
    private Integer price;
    @Column(name="quantity")
    private Integer quantity;


    public Integer getMobileId() {
        return mobileId;
    }

    public void setMobileId(Integer mobileId) {
        this.mobileId = mobileId;
    }

    public boolean isHasBluetoothConnectivity() {
        return hasBluetoothConnectivity;
    }

    public void setHasBluetoothConnectivity(boolean hasBluetoothConnectivity) {
        this.hasBluetoothConnectivity = hasBluetoothConnectivity;
    }

    public boolean isHasNetworkConnectivity() {
        return hasNetworkConnectivity;
    }

    public void setHasNetworkConnectivity(boolean hasNetworkConnectivity) {
        this.hasNetworkConnectivity = hasNetworkConnectivity;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Integer getPrice() {
        return price;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
