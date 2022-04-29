package br.com.alura.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.TopicoRepository;

@RestController
public class TopicosController {

    @Autowired
    private TopicoRepository TopicoRepository; // preciso injetar o JpaRepository que acabei de criar. Para fazer a
                                               // injeção, uso o mesmo esquema do Spring @Autowired. Você declara um
                                               // atributo, que no nosso caso vai ser do tipo topicoRepository, que é
                                               // nossa interface, e aí vou chamar de topicoRepository o atributo. Vou
                                               // só importar o @Autowired e o TopicoRepository. Está pronta a injeção
                                               // de dependências como em qualquer outra classe.

    @RequestMapping("/topicos")
    public List<TopicoDto> lista() { // preciso que retorne a lista de topicos com os atributos mencionados no
                                     // TopicoDto
        List<Topico> topicos = TopicoRepository.findAll(); // Quando injeto o topicoRepository e chamo o método
                                                           // findAll(), que nem fomos nós que escrevemos - se olharmos
                                                           // lá no nosso TopicoRepository.java, está vazio, porque ele
                                                           // já veio herdado do JpaRepository. Essa é uma comodidade
                                                           // para quem trabalha com JPA, não precisar criar mais as
                                                           // classes seguindo o padrão DAO. Usa o Repository e deixa o
                                                           // seu código muito menor, muito mais simples e fácil de
                                                           // fazer manutenção.

        return TopicoDto.converter(topicos); // Na hora de chamar o TopicoDto.converter(), preciso passar uma lista
                                             // chamada topicos, que busca todos os topicos no banco mostrando atraves
                                             // do TopicoDto os atributos que quero mostrar

    }
}
