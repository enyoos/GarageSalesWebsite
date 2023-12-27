package com.api.grg.envy.post;

import java.sql.Date;
import java.time.LocalDate;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.grammars.hql.HqlParser.LocalDateTimeLiteralContext;
import org.springframework.format.annotation.DateTimeFormat;

import com.api.grg.envy.vendor.Vendor;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table( name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_generator")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vendor_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Vendor vendor;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column( name = "post_date", columnDefinition = "DATE")
    private Date dateOfPublication;

    @Column( name = "post_title")
    private String title;

    @Lob
    @Column(name = "post_description")
    private String description;


    @Nullable
    @Lob
    @Column( name = "post_image", length = 20971520)
    private Byte[] image;

    public Post(){}


    // make the 2 constructors
    public Post ( Long id, Date dateOfPublication, String ttl, String d, Byte[] img )
    {
        this.id = id;
        this.dateOfPublication=dateOfPublication;
        this.title = ttl;
        this.description = d;
        this.image = img;
    }

    public Post ( Date dateOfPublication, String ttl, String d, Byte[] img )
    {
        this.dateOfPublication=dateOfPublication;
        this.title = ttl;
        this.description = d;
        this.image = img;
    }

    // make the getter and setters
    public Long getId ( ) { return this.id ;}
    public Vendor getVendor () { return this.vendor;}
    public Date getDateOfPublication ( ) { return this.dateOfPublication; }
    public String getTitle () { return this.title; }
    public String getDescription ( ) { return this.description;}
    public Byte[] getImage () { return this.image; }

    public void settId ( Long id ) { this.id = id ;}
    public void setVendor ( Vendor vendor ) { this.vendor = vendor;}
    public void setDateOfPublication( Date date ) { this.dateOfPublication = date; }
    public void setTitle ( String ttl) { this.title = ttl; }
    public void setDescription ( String d) { this.description = d ;}
    public void setImage ( Byte[] b ) { this.image = b; }
}
