package br.com.alura.forum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.config.security.TokenService;
import br.com.alura.forum.controller.form.LoginForm;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authManager;
    // Porém, tem um pequeno detalhe. Essa classe é do Spring, mas ele não consegue
    // fazer a injeção de dependências dela automaticamente, a não ser que nós
    // configuremos isso. Por algum motivo, ela não vem configurada. Podemos fazer
    // isso na nossa classe SecurityConfiguration.

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody @Valid LoginForm form) {
        UsernamePasswordAuthenticationToken dadosLogin = form.converter();

        try {
            Authentication authentication = authManager.authenticate(dadosLogin); // metodo para fazer autenticacao,
            // Quando chegar nessa linha ele vai chamar o metodo da classe
            // AuthenticationServices, que chama o UsuarioRepository para consultar os dados
            // do BD. Se der certo, ele vem para a linha de baixo. Se der errado, vai dar um
            // exception.
            String token = tokenService.gerarToken(authentication); // para gerar o token vamos usar a biblioteca jjwt
            System.out.println(token);
            return ResponseEntity.ok().build();
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
