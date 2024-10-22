package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/login", "/register", "/resources/**").permitAll().anyRequest()
                                .authenticated())
                .formLogin(login -> login
                        .loginPage("/login") // Đường dẫn tới trang đăng nhập
                        .defaultSuccessUrl("/home", true) // Điều hướng đến /home sau khi đăng nhập thành công
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL để logout
                        .logoutSuccessUrl("/logout-success") // URL chuyển hướng sau khi logout thành công
                        .invalidateHttpSession(true) // Hủy session
                        .deleteCookies("JSESSIONID") // Xóa cookie chứa session ID
                        .permitAll())
                .sessionManagement(ss -> ss
                        .sessionFixation().migrateSession() // Quản lý session mới khi đăng nhập lại
                        .maximumSessions(1) // Giới hạn mỗi tài khoản chỉ có 1 session
                        .expiredUrl("/login?expired=true"));// Đường dẫn khi session hết hạn

        return http.build();
    }

    // Tạo người dùng mặc định cho thử nghiệm
    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build()
        );
    }
}
