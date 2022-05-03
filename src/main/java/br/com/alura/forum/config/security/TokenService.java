package br.com.alura.forum.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.alura.forum.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

    @Value("${forum.jwt.expiration}")
    private String expiration; // Injetar uma propriedade que esta no application.properties

    @Value("${forum.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication) {

        Usuario logado = (Usuario) authentication.getPrincipal();
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
        // pegar o milisegundos de hoje + o expiration 86400000ms que eh 1 dia,
        // configurado no application.properties

        return Jwts.builder() // Método que cria um objeto builder onde posso setar informações para ele
                              // construir o token
                .setIssuer("API do Fórum da Alura") // Quem eh a aplicacao que esta fazendo a geracao de token
                .setSubject(logado.getId().toString()) // Quem eh o dono desse token, usuario a quem esse token
                                                       // pertence, id do usuario logado
                .setIssuedAt(hoje) // Data geracao do token
                .setExpiration(dataExpiracao) // Data de validacao, uma data que ele vai expirar
                .signWith(SignatureAlgorithm.HS256, secret) // o token precisa ser criptografado, entao preciso dizer o
                // algoritmo de criptografia e senha da minha aplicação
                // É aqui que vou injetar o secret, que está no application.properties. A ideia
                // é pegar um programa que gera uma string aleatória gigantesca e usar como
                // senha. Para não ficar isso no código, podemos colocar no
                // application.properties.
                .compact(); // para compactar e usar como string
    }

}
