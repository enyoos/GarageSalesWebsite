package com.api.grg.envy.vendor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.api.grg.envy.post.Post;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "VENDOR")
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false)
    private Long id;

    @Column( name = "name", nullable = false)
    private String name;
    @Column( name = "email", nullable =  false)
    private String email;
    @Column( name = "password", nullable = false)
    private String password;

    @Nullable
    @Lob
    @Column(name = "photo", length = 20971520, columnDefinition="BLOB")
    private Byte[] profil;    

    @Nullable
    @OneToMany(mappedBy="vendor")
    private List<Post> posts;


    public Vendor() {}

    // with all the entries
    // TODO
    public Vendor ( Long id , String name, String email, String password, Byte[] profil, ArrayList<Post> post )
    {
        this.id = id;
        this.name = name;
        this.password = password;
        this.profil = profil;
        this.posts = post;
    }

    // without the id
    // TODO
    // public Vendor ( String name, String email, String password, Byte[] profil, ArrayList<Post> post )
    // {
    // }

    // without the posts and the profil
    // TODO
    // public Vendor ( String name, String email, String passw )
    // {

    // }

    // make the getter and setters
    public Long getId ( ) {return this.id;}
    public String getName () { return this.name ;}
    public String getPassword () { return this.password;}
    public String getEmail ( ) { return this.email ;}
    public Byte[] getProfil( ) { return this.profil;}
    public List<Post> getPosts() { return this.posts ;}

    public void setId ( Long id) {this.id = id ;}
    public void setName ( String n ) { this.name = n;}
    public void setPassword ( String p ) { this.password = p ;}
    public void setEmail ( String e) { this.email = e;}
    public void setProfil( Byte[] p ) { this.profil = p;}
    public void setPosts( List<Post> p ) { this.posts = p ;}

    @Override
    public String toString ( )
    {

        Map<String, Object> map = new HashMap<>();
        map.put ( "id", this.id );
        map.put ( "name", this.name );
        map.put ( "password", this.password );
        map.put ( "email", this.email );

        String out = map.toString();
        map = null;

        return out;
    }


}
