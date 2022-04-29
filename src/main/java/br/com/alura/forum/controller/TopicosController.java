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
    private TopicoRepository TopicoRepository;

    @RequestMapping("/topicos")
    public List<TopicoDto> lista(String titulo) { // No modo anterior trazia todos os registros. Às vezes queremos
                                                  // filtrar. Filtrando nesse caso pelo Titulo um atributo do
                                                  // TopicoDto
        if (titulo == null) { // se nao esta vindo o parâmetro titulo vou chamar o findAll
            List<Topico> topicos = TopicoRepository.findAll();
            return TopicoDto.converter(topicos);
        } else {
            List<Topico> topicos = TopicoRepository.findByTitulo(titulo); // Se você seguir esse padrão, ele
                                                                          // consegue gerar a Query para você
                                                                          // automaticamente.
                                                                          // O padrão de nomenclatura seria findBy()
                                                                          // e o nome do atributo na entidade (do
                                                                          // atributo que você quer filtrar). Por
                                                                          // exemplo, imagine que quero filtrar pelo
                                                                          // Titulo, o nome do método seria
                                                                          // findByTitulo(titulo) e eu passaria
                                                                          // o parâmetro que representa esse
                                                                          // atributo.
            return TopicoDto.converter(topicos);
        }

    }
}
