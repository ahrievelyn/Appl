package Appl.project.Service;

import Appl.project.Model.Cart;
import Appl.project.Model.Mobile;

import java.util.ArrayList;
import java.util.List;

public interface CartService {
    Cart createCart(Integer cartId);
    Cart addToCart(Integer mobileId);
    Cart addToCartById(Integer cartId, Integer mobileId);
    ArrayList<CartResponse> showCart();
    ArrayList<Mobile> showCartById(Integer cartId);
    void deleteFromCartById(Integer cartId);
    void deleteFromCartByMobileId(Integer cartId, Integer mobileId);
}
