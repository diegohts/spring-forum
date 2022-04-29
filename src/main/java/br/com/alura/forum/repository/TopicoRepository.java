package br.com.alura.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.forum.model.Topico;

//Quando você herda dessa interface, percebe que ela tem um generics que você tem que passar dois tipos. 
//O primeiro é a entidade com que o JpaRepository vai trabalhar (no nosso caso é Topico). 
//E o segundo é qual o tipo do atributo do ID, da chave primária dessa entidade. No nosso caso, estamos usando o Long.
public interface TopicoRepository extends JpaRepository<Topico, Long> {

    // Só precisamos criar essa interface herdando JpaRepository, os generics, e ela
    // fica vazia. Na verdade, como estou herdando, toda vez que herdo ganho tudo da
    // interface ou da classe que estou herdando. Essa interface JpaRepository já
    // tem vários métodos comuns. Daí que vem a facilidade. Você não precisa
    // implementar esses métodos, porque eles são comuns neste tipo de classe
}
