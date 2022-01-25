package org.launcode.Code.Food.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import java.util.concurrent.TimeUnit;

import static org.launcode.Code.Food.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) //Adds more encryption for requests sent from browsers
//                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "list", "list-recipes", "/css/*", "/js/*", "/assets.img/*",
                        "/cuisine/view/*", "/dietaryrestrictions/view/*", "/mealtypes/view/*", "/recipe/view/*", "/list", "/list/*").permitAll() //Allows for pages containing these to be accessed without logging in
                .antMatchers("/api/**").hasRole(USER.name()) //Allows regular users to access their account
                .antMatchers("/add/**").hasRole(ADMIN.name()) //Allows only admin access to adding
                .antMatchers("/delete/**").hasRole(ADMIN.name()) //Allows only admin access to deleting
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .defaultSuccessUrl("/", true) //Adds page to redirect to after successful login
                    .passwordParameter("password") //Allows us to change parameter for password
                    .usernameParameter("username") //Allows us to change parameter for username
                .and()
                .rememberMe()
                    .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21)) //Increases remember-me session past 2 weeks
                    .key("somethingverysecret") //Supposed to keep secret
                    .rememberMeParameter("remember-me") //Allows us to change parameter for remember me checkbox
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID", "remember-me")
                    .logoutSuccessUrl("/login"); //Adds logout of the user and deletes any data leftover
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails annaSmithUser = User.builder()
                .username("annasmith")
                .password(passwordEncoder.encode("password"))
                .authorities(USER.getGrantedAuthorities())
                .build();
        //Manually adds Anna Smith as a USER

        UserDetails lindaUser = User.builder()
                .username("linda")
                .password(passwordEncoder.encode("password123"))
                .authorities(ADMIN.getGrantedAuthorities())
                .build();
        //Manually adds Linda as an ADMIN

        UserDetails tomUser = User.builder()
                .username("tom")
                .password(passwordEncoder.encode("password123"))
                .authorities(ADMINTRAINEE.getGrantedAuthorities())
                .build();
        //Manually adds Tom as an ADMIN TRAINEE

        return new InMemoryUserDetailsManager(
                annaSmithUser,
                lindaUser,
                tomUser
        );
    }

}