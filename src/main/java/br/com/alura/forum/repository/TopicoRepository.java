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
                                                    // Agora imagine a situacao Topico tenha um atributo chamado
                                                    // cursoNome, entao para tirar essa ambiguidade, colocamos o _ para
                                                    // separar pois queremos o nome da tabela curso nao o atributo
                                                    // nomeCurso da tabela topico, entao ficaria a tabela_atributo,
                                                    // desse modo findByCurso_Nome.

    // Esse seria um caso que queira outro nome, o ruim que tenho que montar a query
    // no annotation, o de cima ja vem como convencao e o Spring Data interpreta e
    // sendo bem mais facil
    // @Query("SELECT t FROM Topico t WHERE t.cuso.nome= :nomeCurso")
    // List<Topico> carregarPorNomeDoCurso(@Param("nomeCurso") String nomeCurso);
}
