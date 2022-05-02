package br.com.alura.forum.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity // Habilitando o modulo de seguranca
@Configuration // Spring vai ler as configuracoes presente nessa classe
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {
    // Ao utilizar WebSecurityConfigurerAdapter vai ter alguns metodos pra fazer as
    // configuracoes que vamos sobrescrever posteriormente

    // Configuracoes de autenticacao
    // A parte de controle de acesso, de login, fica nesse método
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    }

    // Configuracoes de Autorizacao
    // Quem pode acessar cada url, perfil de acesso
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Nesse projeto vamos liberar o que lista todos os topicos (/topicos) e que o
        // que detalha um topico especifico (/topicos/{id})
        // Os outros 3 para cadastrar, atualizar e remover vou querer restringir,
        // nao ficando publico
        // Liberando acesso aos endpoints publicos
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/topicos").permitAll() // Permitir o metodo get do /topicos permiti acesso
                .antMatchers(HttpMethod.GET, "/topicos/*").permitAll(); // Permiti ao get do /topicos/{alguma coisa}
    }

    // Configurações de recursos estáticos
    // São requisições para arquivo ( js, css, imagens, etc).
    // Não é nosso caso, já que estamos desenvolvendo só a parte do backend
    @Override
    public void configure(WebSecurity web) throws Exception {

    }

}
