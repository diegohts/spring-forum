package br.com.alura.forum.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.forum.model.Topico;

public class TopicoDto {
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;

    // Toda vez que eu der new no topicoDto, passo como parametro
    // um objeto do tipo topico. Isto eh, TopicoDto(Topico topico) Dentro do Topico,
    // ja tenho as informacoes: o "id", o "título" e a "mensagem". Recebo o topico
    // e, a partir desse topico, preencho os atributos.
    public TopicoDto(Topico topico) {
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
    }

    /**
     * @return long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return String return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @return String return the mensagem
     */
    public String getMensagem() {
        return mensagem;
    }

    /**
     * @return LocalDateTime return the dataCriacao
     */
    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public static List<TopicoDto> converter(List<Topico> topicos) {
        return topicos.stream().map(TopicoDto::new).collect(Collectors.toList());
        // A função do mapeamento será TopicoDto::new, porque ele vai chamar o
        // construtor que recebe o próprio tópico como parâmetro. No final, tenho que
        // transformar isso em uma lista, então vou encadear a chamada para o método
        // collect(), passando collectors.toList() para transformar numa lista.
        // Essa é a sintaxe do Java 8. Sem ele, teríamos que pegar essa lista de
        // tópicos, fazer um for para cada tópico, dar new no topicoDto, guardar em uma
        // lista de topicoDto e devolver essa lista de topicoDto no final. Desse jeito,
        // ele faz tudo isso em uma linha só usando API de strings do Java 8.
    }

}
