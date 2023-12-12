package com.api.grg.envy;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.grg.envy.vendor.Vendor;

public interface Repository extends JpaRepository< Vendor, Long> {
    Optional<Vendor> findByName(String name);
    Optional<Vendor> findByEmail(String email);
}