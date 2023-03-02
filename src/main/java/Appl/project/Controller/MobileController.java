package Appl.project.Controller;

import Appl.project.Exceptions.ItemNotFoundException;
import Appl.project.Model.Mobile;
import Appl.project.Service.MobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v2")
public class MobileController {
    @Autowired
    MobileService mobileService;

    @PostMapping("/mobiles")
    public ResponseEntity<Object> addProduct(@RequestBody Iterable<Mobile> prod){
        return new ResponseEntity<>(mobileService.addMobile(prod),HttpStatus.CREATED);
    }
    @GetMapping("/mobiles/id/{id}")
    //We are getting the details of the product using getProductById method of ProductService class, and adding the response into http response entity's body.
    public ResponseEntity<Object> getProductById(@PathVariable("id") Integer id){
        try {
            return ResponseEntity.ok().body(mobileService.getMobileById(id));
        }
        catch (ItemNotFoundException e)
        {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/mobiles/name/{name}")
    //We are getting the details of the product using getProductById method of ProductService class, and adding the response into http response entity's body.
    public ResponseEntity<Object> getProductByName(@PathVariable("name") String name){
        try {
            return ResponseEntity.ok().body(mobileService.getMobileByName(name.toLowerCase()));
        }
       catch (ItemNotFoundException e)
       {
           return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
       }
    }
    @GetMapping("/mobiles")
    public List<Mobile> getProducts(){
        return mobileService.getMobiles();
    }

    @PutMapping("/mobiles/update/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable("id") Integer id, @RequestBody Mobile prod){
        try
        {
            return new ResponseEntity<>(mobileService.updateMobile(id,prod),HttpStatus.OK);
        }
        catch(ItemNotFoundException e)
        {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("mobiles/delete/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") int id) {
        try {
            mobileService.deleteMobile(id);
            return new ResponseEntity<>("", HttpStatus.OK);
        }
        catch(ItemNotFoundException e)
        {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
