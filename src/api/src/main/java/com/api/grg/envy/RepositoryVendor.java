package com.api.grg.envy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.grg.envy.vendor.Vendor;

import jakarta.transaction.Transactional;

public interface RepositoryVendor extends JpaRepository<Vendor, Long>{
    
    Vendor findByName( String name );
}
