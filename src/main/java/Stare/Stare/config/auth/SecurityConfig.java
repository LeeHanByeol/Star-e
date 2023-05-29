package Stare.Stare.config.auth;


import Stare.Stare.handler.LoginFailureHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig{

    @Bean
    protected SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception{

        //disable csrf
        http
                .csrf().disable();

        http
                .authorizeRequests()
                    .antMatchers("/users/**", "/feeds/**", "/follow/**").authenticated()
                    .antMatchers("/").permitAll()           //Concern 모든 사람이 포스트 볼 수 있도록

                .and()                                              //로그인
                .formLogin()
                    .loginPage("/login")                            //GET
                    .loginProcessingUrl("/login")                   //POST -> SpringSecurity에게 맡겼음
                    .failureHandler(loginFailureHandler())
                    .defaultSuccessUrl("/")

                .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true);


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public LoginFailureHandler loginFailureHandler(){
        return new LoginFailureHandler();
    }

    /*
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
    */

}
