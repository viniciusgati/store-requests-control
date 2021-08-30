package live.gatisoft.storerequestscontrol.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .cors()
                .and()
                .authorizeRequests()
                .mvcMatchers(HttpMethod.POST,"/authentication/**").permitAll() // liberado auth em geral por motivos obvios
//                .mvcMatchers(HttpMethod.POST, "/users").permitAll()
//                .mvcMatchers(HttpMethod.GET, "/companies").permitAll() // liberado buscar empresas e filiais (GET) sem login
//                .mvcMatchers(HttpMethod.POST, "/hello").permitAll()
//                .mvcMatchers(HttpMethod.GET, "/connection_test").permitAll()
                .anyRequest().authenticated();
//                .and()
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // filtra outras requisições para verificar a presença do JWT no header
    }

}
