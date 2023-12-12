package com.api.grg.envy;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.api.grg.envy.post.Post;
import com.api.grg.envy.vendor.Vendor;

@org.springframework.stereotype.Service
public class Service {
    
    private final Repository repo; 

    @Autowired
    public Service( Repository r ) { this.repo = r; }

    public List<Vendor> getVendors ( ) { return this.repo.findAll(); }
    public Optional<Vendor> getVendorById ( Long id ) { return this.repo.findById(id); }
    public Optional<Vendor> getVendorByName ( String name ){ return this.repo.findByName(name);}
    public Optional<Vendor> getVendorByEmail ( String email ) { return this.repo.findByEmail(email);}
    
    public void saveVendor ( Vendor v ) {
        Vendor tt = this.repo.save(v); 
    }

    public boolean updateVendor( Vendor v )
    {
        String email = v.getEmail();
        String password= v.getPassword();
        String name= v.getName();
        List<Post> posts = v.getPosts();
        Byte[] bytes = v.getProfil();

        Vendor ref = this.repo.getReferenceById(v.getId()); // the name of the method is clear
        if ( ref != null )
        {
            ref.setName(name);
            ref.setEmail(email);
            ref.setPassword(password);
            ref.setPosts(posts);
            ref.setProfil(bytes);
            return true;
        }
        else return false;
    }

    public void deleteVendor( Vendor v ) { this.repo.delete(v);}
    public boolean deleteVendorById ( Long id ) {
        Optional< Vendor > opt = this.getVendorById(id);
        if ( opt.isPresent() )
        {
            deleteVendor(opt.get());
            return true;
        }
        else return false;
    }
}
