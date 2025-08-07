import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Diretor extends Pessoa {
    private List<Filme> filmesDirigidos;

    public Diretor(String nome, LocalDate dataNascimento, String nacionalidade) {
        super(nome, dataNascimento, nacionalidade);
        this.filmesDirigidos = new ArrayList<>();
    }

    public void dirigirFilme(Filme filme) {
        if (filme == null) {
            throw new IllegalArgumentException("Filme não pode ser nulo.");
        }
        if (filmesDirigidos.contains(filme)) {
            throw new IllegalArgumentException("Este filme já é dirigido por este diretor.");
        }
        filmesDirigidos.add(filme);
        filme.setDiretor(this);
    }

    @Override
    public String getInfo() {
        String info =  String.format("Diretor: %s, Nacionalidade: %s, Filmes Dirigidos: %d",
                getNome(), getNacionalidade(), filmesDirigidos.size());
        if (!filmesDirigidos.isEmpty()) {
            info += "\nFilmes Dirigidos:\n";

            List<Filme> filmesOrdenados = new ArrayList<>(filmesDirigidos);
            filmesOrdenados.sort((f1, f2) -> f2.getDataLancamento().compareTo(f1.getDataLancamento()));

            for (Filme filme : filmesOrdenados) {
                info += String.format("- %s (%d)\n", filme.getTitulo(), filme.getDataLancamento().getYear());
            }
        } else {
            info += "\nNenhum filme dirigido.";
        }
        return info;
    }
}