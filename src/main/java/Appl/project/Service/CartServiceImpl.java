package Appl.project.Service;

import Appl.project.Exceptions.ItemNotFoundException;
import Appl.project.Model.Cart;
import Appl.project.Model.Mobile;
import Appl.project.Repository.CartRepo;
import Appl.project.Repository.MobileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class CartServiceImpl extends CartResponse implements CartService {
    @Autowired
    CartRepo cartRepo;
    @Autowired
    MobileRepo mobileRepo;
    @Override
    public Cart createCart(Integer cartId) {
        Cart c = new Cart();
        c.setOrderId(cartId);
        cartRepo.save(c);
        return c;
    }
    @Override
    public Cart addToCart(Integer mobileId) {
        Mobile mobile = mobileRepo.findById(mobileId).orElseThrow(
                () -> new ItemNotFoundException("No such mobile ("+mobileId+") found"));
        Cart cart = new Cart();
        cart.setMobile(mobileId);
        return cartRepo.save(cart);
    }

    @Override
    public Cart addToCartById(Integer cartId, Integer mobileId) {
        Cart cart = cartRepo.findById(cartId).orElseThrow(
                () -> new ItemNotFoundException("No such cart ("+cartId+") found, Cannot add item to cart that doesn't exist"));
        Mobile mobile = mobileRepo.findById(mobileId).orElseThrow(
                () -> new ItemNotFoundException("No such mobile ("+mobileId+") found"));
        cart.setMobile(mobileId);
        return cartRepo.save(cart);
    }

    @Override
    public ArrayList<CartResponse> showCart()
    {
        ArrayList<CartResponse> response = new ArrayList<>();
        List<Cart> cart = cartRepo.findAll();
        for (Cart each : cart){
            CartResponse temp = new CartResponse();
            temp.setCartId(each.getOrderId());
            for (Integer e : each.getMobiles())
            {
                Mobile mobile = mobileRepo.findById(e).orElseThrow(
                        () -> new ItemNotFoundException("No such mobile ("+each+") found"));
                temp.addMobiles(mobile);
            }
            response.add(temp);
        }
        return response;
    }

    @Override
    public ArrayList<Mobile> showCartById(Integer cartId) {
        Cart cart = cartRepo.findById(cartId).orElseThrow(
                () -> new ItemNotFoundException("No such cart ("+cartId+") found"));
        ArrayList<Mobile> mobiles = new ArrayList<>();

        for (Integer each:cart.getMobiles())
        {
            Mobile mobile = mobileRepo.findById(each).orElseThrow(
                    () -> new ItemNotFoundException("No such mobile ("+each+") found"));
            mobiles.add(mobile);
        }
        return mobiles;
    }

    @Override
    public void deleteFromCartById(Integer cartId) {
        Cart cart = cartRepo.findById(cartId).orElseThrow(
                () -> new ItemNotFoundException("No such cart ("+cartId+") found, Cannot delete what doesn't exist"));
        cartRepo.delete(cart);
    }

    @Override
    public void deleteFromCartByMobileId(Integer cartId, Integer mobileId){
        Cart cart = cartRepo.findById(cartId).orElseThrow(
                () -> new ItemNotFoundException("No such cart ("+cartId+") found, Cannot delete what doesn't exist"));
        Mobile mobile = mobileRepo.findById(mobileId).orElseThrow(
                () -> new ItemNotFoundException("No such mobile ("+mobileId+") found"));
        cart.deleteMobile(mobileId);
        cartRepo.save(cart);
    }
}
