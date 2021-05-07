package com.codeup.springblog.services;

import com.codeup.springblog.models.User;
import com.codeup.springblog.models.UserWithRoles;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsLoader implements UserDetailsService {

    // Curriculum calls the UserRepository interface 'Users' - much like we did with McBurgers - you can use either, as long as you keep the pattern identical for ALL of your interfaces
    // Use the same naming convention for all interfaces/repositories

    private final UserRepository users; // gives us access to the "Claw" of grabbing/manipulating User objects

    public UserDetailsLoader(UserRepository users) {
        this.users = users;
    }

    // UserDetails interface allows us to get password / username / check if account is expired, locked, or credentials expired
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // this just announces to our app that the exception CAN be thrown
        User user = users.findByUsername(username);

        // check to see if we actually found a user or not
        if (user == null) {
            // Now our console log output will be more detailed, in order for us to find the problem more quickly, because we know exactly that this had to do with not finding that username in the DB
            throw new UsernameNotFoundException("User was not found for username: " + username);
            // this tells our Java app to throw the exception immediately
        }

        // why are we not just returning the user? What does returning a 'UserWithRoles' object give us in addition to just the user itself
        return new UserWithRoles(user); // this will return the result of sending the 'user' object INTO a 'UserWithRoles' constructor, and getting the output from using that constructor
    }
}