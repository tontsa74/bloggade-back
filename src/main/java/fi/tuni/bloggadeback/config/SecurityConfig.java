package fi.tuni.bloggadeback.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";

/*    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                    .antMatchers("/api/private/accounts/*").hasRole(USER)
                    .antMatchers("/api/private/admin/**").hasRole(ADMIN)
                .and()
                    .formLogin();
    }*/

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/api/public/**").permitAll()
                .antMatchers("/api/private/user/**").hasRole(USER)
                .antMatchers("/api/private/admin/**").hasRole(ADMIN)
                .and()
                .formLogin();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("jussi").password("{noop}password").roles(USER, ADMIN)
                .and()
                .withUser("jaska").password("{noop}password").roles(USER);
    }
}
