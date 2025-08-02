import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Ator extends Pessoa {
    private List<Filme> filmesParticipados;

    public Ator(String nome, LocalDate dataNascimento, String nacionalidade) {
        super(nome, dataNascimento, nacionalidade);
        this.filmesParticipados = new ArrayList<>();
    }

    public void atuarFilme(Filme filme) {
        filmesParticipados.add(filme);
        filme.adicionarAtor(this);
    }

    @Override
    public String getInfo() {
        return String.format("Ator: %s, Nacionalidade: %s, Filmes Participados: %d",
                getNome(), getNacionalidade(), filmesParticipados.size());
    }
}