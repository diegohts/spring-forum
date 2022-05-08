package br.com.alura.forum.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.alura.forum.model.Curso;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CursoRepositoryTest {

    @Autowired // Anotacao do spring para fazer injecao de dependencias
    private CursoRepository repository;

    @Test
    public void deveriaCarregarUmCursoAoBuscarPeloSeuNome() {
        String nomeCurso = "HTML 5"; //testando o nome desse curso
        Curso curso = repository.findByNome(nomeCurso); // buscando no BD se tem esse curso HTML 5

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
