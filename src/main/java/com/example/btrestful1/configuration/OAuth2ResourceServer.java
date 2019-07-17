package com.example.btrestful1.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@EnableResourceServer
@Configuration
public class OAuth2ResourceServer extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
       // http.authorizeRequests().anyRequest().permitAll();

        http.authorizeRequests()
                .antMatchers("/api**").permitAll();
    }

    @Override
    public void configure(final ResourceServerSecurityConfigurer config) {
        //config.tokenServices(remoteTokenServices());
        config.tokenStore(tokenStore());
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        //symmetric key
        converter.setSigningKey("123");
        return converter;
    }

//    @Bean
//    public RemoteTokenServices remoteTokenServices() {
//        final RemoteTokenServices tokenServices = new RemoteTokenServices();
//        tokenServices.setCheckTokenEndpointUrl("http://localhost:8080/oauth/check_token");
//        tokenServices.setClientId("dung");
//        tokenServices.setClientSecret("123");
//        return tokenServices;
//    }


}
