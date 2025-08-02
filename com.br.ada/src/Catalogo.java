import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Catalogo {
    private List<Filme> filmes;
    private List<Diretor> diretores;
    private List<Ator> atores;

    public Catalogo() {
        this.filmes = new ArrayList<>();
        this.diretores = new ArrayList<>();
        this.atores = new ArrayList<>();
    }

    public void cadastrarFilme(Filme filme) {
        filmes.add(filme);
    }

    public void cadastrarDiretor(Diretor diretor) {
        diretores.add(diretor);
    }

    public void cadastrarAtor(Ator ator) {
        atores.add(ator);
    }

    public List<Filme> buscarFilmePorNome(String nome) {
        return filmes.stream()
                .filter(f -> f.getTitulo().toLowerCase().contains(nome.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Outros métodos necessários
}