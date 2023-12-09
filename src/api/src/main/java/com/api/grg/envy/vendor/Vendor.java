package com.api.grg.envy.vendor;

import java.util.ArrayList;
import com.api.grg.envy.post.Post;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.SecondaryTables;
import jakarta.persistence.Table;

@Entity
@Table(name = "vendors_table")
@SecondaryTables({
    @SecondaryTable(name="posts_table")
})
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(unique = true, length = 200, nullable = false)
    private String name;
    @Column(unique = true, length = 200, nullable = false)
    private String email;
    @Column(unique = true, length = 200, nullable = false)
    private String password;

    @Lob
    @Column(name = "photo", length = 20971520, columnDefinition="BLOB")
    @Nullable
    private Byte[] profil;    

    @Nullable
    @Column(table="posts_table")
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
