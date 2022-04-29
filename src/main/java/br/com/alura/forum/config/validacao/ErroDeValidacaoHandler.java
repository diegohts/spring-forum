package br.com.alura.forum.config.validacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Regra de excecao que vale para qualquer tipo de controller do nosso projeto, como fosse um interceptador onde toda vez que acontecesse uma exception de qualquer metodo de qualquer controller o spring automaticamente chama esse interceptador onde faz o tratamento apropriado.
@RestControllerAdvice // Como o Spring nomeou esse interceptador
public class ErroDeValidacaoHandler { // Classe para fazer o handler, o tratamento dos erros de validacao

    @Autowired
    private MessageSource messageSource; // injeta um atributo do tipo MessageSource, ajudando pegar mensagens de erro

    @ResponseStatus(code = HttpStatus.BAD_REQUEST) // falar para ele qual o status que ele vai devolver e seu codig
                                                   // nesse caso 400 de erro
    @ExceptionHandler(MethodArgumentNotValidException.class) // Metodo chamado quando houver uma excecao dentro de algum
                                                             // controller, que no caso eh tipo de erro de validacao de
                                                             // formulario
    public List<ErroDeFormularioDto> handle(MethodArgumentNotValidException exception) {
        // Toda vez que acontecer alguma exception desse tipo em qualquer RestController
        // do projeto, o Spring vai chamar esse método handle() passando como parâmetro
        // a exception que aconteceu. Tendo esse interceptador, o Spring considera que
        // deu um erro, mas que não vai devolver o código 400 para o cliente. Ele vai
        // chamar o interceptador e, aqui dentro, você faz o tratamento.

        // Esse handler retorna uma lista com cada erro que aconteceu
        List<ErroDeFormularioDto> dto = new ArrayList<>(); // Aqui serve por que quero um array de erros de formulario
                                                           // dto com campo e erro apenas
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors(); // aqui contem todos os erros de
                                                                                      // formulario que aconteceu

        fieldErrors.forEach(e -> { // percorrer os fieldErrors para cada fieldError criar um objeto erro Dto e
                                   // guarda na lista representada pelo dto
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            // Spring ajuda no caso de mensagem de acordo com idioma local
            ErroDeFormularioDto erro = new ErroDeFormularioDto(e.getField(), mensagem);
            dto.add(erro);
        });
        // Pensa que o handler é um interceptador que estou configurando o Spring sempre
        // que encontrar um erro , alguma exception de qualquer controller ele chama
        // automaticamente esse interceptador, passando a lista de erros que ocorreu
        return dto; // Vou devolver uma lista de erro formulario dto
    }
}
