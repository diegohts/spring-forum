package br.com.alura.forum.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.alura.forum.model.Curso;
import br.com.alura.forum.model.Topico;

// Endereço que vai devolver a lista com todos os tópicos que estão cadastrados no sistema.
@Controller
public class TopicosController {

    // Vou fazer uma lista para retornar os topicos
    @RequestMapping("/topicos") // quando digitar no navegador localhost:8080/topicos chama esse metodo
    // Nao vamos fazer a consulta no banco de dados, vamos so criar topicos em
    // memoria. Instanciar objetos topicos, guardar numa lista e devolve-los como
    // resposta. Depois vamos fazer pra ter acesso ao banco de dados utilizando JPA.
    @ResponseBody
    public List<Topico> lista() {
        Topico topico = new Topico("Duvida", "Duvida com Spring", new Curso("Spring", "Programacao"));

        return Arrays.asList(topico, topico, topico); // um método estático, em que você pode passar vários objetos
                                                      // separados por vírgula.
        // Ele transforma isso em uma lista e te devolve ela com todos os objetos.
        //
        // Voce percebe que, por padrao, o Spring pega o retorno (a lista) e devolve ela
        // em um formato JSON. Na verdade, nao e o Spring que faz isso. O Spring usa uma
        // biblioteca chamada "Jackson", que faz a conversao de Java para
        // JSON. O Spring usa o Jackson disfarcadamente, "por debaixo dos panos".
    }
}
