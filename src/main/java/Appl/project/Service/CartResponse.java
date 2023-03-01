package Appl.project.Service;

import Appl.project.Model.Mobile;

import java.util.ArrayList;

public class CartResponse {
    private Integer cartId;
    private ArrayList<Mobile> mobiles = new ArrayList<>();

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public ArrayList<Mobile> getMobiles() {
        return mobiles;
    }

    public void addMobiles(Mobile mobiles) {
        this.mobiles.add(mobiles);
    }
}
