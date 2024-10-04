package com.example.config;

import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
//@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
//	@Bean
//	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//		http
//			//httpリクエストに対するセキュリティ設定
//			.authorizeHttpRequests(authz -> authz
//			//「/login」へのアクセスは認証を必要としない
//			.requestMatchers("/login").permitAll()
//			//【管理者権限設定】url:/todos/**は管理者しかアクセスできない
//			.requestMatchers("/todos/**").hasAuthority("ADMIN")
//			//その他のリクエストは承認が必要
//			.anyRequest().authenticated())
//			//フォームベースのログイン設定
//			.formLogin(form -> form
//			//カスタムログインページのURLを指定
//			.loginPage("/login")
//			//ログイン処理のURLを指定
//			.loginProcessingUrl("/authentication")
//			//ユーザー名のname属性を指定
//			.usernameParameter("usernameInput")
//			//パスワードのname属性を指定
//			.passwordParameter("passwordInput")
//			//ログイン成功時のリダイレクト先を指定
//			.defaultSuccessUrl("/")
//			//ログイン失敗時のリダイレクト先を指定
//			.failureUrl("/login?error"))
//			//ログアウト設定
//			.logout(logout -> logout
//			//ログアウトを処理するURLを指定
//			.logoutUrl("/logout")
//			//ログアウト成功時のリダイレクト先を指定
//			.logoutSuccessUrl("/login?logout")
//			//ログアウト時にセッションを無効にする
//			.invalidateHttpSession(true)
//			//ログアウト時にCookieを削除する
//			.deleteCookies("JSESSIONID")
//		);
//		return http.build();
//	}

}
