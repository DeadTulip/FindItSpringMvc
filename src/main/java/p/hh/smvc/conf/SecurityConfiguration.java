package p.hh.smvc.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import p.hh.smvc.security.*;
import p.hh.smvc.services.UserService;

@Configuration
@EnableWebSecurity
@ComponentScan({"p.hh.smvc.security", "p.hh.smvc.services"})
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private RESTAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private RESTAuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private RESTAuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private ApplicationUserService applicationUserService;

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.authenticationProvider(daoUsernamePasswordAuthenticationProvider())
                .authenticationProvider(tokenAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").permitAll()
                .anyRequest().authenticated();
        http.csrf().disable();
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);

        http.addFilterBefore(new AuthenticationFilter(authenticationManager()), BasicAuthenticationFilter.class);
    }

    @Bean
    public TokenService tokenService() {
        return new TokenService();
    }

    @Bean
    public AuthenticationProvider domainUsernamePasswordAuthenticationProvider() {
        return new DomainUsernamePasswordAuthenticationProvider(tokenService());
    }

    @Bean
    public AuthenticationProvider tokenAuthenticationProvider() {
        return new TokenAuthenticationProvider(tokenService());
    }

    @Bean
    public DaoUsernamePasswordAuthenticationProvider daoUsernamePasswordAuthenticationProvider() {
        return new DaoUsernamePasswordAuthenticationProvider(tokenService(), applicationUserService);
    }
}
