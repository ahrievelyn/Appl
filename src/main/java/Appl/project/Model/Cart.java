package Appl.project.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;

@Entity
@Table(name="Cart")
@ToString
@NoArgsConstructor
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Column(name = "orderId")
    Integer orderId;
    @Column(name = "mobiles")
    ArrayList<Integer> mobiles = new ArrayList<>();

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public ArrayList<Integer> getMobiles() {
        return mobiles;
    }

    public void setMobile(Integer mobileId) {
        this.mobiles.add(mobileId);
    }

    public void deleteMobile(Integer mobileId)
    {
        this.mobiles.remove(mobileId);
    }

}
