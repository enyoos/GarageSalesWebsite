package com.api.grg.envy;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.api.grg.envy.vendor.Vendor;

@org.springframework.stereotype.Service
public class Service {
    
    private final Repository repo; 

    @Autowired
    public Service( Repository r ) { this.repo = r; }

    public List<Vendor> getVendors ( ) { return this.repo.findAll(); }
    public Optional<Vendor> getVendorById ( Long id ) { return this.repo.findById(id); }
    public Vendor saveVendor ( Vendor v ) { return this.repo.save(v); }
}
