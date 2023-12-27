package com.api.grg.envy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.grg.envy.vendor.Vendor;

public interface RepositoryVendor extends JpaRepository<Vendor, Long>{
    Vendor findByName( String name );
}
