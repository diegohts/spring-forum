package br.com.alura.forum.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.alura.forum.model.Curso;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // String nao substitua o meu BD que estou configurando na app, como estamos usando o h2 nao mudara nada. Pois o h2 eh um BD em memoria, mas no application properties colocamos as configuracoes do mysql
@ActiveProfiles("test")
public class CursoRepositoryTest {

    @Autowired // Anotacao do spring para fazer injecao de dependencias
    private CursoRepository repository;

    @Autowired // Aqui vou considerar que o banco esta vazio, para o teste tenho que popular ele
    private TestEntityManager em;

    @Test
    public void deveriaCarregarUmCursoAoBuscarPeloSeuNome() {
        String nomeCurso = "HTML 5"; //testando o nome desse curso

        Curso html5 = new Curso(); // criando o cruso
        html5.setNome(nomeCurso);
        html5.setCategoria("Programacao");
        em.persist(html5); // populando o banco vazio com o curso html5

        Curso curso = repository.findByNome(nomeCurso); // buscando no banco se tem esse curso HTML 5

        Assert.assertNotNull(curso); // A variavel curso nao pode vim nula
        Assert.assertEquals(nomeCurso, curso.getNome()); // Verificar se o nomeCurso eh exatamente igual ao curso buscado no banco
    }    

    @Test
    public void naoDeveriaCarregarUmCursoCujoNomeNaoEstejaCadastrado() {
        String nomeCurso = "JPA"; //testando o nome desse curso que nao esta cadastrado no BD
        Curso curso = repository.findByNome(nomeCurso); // buscando no BD se tem esse curso JPA

        Assert.assertNull(curso); // A variavel curso nao tem que vim nula, pois nao tem esse curso no BD
    }    
}
