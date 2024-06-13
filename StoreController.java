package com.store.store.controller;

import com.store.store.model.Address;
import com.store.store.repository.MySqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
//@RequestMapping("/api/adresses")
public class StoreController {

    @Autowired
    MySqlRepository mySqlRepository;

    @GetMapping("/get-all-addresses")
    public List<Address> getAllAddress(){
        return mySqlRepository.findAll();

    }

    @GetMapping("/get-address/{identity}")
    public Address getSingleAddress(@PathVariable("identity")  Integer id){
        return mySqlRepository.findById(id).get();
    }

    @DeleteMapping("/remove/{id}")
    public boolean removeAddress(@PathVariable("id") Integer id) {
        if (!mySqlRepository.findById(id).equals(Optional.empty())) {
            mySqlRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @PutMapping("/update/{id}")
    public Address updateAddress(@PathVariable("id") Integer id,
                                 @RequestBody Map<String, String> body) {
        Address current = mySqlRepository.findById(id).get();
        current.setStreet(body.get("street"));
        current.setNumber(Integer.parseInt(body.get("number")));
        current.setPostcode(body.get("postcode"));
        mySqlRepository.save(current);
        return current;
    }

    @PostMapping("/insert")
    public Address add(@RequestBody Map<String, String> body) {
        Address current = new Address();
        current.setStreet(body.get("street"));
        current.setNumber(Integer.parseInt(body.get("number")));
        current.setPostcode(body.get("postcode"));
        mySqlRepository.save(current);
        return current;
    }

    @PutMapping("/move/{id}/{id2}")
    public Address updateAddress(@PathVariable("id") Integer idOld,
                                 @PathVariable("id2") Integer idNew,
                                 @RequestBody Map<String, String> body) {
        Address current = mySqlRepository.findById(idOld).get();
        Address newLocation = mySqlRepository.findById(idNew).get();
        current.move(newLocation);
        mySqlRepository.save(current);
        return current;
    }

    @GetMapping("/{adressId}")
    public Address getAddress(@PathVariable Integer adressId){
        Optional<Address> address = mySqlRepository.findById(adressId);

        if(address.isPresent()){
            return address.get();
        }

        return null;
    }
}
