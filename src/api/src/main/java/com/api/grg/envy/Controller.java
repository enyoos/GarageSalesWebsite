package com.api.grg.envy;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.grg.envy.vendor.Vendor;

@RestController
@RequestMapping("/api/vendors")
public class Controller {

    private final Service service;

    @Autowired
    public Controller ( Service s ) { this.service = s; }

    @GetMapping("/")
    public ResponseEntity<List<Vendor>> rootPath( )
    {

        ResponseEntity< List<Vendor> > out;
        List<Vendor> vendors = this.service.getVendors();
        System.out.println(vendors);
        out = new ResponseEntity<>( vendors, HttpStatus.FOUND);

        return out;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Vendor> idPathVar(@PathVariable(value = "id") Long id) {
        
        ResponseEntity< Vendor > out;
        Optional<Vendor> opt = this.service.getVendorById(id);
        if ( opt.isPresent() ) { out = new ResponseEntity<>( null, HttpStatus.NOT_FOUND); }
        else { out = new ResponseEntity<>(opt.get(), HttpStatus.FOUND); }

        return out;
    }
 
    @PostMapping(value =  "/" , 
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, 
        produces = {
            MediaType.APPLICATION_ATOM_XML_VALUE, 
            MediaType.APPLICATION_JSON_VALUE
        }
    )
    public ResponseEntity<String> newVendorPath ( @RequestBody Vendor v )
    {
    
        ResponseEntity< String > out;

        // validation shall be on the client side..
        v = this.service.saveVendor(v);

        System.out.println(v);

        if ( v == null ) { out = new ResponseEntity<>( String.format ( "failed saving the vendor : %s", v.toString()), HttpStatus.INTERNAL_SERVER_ERROR ); }
        else { out = new ResponseEntity<>( String.format ( "saved the vendor : %s", v.toString()), HttpStatus.ACCEPTED); }

        return out;
    }
    
}
