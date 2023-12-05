package com.api.grg.envy.vendor;

import java.util.ArrayList;
import com.api.grg.envy.post.Post;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(unique = true, length = 200, nullable = false)
    private String name;
    private String email;
    private String password;

    @Lob
    private Byte[] profil;    
    private ArrayList<Post> posts;


    public Vendor() {};

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
    public ArrayList<Post> getPosts() { return this.posts ;}

    // do we really need setters for now ?
}
