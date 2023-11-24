package com.api.grg.envy.vendor;

import java.util.ArrayList;

import com.api.grg.envy.post.Post;

public class Vendor {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Byte[] profil;    
    private ArrayList<Post> posts;


    public Vendor() {};

    // with all the entries
    // TODO
    public Vendor ( Long id , String name, String email, String password, Byte[] profil, ArrayList<Post> post )
    {

    }

    // without the id
    // TODO
    public Vendor ( String name, String email, String password, Byte[] profil, ArrayList<Post> post )
    {

    }

    // without the posts and the profil
    // TODO
    public Vendor ( String name, String email, String passw )
    {

    }

    // make the getter and setters
}
