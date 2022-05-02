package br.com.alura.forum.controller.dto;

import java.time.LocalDateTime;
import org.springframework.data.domain.Page;

import br.com.alura.forum.model.Topico;

public class TopicoDto {
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;

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

    public static Page<TopicoDto> converter(Page<Topico> topicos) {
        return topicos.map(TopicoDto::new);
    }

}
