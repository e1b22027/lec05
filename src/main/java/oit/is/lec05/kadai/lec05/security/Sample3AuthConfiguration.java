package oit.is.lec05.kadai.lec05.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class Sample3AuthConfiguration {
  /**
   * 認可処理に関する設定（認証されたユーザがどこにアクセスできるか）
   *
   * @param http
   * @return
   * @throws Exception
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.formLogin(login -> login
        .permitAll())
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/")) // ログアウト後に / にリダイレクト
        .authorizeHttpRequests(authz -> authz
            .requestMatchers(AntPathRequestMatcher.antMatcher("/sample5/**"))
            .authenticated() // /sample4/以下は認証済みであること
            .requestMatchers(AntPathRequestMatcher.antMatcher("/sample58*"))
            .authenticated() // /sample4/以下は認証済みであること
            .requestMatchers(AntPathRequestMatcher.antMatcher("/**"))
            .permitAll())// 上記以外は全員アクセス可能
        .csrf(csrf -> csrf
            .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/*")))// h2-console用にCSRF対策を無効化
        .headers(headers -> headers
            .frameOptions(frameOptions -> frameOptions
                .sameOrigin()));
    return http.build();
  }

  /**
   * 認証処理に関する設定（誰がどのようなロールでログインできるか）
   *
   * @return
   */
  @Bean
  public InMemoryUserDetailsManager userDetailsService() {

    UserDetails customer1 = User.withUsername("customer1")
        .password("{bcrypt}$2y$05$hQz59KuMIFZ6n8iIhG8Zo.xv29GbxNnB2XTjw4axe0lPVjVPokDh2").roles("CUSTOMER").build();
    UserDetails customer2 = User.withUsername("customer2")
        .password("{bcrypt}$2y$05$hQz59KuMIFZ6n8iIhG8Zo.xv29GbxNnB2XTjw4axe0lPVjVPokDh2").roles("CUSTOMER").build();
    UserDetails seller = User.withUsername("seller")
        .password("{bcrypt}$2y$05$hQz59KuMIFZ6n8iIhG8Zo.xv29GbxNnB2XTjw4axe0lPVjVPokDh2").roles("SELLER").build();

    // 生成したユーザをImMemoryUserDetailsManagerに渡す（いくつでも良い）
    return new InMemoryUserDetailsManager(customer1, customer2, seller);
  }

}
