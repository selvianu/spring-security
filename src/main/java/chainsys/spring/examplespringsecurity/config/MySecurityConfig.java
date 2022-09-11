package chainsys.spring.examplespringsecurity.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.jaas.memory.InMemoryConfiguration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// customize for multiple users
		auth.inMemoryAuthentication().withUser("selvi").password("123456").authorities("admin").and().withUser("nithin")
				.password("2022").authorities("user").and().passwordEncoder(NoOpPasswordEncoder.getInstance());
//another way to customize for multiple clients
		InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
		UserDetails user1 = User.withUsername("selvi").password("123456").authorities("admin").build();
		UserDetails user2 = User.withUsername("nithin").password("2022").authorities("user").build();
		userDetailsService.createUser(user1);
		userDetailsService.createUser(user2);
		auth.userDetailsService(userDetailsService);

	}

	@Bean
	public PasswordEncoder passwordEncoder () {
		return NoOpPasswordEncoder.getInstance();
	}
	@Bean
	public UserDetailsService userDetailsService(DataSource datasource) {
		return new JdbcUserDetailsManager(datasource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * authetication based http .authorizeRequests()
		 * .antMatchers("/dashboard").authenticated() .antMatchers("/home").permitAll()
		 * .and() .formLogin() .and() .httpBasic();
		 */
		/*
		 * to deny all request http.authorizeRequests() .anyRequest() .denyAll() .and()
		 * .httpBasic();
		 */

		/*
		 * //pages restrictive to admin and users.if user - permitall and it admin -
		 * deny all http .authorizeRequests() .antMatchers("/user/**") .permitAll()
		 * .antMatchers("/admin/**") .denyAll() .and() .httpBasic();
		 */

		// for multiple users - update configure(auth) and then update configure(http)
		http.authorizeRequests().antMatchers("/user/home").permitAll().antMatchers("/user/dashboard").authenticated()
				.and().httpBasic();

	}
}
