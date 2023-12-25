package com.api.grg.envy;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.adapter.HttpWebHandlerAdapter;

import com.api.grg.envy.post.Post;
import com.api.grg.envy.vendor.Vendor;

@RestController
@RequestMapping("/api/vendors")
public class Controller {

    private final Service service;
    @Autowired
    public Controller ( Service s ) { this.service = s; }

    @GetMapping("/")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<Vendor>> rootPath( )
    {

        ResponseEntity< List<Vendor> > out;
        List<Vendor> vendors = this.service.getVendors();
        out = new ResponseEntity<>( vendors, HttpStatus.FOUND);

        return out;
    }

    @GetMapping("/name")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Vendor> getNamePathVar ( @RequestParam( value = "name" ) String name)
    {

        ResponseEntity<Vendor> out ;
        Optional<Vendor> opt = this.service.getVendorByName(name);
        if ( opt.isPresent() ) { out = new ResponseEntity<>( opt.get(), HttpStatus.OK); }
        else { out = new ResponseEntity<>(null, HttpStatus.NOT_FOUND); }
        return out;

    }


    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Vendor> GetIdPathVar(@PathVariable(value = "id") Long id) {
        ResponseEntity< Vendor > out;
        Optional<Vendor> opt = this.service.getVendorById(id);
        if ( opt.isPresent() ) { out = new ResponseEntity<>( opt.get(), HttpStatus.FOUND); }
        else { out = new ResponseEntity<>(null, HttpStatus.FOUND); }
        return out;

    }
    

    // is there to make sql query for that ?
    // instead of getting all ? 
    // let's get a limited number of posts
    // or let the user specify it...
    @GetMapping(value = "/posts")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<Post>> giveAllPosts( @RequestParam( value = "page") Integer page )
    {
        // construct a list
        List<Post> out = new ArrayList<>() ;
        // query all the users
        List<Vendor> vendors = this.service.getVendors();
        List<Post> temp = new ArrayList<>();

        int page_  = page.intValue();
        int length = vendors.size();

        // iterate through all the vendors and push it
        if (  length >= page_ )
        {
            for ( int i = 0 ; i < page.intValue(); i ++ )
            {
                temp = vendors.get(i).getPosts();
                for ( Post p : temp ) { 
                    if ( p != null ) out.add(p);
                    else continue;
                }
            }
        }
        else 
        {
            for ( Vendor vendor : vendors )
            {
                temp = vendor.getPosts();
                for ( Post p : temp )
                {
                    if ( p != null ) out.add(p);
                    else continue;
                }
            }
        }

        return new ResponseEntity<List<Post>>( out , HttpStatus.OK );
    }

    @PostMapping(value = "/", consumes = {"application/json"}) // this works very well.
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> newVendorPath ( @RequestBody Vendor v )
    {
        // before even adding the vendor, we need to check if the name is valid
        // or even the email too. ( NOT PREVIOUSLY TAKEN )
        String email = v.getEmail();
        String name = v.getName();
        
        // Optional<Vendor> tt = this.service.getVendorByEmail(email);
        // Optional<Vendor> tt2 = this.service.getVendorByName(name);
        
        boolean nameT = this.isNameTaken(name);
        boolean emailT = this.isEmailTaken(email);
        if ( nameT && emailT ) return new ResponseEntity<>(String.format ( "The email and the name %s, %s are taken", email, name), HttpStatus.CONFLICT);
        else if ( nameT ) return new ResponseEntity<>(String.format ( "The name %s is taken", name), HttpStatus.CONFLICT);
        else if ( emailT ) return new ResponseEntity<>(String.format ( "The email %s is taken", email), HttpStatus.CONFLICT);
        else {
            this.service.saveVendor(v);
            return new ResponseEntity<>(String.format("Saved the vendor : %s", v), HttpStatus.OK );
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
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> updateVendorPath( @RequestBody Vendor newVendor )
    {
        boolean result = this.service.updateVendor(newVendor);
        // boolean result = this.service.saveVendor(newVendor);
        return result ? new ResponseEntity<>(String.format ("Saving the vendor : %s", newVendor), HttpStatus.OK) : new ResponseEntity<>( String.format ( "failed the vendor : %s", newVendor.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping( value = "/{id}/posts")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> updatePostsEntity( @PathVariable( value = "id" ) Long id, @RequestBody Post post )
    {
        Optional<Vendor> opt = this.service.getVendorById(id); 
        if (opt.isPresent())
        {
            Vendor vendor = opt.get();
            // get the posts of the vendor;
            List<Post> posts = vendor.getPosts();
            posts.add(post);
            this.service.updateVendor(vendor);

            return new ResponseEntity<String>( "Post is added successfully! ", HttpStatus.OK );
        }
        else
        { return new ResponseEntity<>("The vendor with the id : " + id + " does not exist", HttpStatus.NOT_FOUND); }
    }

    // GET, WILL DELETE ALL OF YOUR DATA ( THINK OF CRAWLERS )
    // NEED, a token for that ?
    @DeleteMapping( value = "/private/delete/{id}") // htis works very well.
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> DeleteVendorPath ( @PathVariable(value = "id") Long id )
    {
        // get the vendor by the id
        boolean OK = this.service.deleteVendorById(id);
        String msg = "the vendor with the id : ";
        return OK ? new ResponseEntity<>("Deleted " + msg + id, HttpStatus.OK) : new ResponseEntity<>( msg + id + ", is not found", HttpStatus.NOT_FOUND);
    }


}
