package com.michael.timezones;

import org.hibernate.engine.jdbc.connections.internal.DatasourceConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@EnableWebMvcSecurity
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().requireCsrfProtectionMatcher(new RequestMatcher() {
            private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");
            private RegexRequestMatcher apiMatcher = new RegexRequestMatcher("/console/.*", null);

            @Override
            public boolean matches(HttpServletRequest request) {
                if(allowedMethods.matcher(request.getMethod()).matches())
                    return false;
                if(apiMatcher.matches(request))
                    return false;
                return true;
            }
        });

        http.headers().frameOptions().disable();
        http.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);

        http.authorizeRequests().anyRequest().authenticated();
        http.formLogin().failureUrl("/login?error")
                .defaultSuccessUrl("/home")
                .loginPage("/login")
                .permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        PasswordEncoder passwordEncoder = NoOpPasswordEncoder.getInstance();
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery(
//                        "select username,password from users where username=?")
//                .authoritiesByUsernameQuery(
//                        "select username, role from user_roles where username=?");
//
//            JdbcUserDetailsManager userDetailsService = new JdbcUserDetailsManager();
//            userDetailsService.setDataSource(dataSource);
//            PasswordEncoder encoder = ;
//
//            auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
//            auth.jdbcAuthentication().dataSource(dataSource);
//
//        if (userDetailsService.loadUserByUsername()userExists("user")) {
//
//                List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//                authorities.add(new SimpleGrantedAuthority("ADMIN"));
//                User userDetails = new User("user", passwordEncoder.encode("password"), authorities);
//
//                userDetailsService.createUser(userDetails);
//        }

//
//        http.authorizeRequests()
//                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
//                .and()
//                .formLogin().loginPage("/login").failureUrl("/login?error")
//                .usernameParameter("username").passwordParameter("password")
//                .and()
//                .logout().logoutSuccessUrl("/login?logout")
//                .and()
//                .exceptionHandling().accessDeniedPage("/403")
//                .and()
//                .csrf();


//            auth.jdbcAuthentication().authoritiesByUsernameQuery()
//                    .ldapAuthentication()
//                    .userDnPatterns("uid={0},ou=people")
//                    .groupSearchBase("ou=groups")
//                    .contextSource().ldif("classpath:test-server.ldif");
        }
}

