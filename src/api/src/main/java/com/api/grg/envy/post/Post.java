package com.api.grg.envy.post;

import java.time.LocalDate;

import com.api.grg.envy.vendor.Vendor;

import jakarta.persistence.Lob;


public class Post {

    private Long id;
    private Vendor vendor;
    private LocalDate datepub;    
    private String title;

    @Lob
    private String description;
    @Lob
    private Byte[] image;

    // make the 2 constructors
    public Post ( Long id, LocalDate datepub, String ttl, String d, Byte[] img )
    {
        this.id = id;
        this.datepub=datepub;
        this.title = ttl;
        this.description = d;
        this.image = img;
    }

    // make the getter and setters
    public Long getId ( ) { return this.id ;}
    public LocalDate getDatePub ( ) { return this.datepub; }
    public String getTitle () { return this.title; }
    public String getDescription ( ) { return this.description;}
    public Byte[] getImage () { return this.image; }
}
