package com.api.grg.envy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.grg.envy.vendor.Vendor;

public interface Repository extends JpaRepository< Vendor, Long> {}