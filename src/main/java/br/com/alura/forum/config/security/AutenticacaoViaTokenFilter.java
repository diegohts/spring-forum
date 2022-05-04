package br.com.alura.forum.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

// A ideia é que essa lógica tem que rodar antes de cair no meu controller, no meu código. Tenho que interceptar a requisição e executar a lógica. Para fazer isso, vamos criar um filtro. 
public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {
    // OncePerRequestFilter filtro do Spring chamado uma única vez a cada requisição

    // Em classes filter nao eh possivel injetar dependencia, entao vou injetar via
    // construtor recebido da classe SecurityConfigurations

    private TokenService tokenService;

    public AutenticacaoViaTokenFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // metodo para colocar a logica para pegar o token do cabecalho verificar se
        // esta ok, se tiver ok autenticar no Spring

        // recuperar o token do cabecalho,
        String token = recuperarToken(request);
        // verificar se o token esta valido ou nao
        boolean valido = tokenService.isTokenValido(token);

        System.out.println(valido);

        // e se tiver ok, autenticar o usuario para o Spring

        filterChain.doFilter(request, response); // ja rodei o que tinha que rodar nesse filtro, segue o fluxo da
                                                 // requisicao
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization"); // O nome do cabecalho que quero recuperar

        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            // senao tem o cabecalho Authorization ou token ta vazio ou o token nao comeca
            // com Bearer
            return null;
        }
        return token.substring(7, token.length()); // Pegando so o conteudo String apos o Bearer de tamanho 7
    }

}
