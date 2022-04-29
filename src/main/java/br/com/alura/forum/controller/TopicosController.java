package br.com.alura.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;

@RestController
@RequestMapping(value = "/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository TopicoRepository;

    @Autowired
    private CursoRepository cursoRepository; // Injetando CursoRepository

    // Agora usaremos os verbos HTTP, para diferenciar essa ambiguidade ao chamar os
    // /topicos e tem 2 modos de fazer isso
    // @RequestMapping(value = "/topicos", method = RequestMethod.GET) primeiro modo
    // seria assim diferenciando do metodo
    // cadastrar o POST.
    // O segundo modo eh melhor e como aqui chama sempre o /topicos, menciono em
    // cima dessa classe e depois mapeado atraves dos verbos, igual a baixo
    @GetMapping
    public List<TopicoDto> lista(String nomeCurso) {
        if (nomeCurso == null) {
            List<Topico> topicos = TopicoRepository.findAll();
            return TopicoDto.converter(topicos);
        } else {
            List<Topico> topicos = TopicoRepository.findByCursoNome(nomeCurso);
            return TopicoDto.converter(topicos);
        }
    }

    // @RequestMapping(value = "/topicos", method = RequestMethod.POST)
    @PostMapping
    public void cadastrar(@RequestBody TopicoForm form) { // Agora preciso receber as informacoes de um formulario do
                                                          // topico que quero cadastrar. Na teoria poderia cadastrar
                                                          // todos os atributos dos Topicos, mas os necessarios sao os
                                                          // atributos TopicoDto entao para diferenciar usarei o
                                                          // TopicoForm.
        // TopicoDto = são dados que saem da API de volta para o cliente.
        // TopicoForm = são dados que chegam do cliente para a API

        // Na hora de cadastrar, eles não vêm na barra de endereços, não vêm na URL.
        // Eles vêm no corpo da requisição.
        // A requisição é via método POST, não método GET. Então eu não mando os
        // parâmetros via URL. Os parâmetros vão no corpo da requisição. Preciso avisar
        // isso para o Spring. Temos que colocar uma anotação no parâmetro TopicoForm
        // topico, que é o @RequestBody

        // save = salva no banco, porem espera um Topico e nao um form, precisa salvar o
        // topico da entidade. Entao teremos que converter.
        // Na maneira de cima no metodo lista convertemos a lista de topicos e converteu
        // para TopicoDto.
        // Aqui sera o contrario vou receber o form e converter para topico
        Topico topico = form.converter(cursoRepository); // tenho que passar um CursoRepository injetando
        TopicoRepository.save(topico);
    }
}
