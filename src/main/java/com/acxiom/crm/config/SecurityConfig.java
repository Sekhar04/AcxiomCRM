package com.acxiom.crm.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
  @Bean PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

  @Bean UserDetailsService users(PasswordEncoder encoder) {
    UserDetails admin = User.withUsername("admin").password(encoder.encode("Admin@123")).roles("ADMIN").build();
    UserDetails manager = User.withUsername("manager").password(encoder.encode("Manager@123")).roles("MANAGER").build();
    UserDetails sales = User.withUsername("sales").password(encoder.encode("Sales@123")).roles("SALES").build();
    return new InMemoryUserDetailsManager(admin, manager, sales);
  }

  @Bean SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(auth -> auth
        .requestMatchers("/login", "/css/**", "/h2-console/**").permitAll()
        .requestMatchers("/api/**").hasAnyRole("ADMIN", "MANAGER", "SALES")
        .requestMatchers("/admin/**").hasRole("ADMIN")
        .anyRequest().authenticated())
      .formLogin(form -> form.defaultSuccessUrl("/", true).permitAll())
      .logout(Customizer.withDefaults())
      .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
      .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));
    return http.build();
  }
}
