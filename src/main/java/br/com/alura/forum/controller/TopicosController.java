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
    public List<TopicoDto> lista(String titulo) {
        if (titulo == null) {
            List<Topico> topicos = TopicoRepository.findAll();
            return TopicoDto.converter(topicos);
        } else {
            List<Topico> topicos = TopicoRepository.findByTitulo(titulo);
            return TopicoDto.converter(topicos);
        }

    }
}
