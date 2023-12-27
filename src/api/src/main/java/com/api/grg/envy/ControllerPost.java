package com.api.grg.envy;

import java.util.ArrayList;
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

import com.api.grg.envy.post.Post;
import com.api.grg.envy.vendor.Vendor;

@CrossOrigin( value = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ControllerPost {

    private static int currentPagination = 0;
    private static int previousPagination = 0;
    private static List<Post> previousOutput = new ArrayList<>();

    @Autowired RepositoryVendor repoVendor;
    @Autowired RepositoryPost repo;

    @GetMapping( value = "/vendor/{id}/posts")
    public ResponseEntity<List<Post>> getAllPostByVendorId ( @PathVariable  ( value = "id") Long id )
    {
        if ( !this.repoVendor.existsById(id) )
        { return new ResponseEntity<List<Post>>( new ArrayList<Post>(), HttpStatus.NOT_FOUND); }

        List<Post> posts = this.repo.findByVendorId(id);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }


    @GetMapping( value = "/posts")
    public ResponseEntity<List<Post>> getAllPostOfAllVendors (@RequestParam( value = "page") Integer page)
    {
        previousPagination = currentPagination;
        currentPagination += page.intValue();
        // get all the vendors of the app
        List<Post> ret = new ArrayList<>();
        List<Vendor> vendors = this.repoVendor.findAll();

        System.out.println("the vendors list : " + vendors);

        // else just increment the paging.
        if ( currentPagination - previousPagination >= vendors.size() ) { currentPagination = vendors.size(); previousPagination = 0;}

        for ( int i = previousPagination; i < currentPagination; i ++ )
        {
            List<Post> posts = this.repo.findByVendorId(vendors.get(i).getId());
            System.out.println("the posts : " + posts);
            for ( Post post : posts ) { ret.add(post); }
        }

        if ( ret.equals(previousOutput)) { return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK); }
        ret = previousOutput;
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @GetMapping( value = "/posts/{id}")
    public ResponseEntity<Post> getPostById ( @PathVariable( value = "id") Long id )
    {
        Optional<Post> post = this.repo.findById(id);
        if ( post.isPresent() ) { return new ResponseEntity<>(post.get(), HttpStatus.OK); }
        else { return new ResponseEntity<>(post.get(), HttpStatus.NOT_FOUND); }
    }

    @PostMapping( value = "vendor/{id}/posts")
    public ResponseEntity<Post> createPost( @PathVariable( value = "id") Long id, @RequestBody Post post )
    {
        // find the vendor
        // get his post
        Optional<Vendor> optionalVendor = this.repoVendor.findById(id);

        if ( optionalVendor.isPresent() )
        {
            Vendor vendor = optionalVendor.get();
            Post _post = new Post( post.getDateOfPublication(), post.getTitle(), post.getDescription(), post.getImage());
            _post.setVendor(vendor);
            return new ResponseEntity<> ( this.repo.save(_post), HttpStatus.OK );
        }
        else
        {
            return new ResponseEntity<>(new Post(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping( value = "/posts/{id}" )
    public ResponseEntity<Post> updatePost ( @PathVariable( value = "id") Long id , @RequestBody Post post)
    {
        Optional<Post> _post = this.repo.findById(id);
        if ( _post.isPresent() ) { 
            Post tPost = new Post ( 
                post.getDateOfPublication(),
                post.getTitle(),
                post.getDescription(),
                post.getImage()
            );
            return new ResponseEntity<>( this.repo.save(tPost), HttpStatus.OK );
        }
        else { return new ResponseEntity<>(_post.get(), HttpStatus.NOT_FOUND); }
    }
    
    @DeleteMapping( value = "/posts/{id}")
    public ResponseEntity<HttpStatus> deletePostById ( @PathVariable( value = "id") Long id )
    {
        repo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping( value = "/vendor/{id}/posts")
    public ResponseEntity<HttpStatus> deleteAllPostByVendorId ( @PathVariable (  value = "id" ) Long id )
    {
        Optional<Vendor> optionalVendor = this.repoVendor.findById(id);

        if ( optionalVendor.isPresent() )
        {
            repo.deleteByVendorId(id);
            // return new ResponseEntity<> ( this.repo.save(_post), HttpStatus.OK );
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }

    }
}
