package Appl.project.Controller;

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
    public ResponseEntity<List<Mobile>> addProduct(@RequestBody Iterable<Mobile> prod){
        return new ResponseEntity<>(mobileService.addMobile(prod),HttpStatus.CREATED);
    }
    @GetMapping("/mobiles/id/{id}")
    //We are getting the details of the product using getProductById method of ProductService class, and adding the response into http response entity's body.
    public ResponseEntity<Mobile> getProductById(@PathVariable("id") Integer id){
        return ResponseEntity.ok().body(mobileService.getMobileById(id));
    }

    @GetMapping("/mobiles/name/{name}")
    //We are getting the details of the product using getProductById method of ProductService class, and adding the response into http response entity's body.
    public ArrayList<Mobile> getProductByName(@PathVariable("name") String name){
        return (mobileService.getMobileByName(name.toLowerCase()));
    }
    @GetMapping("/mobiles")
    public List<Mobile> getProducts(){
        return mobileService.getMobiles();
    }

    @PutMapping("/mobiles/update/{id}")
    public ResponseEntity<Mobile> updateProduct(@PathVariable("id") Integer id, @RequestBody Mobile prod){
        return new ResponseEntity<>(mobileService.updateMobile(id,prod),HttpStatus.OK);
    }
    @DeleteMapping("mobiles/delete/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") int id) {
        mobileService.deleteMobile(id);
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
