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
        filmesDirigidos.add(filme);
        filme.setDiretor(this);
    }

    @Override
    public String getInfo() {
        return String.format("Diretor: %s, Nacionalidade: %s, Filmes Dirigidos: %d",
                getNome(), getNacionalidade(), filmesDirigidos.size());
    }
}