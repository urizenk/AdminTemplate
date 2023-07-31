package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateException;

/**
 * @author yf
 * @create 2023-07-21 19:59
 * 开启认证服务器功能
 */
@EnableAuthorizationServer
@Configuration
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .tokenStore(jwtTokenStore())
                .tokenEnhancer(jwtAccessConverter());
        super.configure(endpoints);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("admin-api")
                .secret(passwordEncoder.encode("admin-secret"))
                .scopes("all")
                .authorizedGrantTypes("authorization_code", "refresh_token")
                //生产上定向到nginx地址
                .redirectUris("http://localhost:8080/login/oauth2/code/admin-client")
                .accessTokenValiditySeconds(7 * 24 * 3600)
                .refreshTokenValiditySeconds(30 * 24 * 3600)
                .and()
                .withClient("inside-app")
                .secret(passwordEncoder.encode("inside-secret"))
                .scopes("all")
                .authorizedGrantTypes("client_credentials")
                .accessTokenValiditySeconds(Integer.MAX_VALUE);

        super.configure(clients);
    }

    private TokenStore jwtTokenStore() throws CertificateException, KeyStoreException, IOException {
        JwtTokenStore jwtTokenStore = new JwtTokenStore(jwtAccessConverter());
        return jwtTokenStore;
    }

    private JwtAccessTokenConverter jwtAccessConverter() throws CertificateException, KeyStoreException, IOException {
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();

        //通过本地的密钥对加载
        ClassPathResource resource = new ClassPathResource("jwt.jks");

        KeyStoreKeyFactory keyFactory = new KeyStoreKeyFactory(resource, "123456".toCharArray());
        tokenConverter.setKeyPair(keyFactory.getKeyPair("jwt", "123456".toCharArray()));
        return tokenConverter;
    }


}
