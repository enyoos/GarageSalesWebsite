package com.api.grg.envy;

// "_ for optionals"

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.grg.envy.vendor.Vendor;

@CrossOrigin( value = "http://localhost:3000")
@RestController
@RequestMapping("/api/vendor")
public class ControllerVendor {

    @Autowired
    RepositoryVendor repo; 

    @GetMapping(value = "/")
    public ResponseEntity<List<Vendor>> getAllVendors(@RequestParam( value = "name", required = false ) String name)
    { return new ResponseEntity<List<Vendor>>(this.repo.findAll(), HttpStatus.OK); }

    @GetMapping(value = "/name")
    public ResponseEntity<Vendor> getVendorByName(@RequestParam( value = "name", required = false ) String name)
    { return new ResponseEntity<>(this.repo.findByName(name), HttpStatus.OK); }


    
    @GetMapping("/{id}")
    public ResponseEntity<Vendor> getVendorById (@PathVariable(value = "id") Long id )
    {
        Optional<Vendor> optionalVendor = this.repo.findById(id);

        if ( optionalVendor.isPresent() )
        { return new ResponseEntity<Vendor>(optionalVendor.get(), HttpStatus.OK); }
        else
        { return new ResponseEntity<Vendor>(optionalVendor.get(), HttpStatus.NOT_FOUND ); }

    }

    @PostMapping( value = "/")
    public ResponseEntity<Vendor> createVendor( @RequestBody Vendor vendor )
    {
        Vendor savedInstanceVendor = new Vendor ( 
            vendor.getName(),
            vendor.getPassword(),
            vendor.getProfil()
        );

        return new ResponseEntity<Vendor>(this.repo.save(savedInstanceVendor), HttpStatus.CREATED);
    }


    @PutMapping( value = "/{id}")
    public ResponseEntity<Vendor> updateVendor (@PathVariable( value = "id")  Long id , @RequestBody Vendor vendor )
    {
        Vendor tempVendor;
        Optional<Vendor> _vendor = this.repo.findById(id);

        if ( _vendor.isPresent() )
        { 
            tempVendor = _vendor.get(); 
            tempVendor.setName(vendor.getName());
            tempVendor.setPassword(vendor.getPassword());
            tempVendor.setProfil(vendor.getProfil());

            return new ResponseEntity<Vendor>(this.repo.save(tempVendor), HttpStatus.OK);
        }
        else
        { return new ResponseEntity<Vendor>(_vendor.get(), HttpStatus.NOT_FOUND ); }
    }

    @DeleteMapping( value = "/{id}")
    public ResponseEntity<HttpStatus> deleteVendorById (@PathVariable( value = "id") Long id )
    {
        this.repo.deleteById(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping( value = "/")
    public ResponseEntity<HttpStatus> deleteAllVendors ( )
    {
        this.repo.deleteAll();
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    } 

}

