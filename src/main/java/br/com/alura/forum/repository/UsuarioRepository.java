package br.com.alura.forum.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.forum.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // todo repository tme que da um extends JpaRepository e passar nos generics
    // qual eh a entidade e qual tipo da chave primaria

    Optional<Usuario> findByEmail(String email); // metodo que faz a busca pelo email, o optional serve para que pode
                                                 // ser que encontre usuario ou nao

}
