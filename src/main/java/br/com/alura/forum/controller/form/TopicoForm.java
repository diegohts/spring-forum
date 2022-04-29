package br.com.alura.forum.controller.form;

import br.com.alura.forum.model.Curso;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.CursoRepository;

public class TopicoForm {
    // Definir quais campos sejam fornecidos no formulario cadastrado pelo cliente
    private String titulo;
    private String mensagem;
    private String nomeCurso; // o nome do Curso eh necessario, sendo buscado no banco de dados o atributo
                              // Nome da tabela Curso
    // Data de criacao nao preciso, pois eh instaciado na hora, sendo preenchida ao
    // criar um topico
    // o status do topico ja vem preenchido, como NAO_RESPONDIDO
    // O autor sera o usuario logado

    /**
     * @return String return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return String return the mensagem
     */
    public String getMensagem() {
        return mensagem;
    }

    /**
     * @param mensagem the mensagem to set
     */
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Topico converter(CursoRepository cursoRepository) {
        Curso curso = cursoRepository.findByNome(nomeCurso); // Tenho que pegar o atributo nome da tabela Curso
        return new Topico(titulo, mensagem, curso); // Crio um objeto Topico, porem preciso de um construtor criado no
                                                    // model Topico com os atributos aqui mencionados para cadastrar
    }

}
