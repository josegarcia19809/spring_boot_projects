package com.example.empleos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity {

    //@Bean
//    UserDetailsManager users(DataSource dataSource) {
//
//        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//        return users;
//    }

    @Bean
    public UserDetailsManager usersCustom(DataSource dataSource) {

        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        users.setUsersByUsernameQuery("select username, password, estatus from " +
                "usuarios u where username=?");
        users.setAuthoritiesByUsernameQuery("select u.username, p.perfil from " +
                "usuario_perfil up " +
                "inner join usuarios u on u.id = up.id_usuario " + "inner join " +
                "perfiles p on p.id = up.id_perfil " + "where u.username=?");
        return users;
    }
}
