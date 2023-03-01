package Appl.project.Controller;

import Appl.project.Model.Cart;
import Appl.project.Model.Mobile;
import Appl.project.Service.CartResponse;
import Appl.project.Service.CartService;
import Appl.project.Service.MobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v3/")

public class cartController {
    @Autowired
    CartService cartService;

    @PostMapping("/cart/create")
    public ResponseEntity<Cart> createCart(@RequestBody Integer cartId)
    {
        return new ResponseEntity<>((cartService.createCart(cartId)),HttpStatus.CREATED);
    }
    @PostMapping("/cart/add/mobile/id/")
    //
    public ResponseEntity<Cart> addToCart(@RequestBody Integer mobileId)
    {
        return new ResponseEntity<>(cartService.addToCart(mobileId),HttpStatus.CREATED);
    }
    @PostMapping("/cart/add/{cartId}/mobile/")
    public ResponseEntity<Cart> addToCartById(@PathVariable("cartId") Integer cartId, @RequestBody Integer mobileId){
        return new ResponseEntity<>(cartService.addToCartById(cartId,mobileId),HttpStatus.OK);
    }

    @GetMapping("/cart/show/{cartId}")
    public ResponseEntity<ArrayList<Mobile>> showCartById(@PathVariable("cartId") Integer cartId){
        return ResponseEntity.ok().body(cartService.showCartById(cartId));
    }
    @GetMapping("/cart/show")
    public ResponseEntity<ArrayList<CartResponse>> showCart(){
        return ResponseEntity.ok().body(cartService.showCart());
    }

    @DeleteMapping("cart/delete/{cartId}")
    public ResponseEntity<Object> deleteFromCartById(@PathVariable("cartId") Integer cartId) {
        cartService.deleteFromCartById(cartId);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @DeleteMapping("cart/delete/{cartId}/mobile/")
    public ResponseEntity<Object> deleteFromCartByMobileId(@PathVariable("cartId") Integer cartId,@RequestBody Integer mobileId) {
        cartService.deleteFromCartByMobileId(cartId,mobileId);
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
