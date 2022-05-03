package br.com.alura.forum.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    @Autowired
    private AutenticacaoService autenticacaoService;

    // Configuracoes de autenticacao
    // A parte de controle de acesso, de login, fica nesse método
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // classe onde tem a lógica de autenticação
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
        // usaremos o bcrypt algoritmo de hash
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/topicos").permitAll()
                .antMatchers(HttpMethod.GET, "/topicos/*").permitAll()
                .antMatchers(HttpMethod.POST, "/auth").permitAll() // liberando o auth, url de login
                .anyRequest().authenticated() // tiramos o and().formLogin, porque senão vai criar sessao
                .and().csrf().disable() // crsf é uma abreviação para um tipo de ataque hacker que acontece na web, só
                                        // que como vamos fazer autenticação via token automaticamente da livre desse
                                        // ataque e entao desabilitamos
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // metodo informando que nao queremos usar sessao, entao qual vai ser a politica
        // de criacao de sessao de forma STATELESS
    }

    // Configurações de recursos estáticos
    // São requisições para arquivo ( js, css, imagens, etc).
    // Não é nosso caso, já que estamos desenvolvendo só a parte do backend
    @Override
    public void configure(WebSecurity web) throws Exception {

    }

    // Foi usado para saber a senha hash usado para o banco de dados data.sql
    // public static void main(String[] args) {
    // System.out.println(new BCryptPasswordEncoder().encode("123456"));
    // }

}
