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
        if (filme == null) {
            throw new IllegalArgumentException("Filme não pode ser nulo.");
        }
        if (filmesParticipados.contains(filme)) {
            throw new IllegalArgumentException("Este ator já participa neste filme.");
        }
        filmesParticipados.add(filme);
        filme.adicionarAtor(this);
    }

    @Override
    public String getInfo() {
        String info = String.format("Ator: %s, Nacionalidade: %s, Filmes Participados: %d",
                getNome(), getNacionalidade(), filmesParticipados.size());
        if (!filmesParticipados.isEmpty()) {
            info += "\nFilmes Participados:\n";

            List<Filme> filmesOrdenados = new ArrayList<>(filmesParticipados);
            filmesOrdenados.sort((f1, f2) -> f2.getDataLancamento().compareTo(f1.getDataLancamento()));

            for (Filme filme : filmesOrdenados) {
                info += String.format("- %s (%d)\n", filme.getTitulo(), filme.getDataLancamento().getYear());
            }
        } else {
            info += "\nNenhum filme participado.";
        }
        return info;
    }
}