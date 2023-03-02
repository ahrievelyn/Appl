package Appl.project.Service;

import Appl.project.Exceptions.ItemNotFoundException;
import Appl.project.Model.Cart;
import Appl.project.Model.Mobile;
import Appl.project.Repository.CartRepo;
import Appl.project.Repository.MobileRepo;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.InvalidTransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Objects;

@Service

public class CartServiceImpl extends CartResponse implements CartService {
    @Autowired
    CartRepo cartRepo;
    @Autowired
    MobileRepo mobileRepo;
    @Override
    public Cart createCart(Integer cartId) {
        if(cartRepo.existsById(cartId))
        {
            throw new EntityExistsException("Cart ("+cartId+") already exists");
        }
        else
        {
            Cart c = new Cart();
            c.setOrderId(cartId);
            return cartRepo.save(c);
        }
    }

    @Override
    public Cart addToCartById(Integer cartId, Integer mobileId) {
        Cart cart = cartRepo.findById(cartId).orElseThrow(
                () -> new ItemNotFoundException("No such cart ("+cartId+") found, Cannot add item to cart that doesn't exist"));
        Mobile mobile = mobileRepo.findById(mobileId).orElseThrow(
                () -> new ItemNotFoundException("No such mobile ("+mobileId+") found"));
        if(!cart.getMobiles().contains(mobileId)){
            if(mobile.getQuantity()!=0)
            {
                cart.setMobile(mobileId);
                mobile.setQuantity(mobile.getQuantity()-1);
                return cartRepo.save(cart);
            }
            else {
                throw new ItemNotFoundException("No more mobiles of ("+mobileId+")exist to add");
            }
        }
        else {
            throw new EntityExistsException("Mobile ("+mobileId+") already exists");
        }
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
        for (Integer e: cart.getMobiles())
        {
            Mobile m = mobileRepo.findById(e).orElseThrow(
                    () -> new ItemNotFoundException("No such mobile ("+e+") found"));
            m.setQuantity(m.getQuantity()+1);
        }
        cartRepo.delete(cart);
    }

    @Override
    public void deleteFromCartByMobileId(Integer cartId, Integer mobileId){
        Cart cart = cartRepo.findById(cartId).orElseThrow(
                () -> new ItemNotFoundException("No such cart ("+cartId+") found, Cannot delete from what doesn't exist"));
        Mobile mobile = mobileRepo.findById(mobileId).orElseThrow(
                () -> new ItemNotFoundException("No such mobile ("+mobileId+") found"));
        mobile.setQuantity(mobile.getQuantity()+1);
        cart.deleteMobile(mobileId);
        cartRepo.save(cart);
    }
    @Override
    public void deleteFromCartsByMobileId(Integer mobileId)
    {
        Mobile mobile = mobileRepo.findById(mobileId).orElseThrow(
                () -> new ItemNotFoundException("No such mobile ("+mobileId+") found"));
        List<Cart> carts = cartRepo.findAll();
        for (Cart each: carts){
            if(each.getMobiles().contains(mobileId))
            {
                deleteFromCartByMobileId(each.getOrderId(),mobileId);
            }
        }
    }
}
