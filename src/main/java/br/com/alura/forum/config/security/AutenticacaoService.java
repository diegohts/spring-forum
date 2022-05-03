package br.com.alura.forum.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.alura.forum.model.Usuario;
import br.com.alura.forum.repository.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService { // Service de lógica de autenticação

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Quando entrarmos no formulário de login, digitar o e-mail e clicarmos no
        // botão de login, o Spring vai saber que essa é a classe autenticação e vai
        // chamar esse método. Por isso precisamos da interface. Ele vai procurar esse
        // método, que recebe como parâmetro o e-mail que digitamos na tela de login.
        // E a senha? Para o Spring, ele só passa o e-mail e nós temos que fazer a
        // consulta no banco de dados filtrando só por isso. A senha ele faz em memória.
        // No geral, em uma aplicação já fazemos a consulta no banco filtrando pelo
        // usuário e pela senha.
        // No Spring security é diferente. Nós buscamos pelo e-mail e o Spring faz a
        // checagem da senha em memória.
        Optional<Usuario> usuario = repository.findByEmail(username); // o email que o usuario passou na tela de login

        if (usuario.isPresent()) { // Quer dizer que encontrei usuario
            return usuario.get(); // Para pegar defato o usuario
        }

        throw new UsernameNotFoundException("Dados Inválidos!");
        // Expections para falar com o Spring que o usuario nao foi encontrado
    }

}
