package hello.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.
                jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    /**
     * Here we still need to change the permit, we have to work
     * with the logistician and the drivers. We have to give them different
     * access to the program/webpage
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/mapView").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/index").permitAll()
                
                .antMatchers("/changePassword").hasAnyAuthority("ROLE_LOGISTICIAN","ROLE_DRIVER")

                .antMatchers("/assignedJob").hasAuthority("ROLE_LOGISTICIAN")
                .antMatchers("/jobToDriver").hasAuthority("ROLE_LOGISTICIAN")
                .antMatchers("/logistician").hasAuthority("ROLE_LOGISTICIAN")
                .antMatchers("/productOrder").hasAuthority("ROLE_LOGISTICIAN")
                .antMatchers("/clientForm").hasAuthority("ROLE_LOGISTICIAN")

                .antMatchers("/addedClient").hasAuthority("ROLE_LOGISTICIAN")
                .antMatchers("/addedProduct").hasAuthority("ROLE_LOGISTICIAN")

                .antMatchers("/selectClient").hasAuthority("ROLE_LOGISTICIAN")
                .antMatchers("/searchClient").hasAuthority("ROLE_LOGISTICIAN")
                .antMatchers("/orderForm").hasAuthority("ROLE_LOGISTICIAN")

                .antMatchers("/showUsers").hasAuthority("ROLE_LOGISTICIAN")
                .antMatchers("/newUser").hasAuthority("ROLE_LOGISTICIAN")
                .antMatchers("/deleteUserCheck/**").hasAuthority("ROLE_LOGISTICIAN")
                .antMatchers("/deleteUser/**").hasAuthority("ROLE_LOGISTICIAN")

                .antMatchers("/showJobs").hasAuthority("ROLE_LOGISTICIAN")
                .antMatchers("/deleteJobCheck/**").hasAuthority("ROLE_LOGISTICIAN")
                .antMatchers("/deleteJob/**").hasAuthority("ROLE_LOGISTICIAN")

                .antMatchers("/showTours").hasAuthority("ROLE_LOGISTICIAN")
                .antMatchers("/newTourCreate").hasAuthority("ROLE_LOGISTICIAN")
                .antMatchers("/newTour/**").hasAuthority("ROLE_LOGISTICIAN")
                .antMatchers("/newTourTruck/**").hasAuthority("ROLE_LOGISTICIAN")
                .antMatchers("/newTourTrailer/**").hasAuthority("ROLE_LOGISTICIAN")
                .antMatchers("/newTourProductOrders/**").hasAuthority("ROLE_LOGISTICIAN")
                .antMatchers("/deleteTourCheck/**").hasAuthority("ROLE_LOGISTICIAN")
                .antMatchers("/deleteTour/**").hasAuthority("ROLE_LOGISTICIAN")
                .antMatchers("/showToursOld").hasAuthority("ROLE_LOGISTICIAN")

                .antMatchers("/driver").hasAuthority("ROLE_DRIVER")
                .antMatchers("/showToursDriver").hasAuthority("ROLE_DRIVER")
                .antMatchers("/acceptedOrRejected").hasAuthority("ROLE_DRIVER")
                .antMatchers("/driverTours").hasAuthority("ROLE_DRIVER")
                .antMatchers("/driverProductOrder").hasAuthority("ROLE_DRIVER")

                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN").anyRequest()

                .authenticated().and().csrf().disable().formLogin()
                .loginPage("/login").failureUrl("/login?error=true")
                .defaultSuccessUrl("/index")
                .usernameParameter("email")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").and().exceptionHandling()
                .accessDeniedPage("/access-denied");
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/css/**", "/js/**", "/images/**");
    }



}