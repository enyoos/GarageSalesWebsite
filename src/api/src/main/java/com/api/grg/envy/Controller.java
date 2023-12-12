package com.api.grg.envy;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    public ResponseEntity<Vendor> GetIdPathVar(@PathVariable(value = "id") Long id) {

        ResponseEntity< Vendor > out;
        Optional<Vendor> opt = this.service.getVendorById(id);
        if ( opt.isPresent() ) { out = new ResponseEntity<>( null, HttpStatus.NOT_FOUND); }
        else { out = new ResponseEntity<>(opt.get(), HttpStatus.FOUND); }
        return out;

    }

    // @Test
    @PostMapping(value = "/", consumes = {"application/json"}) // this works very well.
    public ResponseEntity<String> newVendorPath ( @RequestBody Vendor v )
    {
        // before even adding the vendor, we need to check if the name is valid
        // or even the email too. ( NOT PREVIOUSLY TAKEN )
        String email = v.getEmail();
        String name = v.getName();
        
        Optional<Vendor> tt = this.service.getVendorByEmail(email);
        Optional<Vendor> tt2 = this.service.getVendorByName(name);
        
        boolean nameT = this.isNameTaken(name);
        boolean emailT = this.isEmailTaken(email);
        if ( nameT && emailT ) return new ResponseEntity<>(String.format ( "The email and the name %s, %s are taken", email, name), HttpStatus.CONFLICT);
        else if ( nameT ) return new ResponseEntity<>(String.format ( "The name %s is taken", name), HttpStatus.CONFLICT);
        else if ( emailT ) return new ResponseEntity<>(String.format ( "The email %s is taken", email), HttpStatus.CONFLICT);
        else {
            boolean result = this.service.saveVendor(v);
            return result ? new ResponseEntity<>(String.format("Saved the vendor : %s", v), HttpStatus.INTERNAL_SERVER_ERROR ) : new ResponseEntity<>( String.format ( "failed to save the vendor : %s", v.toString()), HttpStatus.ACCEPTED);
        }
    }

    private boolean isNameTaken ( String name )
    {
        Optional<Vendor> opt = this.service.getVendorByName(name);
        return opt.isPresent();
    }

    private boolean isEmailTaken ( String email )
    {
        Optional<Vendor> opt = this.service.getVendorByEmail(email);
        return opt.isPresent();
    }

    @PostMapping( value = "/update", consumes = {"application/json"})
    public ResponseEntity<String> updateVendorPath( @RequestBody Vendor newVendor )
    {
        boolean result = this.service.updateVendor(newVendor);
        // boolean result = this.service.saveVendor(newVendor);
        return result ? new ResponseEntity<>(String.format ("Saving the vendor : %s", newVendor), HttpStatus.INTERNAL_SERVER_ERROR ) : new ResponseEntity<>( String.format ( "failed the vendor : %s", newVendor.toString()), HttpStatus.ACCEPTED);
    }

    // GET, WILL DELETE ALL OF YOUR DATA ( THINK OF CRAWLERS )
    // NEED, a token for that ?
    @DeleteMapping( value = "/private/delete/{id}") // htis works very well.
    public ResponseEntity<String> DeleteVendorPath ( @PathVariable(value = "id") Long id )
    {
        // get the vendor by the id
        boolean OK = this.service.deleteVendorById(id);
        String msg = "the vendor with the id : ";
        return OK ? new ResponseEntity<>("Deleted " + msg + id, HttpStatus.OK) : new ResponseEntity<>( msg + id + ", is not found", HttpStatus.NOT_FOUND);
    }
}
