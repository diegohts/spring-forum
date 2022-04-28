package br.com.alura.forum.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.model.Curso;
import br.com.alura.forum.model.Topico;

@RestController // Anotacao e justamente para dizer que o @controller e um @RestController.
                // Assumindo que todo metodo ja vai ter o @ResponseBody. Se usarmos
                // o @RestController, nao precisamos mais ficar colocando o @ResponseBody em
                // cima dos metodos. Esse sera o comportamento padrão de todos os
                // metodos.
public class TopicosController {

    @RequestMapping("/topicos")
    public List<TopicoDto> lista() { // Com List<Topico> ele sempre vai devolver todos os atributos, você não tem
                                     // flexibilidade
        // Entao usamos o "DTO", Data Transfer Object, classe de valor, que so tem
        // aqueles atributos que quero devolver
        Topico topico = new Topico("Dúvida", "Dúvida com Spring", new Curso("Spring", "Programacao"));

        return TopicoDto.converter(Arrays.asList(topico, topico, topico)); // Controller esta reclamando porque esse
                                                                           // metodo tem que devolver
        // uma lista de <TopicoDto>, e nao mais de Topico

    }
}
