package br.com.alura.forum.config.validacao;

public class ErroDeFormularioDto {
    private String campo;
    private String erro;

    // Essa classe representa erro de validação. O JSON, que vai ser devolvido para
    // o cliente, não vai ser mais aquele imenso do Spring. Vai ser o JSON
    // representado por essa classe.
    public ErroDeFormularioDto(String campo, String erro) {
        // Representando o erro de algum campo
        this.campo = campo; // campo que ocorreu o erro
        this.erro = erro; // e a mensagem de erro
    }

    /**
     * @return String return the campo
     */
    public String getCampo() {
        return campo;
    }

    /**
     * @param campo the campo to set
     */
    public void setCampo(String campo) {
        this.campo = campo;
    }

    /**
     * @return String return the erro
     */
    public String getErro() {
        return erro;
    }

    /**
     * @param erro the erro to set
     */
    public void setErro(String erro) {
        this.erro = erro;
    }

}
