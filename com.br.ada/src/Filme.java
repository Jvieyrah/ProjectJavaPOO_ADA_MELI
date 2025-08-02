import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Filme {
    private final String titulo;
    private final LocalDate dataLancamento;
    private final double orcamento;
    private final String descricao;
    private Diretor diretor;
    private final List<Ator> atores;

    public Filme(String titulo, LocalDate dataLancamento, double orcamento, String descricao) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título não pode ser nulo ou vazio");
        }
        if (dataLancamento == null) {
            throw new IllegalArgumentException("Data de lançamento não pode ser nula");
        }
        if (orcamento < 0) {
            throw new IllegalArgumentException("Orçamento não pode ser negativo");
        }
        if (descricao == null) {
            throw new IllegalArgumentException("Descrição não pode ser nula");
        }

        this.titulo = titulo;
        this.dataLancamento = dataLancamento;
        this.orcamento = orcamento;
        this.descricao = descricao;
        this.atores = new ArrayList<>();
    }

    // Getters
    public String getTitulo() {
        return titulo;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public double getOrcamento() {
        return orcamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public Diretor getDiretor() {
        return diretor;
    }

    public List<Ator> getAtores() {
        return new ArrayList<>(atores); // Retorna cópia para evitar modificações externas
    }

    // Setters com validação
    public void setDiretor(Diretor diretor) {
        if (diretor == null) {
            throw new IllegalArgumentException("Diretor não pode ser nulo");
        }
        this.diretor = diretor;
    }

    // Métodos para manipulação de atores
    public void adicionarAtor(Ator ator) {
        if (ator == null) {
            throw new IllegalArgumentException("Ator não pode ser nulo");
        }
        atores.add(ator);
    }

    public boolean removerAtor(Ator ator) {
        return atores.remove(ator);
    }

    // Método de informação formatada
    public String getInfo() {
        String nomeDiretor = (diretor != null) ? diretor.getNome() : "Não definido";
        return String.format("Filme: %s | Diretor: %s | %d ator(es) | Lançamento: %s | Orçamento: $%,.2f",
                titulo,
                nomeDiretor,
                atores.size(),
                dataLancamento.toString(),
                orcamento);
    }

    @Override
    public String toString() {
        return getInfo();
    }
}