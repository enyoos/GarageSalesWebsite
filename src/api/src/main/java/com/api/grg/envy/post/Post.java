package com.api.grg.envy.post;

import java.time.LocalDate;

import com.api.grg.envy.vendor.Vendor;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table( name = "POST")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name="vendor_id", nullable=false)
    private Vendor vendor;

    @Column(columnDefinition = "DATE")
    private LocalDate datepub;    

    @Column( name = "title")
    private String title;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;


    @Nullable
    @Lob
    @Column( name = "image")
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
