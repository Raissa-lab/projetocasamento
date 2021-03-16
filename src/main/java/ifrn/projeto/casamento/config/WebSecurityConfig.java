package ifrn.projeto.casamento.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests().antMatchers("/**").permitAll()
		.antMatchers("/site/formCasamento").hasRole("CADASTRADO")
		.antMatchers("/site/listarCasamentos").hasRole("EMPRESA")
		.antMatchers("/site/formEmpresa").hasRole("EMPRESA")
		.antMatchers("/site/listarEmpresas").hasRole("EMPRESA")
		.antMatchers("/site/detalhes").hasRole("EMPRESA")
		.antMatchers("/site/detalhesEmpresa").hasRole("ADMINISTRADOR")
		.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll()
		.and().logout().logoutSuccessUrl("/login?logout").permitAll();
	}
}
