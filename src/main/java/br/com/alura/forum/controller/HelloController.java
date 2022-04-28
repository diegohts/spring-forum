package br.com.alura.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @RequestMapping("/") // Quando a spring ira chamar esse metodo, redireciona a localhost:8080/
    @ResponseBody // Senao o spring vai considerar o "Hello World" como uma pagina e vai procurar
                  // a pagina no projeto, e colocando isso retorna a string direto no navegador
    public String hello() {
        return "Hello World";
    }

}
