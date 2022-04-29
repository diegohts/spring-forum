package br.com.alura.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.forum.model.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    List<Topico> findByCursoNome(String nomeCurso); // Percebe-se que acima ainda é a tabela Topico, mas o Curso esta
                                                    // incluso nessa tabela e pode ser acessado e filtrado atraves
                                                    // ?nomeCurso=HMTL 5
                                                    // Com isso traz as informacoes do topico referente a esse Curso
                                                    // filtrado

}
