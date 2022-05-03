package br.com.alura.forum.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.controller.form.LoginForm;

// Por agora nao guardar sessao e usarmos o JWT, vamos perder o formulário e também perdemos o controller, que fazia a parte de autenticação. Então por isso vamos fazer esse controller

@RestController
@RequestMapping("/auth") // url que o controller ira responder
public class AutenticacaoController { // Controller com a logica de autenticacao

    @PostMapping // porque eh parametros de autenticacao e senha
    public ResponseEntity<?> autenticar(@RequestBody @Valid LoginForm form) {// metodo que tem a logica de autenticacao
        // Esse metodo vai ser chamado pelo cliente quando ele solicitar os dados de
        // login e senha. A aplicação cliente tem que configurar para chamar esse
        // endereço /auth, a requisição tem que ser via método post, e no corpo da
        // requisição ele tem que mandar o e-mail e a senha. Vamos validar atraves do
        // beanValidation para garantir que esta vindo email e senha

        System.out.println(form.getEmail());
        System.out.println(form.getSenha());

        return ResponseEntity.ok().build();
    }
}
