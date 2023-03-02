package Appl.project.Controller;

import Appl.project.Exceptions.ItemNotFoundException;
import Appl.project.Model.Cart;
import Appl.project.Model.Mobile;
import Appl.project.Service.CartResponse;
import Appl.project.Service.CartService;
import Appl.project.Service.MobileService;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.InvalidTransactionException;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;

@RestController
@RequestMapping("api/v3/")

public class cartController {
    @Autowired
    CartService cartService;

    @PostMapping("/cart/create")
    /*This creates a cart with specified id passed through request body.
    If the cart already exists with given Id, it throws exception mentioning the same to client in the response.
    If the cart doesn't exist, it creates a cart with given id sends a success message.
     */
    public ResponseEntity<Object> createCart(@RequestBody Integer cartId) {
        Cart c = null;
        try {
            c = cartService.createCart(cartId);
            return new ResponseEntity<Object>(c,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @PostMapping("/cart/add/{cartId}/mobile/")
    public ResponseEntity<Object> addToCartById(@PathVariable("cartId") Integer cartId, @RequestBody Integer mobileId) {
        Cart c = null;
        try {
            c = cartService.addToCartById(cartId, mobileId);
            return new ResponseEntity<>(c, HttpStatus.OK);
        } catch (EntityExistsException e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_IMPLEMENTED);
        } catch (ItemNotFoundException e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/cart/show/{cartId}")
    public ResponseEntity<Object> showCartById(@PathVariable("cartId") Integer cartId){

        try{
            return ResponseEntity.ok().body(cartService.showCartById(cartId));
        }
        catch(ItemNotFoundException e)
        {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/cart/show")
    public ResponseEntity<Object> showCart(){
        return ResponseEntity.ok().body(cartService.showCart());
    }

    @DeleteMapping("cart/delete/{cartId}")
    public ResponseEntity<Object> deleteFromCartById(@PathVariable("cartId") Integer cartId) {
        try{
            cartService.deleteFromCartById(cartId);
            return new ResponseEntity<>("", HttpStatus.OK);
        }
        catch(ItemNotFoundException e)
        {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("cart/delete/{cartId}/mobile/{mobileId}")
    public ResponseEntity<Object> deleteFromCartByMobileId(@PathVariable("cartId") Integer cartId,@PathVariable("mobileId") Integer mobileId) {

        try
        {
            cartService.deleteFromCartByMobileId(cartId,mobileId);
            return new ResponseEntity<>("", HttpStatus.OK);
        }
        catch(ItemNotFoundException e)
        {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
