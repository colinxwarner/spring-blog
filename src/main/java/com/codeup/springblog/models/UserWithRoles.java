package com.codeup.springblog.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserWithRoles extends User implements UserDetails {

    // Copy Constructor - for a User object
    public UserWithRoles(User user) { // takes an input argument of a 'User'
        super(user); // this returns a copy of the superclass, which is 'User'

        // Seems redundant, doesn't it?
        // Send in a user to the constructor, get a user out of it..... huh?
        // This is so we can use polymorphism to have an object that looks EXACTLY like a user to other parts of our code, but can do additional things, like those in the UserDetails interface.
        // Let's see that now!
    }

    // We also want to override the getAuthorities() method from the UserDetails interface
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roles = "";
        // When you actually want to implement roles, you put them in that string separated by commas
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roles); // this will return NADA roles. (none roles)
        // no roles will be returned. none.
    }

    // Override the UserDetails methods, and have them available here, with our pre-determined values
    @Override
    public boolean isAccountNonExpired() {
        // If we actually wanted to check to see if an account is expired, we could check a DateTime difference between today, and the User.registrationDate (assuming that property exists in our User object)
        // Date now = Date();
        // if ((now - user.registrationDate) > 365) {
        //     return false;
        // }
        return true;
    }
    // If Spring Security wants to check  for account expiration, just say it's not expired, because we haven't written in any functionality to handle and expired account

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    // Just say that all passwords can last forever, without ever needing to be changed.
    //  (JUST FOR THIS APP! BECAUSE WE DON'T NEED IT! YOU'LL STILL USE THIS FOR REAL, IN THE REAL WORLD!)

    @Override
    public boolean isEnabled() {
        return true;
    }

}
