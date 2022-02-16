package org.launcode.Code.Food.auth;

import org.launcode.Code.Food.auth.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsService userDetailsService() {
        return new ApplicationUserService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) //Adds more encryption for requests sent from browsers
//                .and()
                .csrf().disable()
                .authorizeRequests()
//                .antMatchers("/", "index", "/css/*", "/js/*", "/assets.img/*",
//                        "/cuisine", "/cuisine/view/*", "/dietaryrestrictions", "/dietaryrestrictions/view/*",
//                        "/mealtypes", "/mealtypes/view/*", "/recipe", "/recipe/view/*",
//                        "/user/*",
//                        "/list", "/list/*").permitAll() //Allows for pages containing these to be accessed without logging in
//                .antMatchers("/api/**").hasRole(USER.name()) //Allows regular users to access their account
                .antMatchers("/add/**").hasAuthority("ADMIN") //Allows only admin access to adding
                .antMatchers("/delete/**").hasAuthority("ADMIN") //Allows only admin access to deleting
                .anyRequest()
                .permitAll()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .usernameParameter("email") //Allows us to change parameter for username
                    .passwordParameter("password") //Allows us to change parameter for password
                    .defaultSuccessUrl("/", true) //Adds page to redirect to after successful login
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
                    .logoutSuccessUrl("/login") //Adds logout of the user and deletes any data leftover
                .and()
                .exceptionHandling().accessDeniedPage("/403");
    }

/*
    In SQL, use:
    INSERT INTO users_roles (user_id, role_id) VALUES (x, y);
    to add roles
 */

}