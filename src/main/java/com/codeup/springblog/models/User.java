package com.codeup.springblog.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    // If we wanted to check for account expiration (like it's been more than 365 days since they registered, we need to store a 'registrationDate' property, to do the date difference check on
    // @Column(nullable = false)
    // private Date registrationDate;

    // We would also add this property to the constructor, copy constructor, and add getters and setters for it, if we wanted to store the registration

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Post> posts;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Ad> ads;

    // zero argument constructor - to simply reserve space in memory for creation of User objects
    public User() {
    }

    public User(long id, String email, String username, String password, List<Ad> ads, List<Post> posts) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.ads = ads;
        this.posts = posts;
    }

    // implement the Copy Constructor right here in the User model!
    // We can call on this constructor from elsewhere in our code, and don't have to specify all of the User object's properties (like email, username, etc)
    public User(User copy) {
        this.id = copy.id; // VERY IMPORTANT. Many things won't work if you don't include this assignment
        this.email = copy.email;
        this.username = copy.username;
        this.password = copy.password;
        this.ads = copy.ads;
        this.posts = copy.posts;

        // It's like the Abed from the Darkest Timeline, and normal Abed (Community - it's on Netflix. Watch it)
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Ad> getAds() {
        return ads;
    }

    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}