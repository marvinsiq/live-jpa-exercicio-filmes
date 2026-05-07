package br.com.unipds.filmes.telas;

public enum MenuOpcao {
    BUSCAR_FILME(1, "telaBuscarFilme", "Buscar filme"),
    CADASTRAR_FILME(2, "telaCadastrarFilme", "Incluir filme"),
    LISTAR_FILMES(3, "telaListarFilmes", "Listar filmes"),
    BUSCAR_ATOR(4, "telaBuscarAtor", "Buscar ator"),
    CADASTRAR_ATOR(5, "telaCadastrarAtor", "Incluir ator"),
    LISTAR_ATORES(6, "telaListarAtores", "Listar atores"),
    SAIR(0, null, "Sair");

    private final int codigo;
    private final String nomeBean;
    private final String descricao;

    MenuOpcao(int codigo, String nomeBean, String descricao) {
        this.codigo = codigo;
        this.nomeBean = nomeBean;
        this.descricao = descricao;
    }

    public static MenuOpcao porCodigo(int opcao) {
        for (MenuOpcao opcaoEnum : MenuOpcao.values()) {
            if (opcaoEnum.getCodigo() == opcao) {
                return opcaoEnum;
            }
        }
        return null;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNomeBean() {
        return nomeBean;
    }

    public String getDescricao() {
        return descricao;
    }
}
