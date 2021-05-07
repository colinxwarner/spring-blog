package com.codeup.springblog;

import com.codeup.springblog.services.UserDetailsLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    // Which pages will require authentication?
    // Which pages are available to everyone?
    // What is the path to the login page?
    // What hashing algorithm will we use to store passwords?
    // (Remember we didn't specify Bcrypt in our UserController? Now we can specify which algorithm to use!)

    public UserDetailsLoader usersLoader;
    // really fancy way of saying now we have a supercharged UserRepository, with way more functionality than a plain UserRepository has

    // Set up our constructor for this Configuration
    public SecurityConfiguration(UserDetailsLoader usersLoader) {
        this.usersLoader = usersLoader;
    }

    // This is like a miniature class, but so small that it doesn't warrant its own file, so we can just annotate it as a @Bean here, for access elsewhere in our code
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Now let's really ramp this thing up

    // This is the override that just sets up the Authentication process for our app (i.e. super-charged UserRepository and PasswordEncoder. "Authy" sort of stuff
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(usersLoader)  // How to find users by their username
                .passwordEncoder(passwordEncoder()) // How to encode and decode/verify passwords
        ;
    }

    // Override what happens when specific HTTP pages are loaded / requests are made / etc
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                /********* Login Configuration *********/
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/posts") // when they successfully log in, redirect to /posts
                .permitAll() // anyone can go to the login page
                /********** Logout Configuration **********/
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout") // we set a parameter 'logout', so we can display a message to the user on the logout page "Congrats on successfully logging out!"
                /********** Pages that can be viewed by anyone ************/
                .and()
                .authorizeRequests() // let visitors view pages, based on the next argument
                .antMatchers("/", "/ads") // Another Neat Tool matcher - if someone hits these URLs in their browser (i.e. http://localhost:8080/ads) they are allowed to view, even if not logged in
                .permitAll()  // like a catch-all
                /************** Pages that DO require authentication ***********/
                .and()
                .authorizeRequests()
                .antMatchers("/ads/create", "/ads/{id}/edit") // pages that we DO want users to be logged in to view/access
                .authenticated() // for the previously mentioned Another Neat Tool Matched URL patterns, users should be authenticated (logged in) to access them
        ;

    }
}
