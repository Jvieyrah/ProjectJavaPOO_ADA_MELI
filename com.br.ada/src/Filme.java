import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Filme {
    private String titulo;
    private LocalDate dataLancamento;
    private double orcamento;
    private String descricao;
    private Diretor diretor;
    private List<Ator> atores;

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

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título não pode ser nulo ou vazio");
        }
        this.titulo = titulo;
    }
    public void setDataLancamento(LocalDate dataLancamento) {
        if (dataLancamento == null) {
            throw new IllegalArgumentException("Data de lançamento não pode ser nula");
        }
        this.dataLancamento = dataLancamento;
    }

    public void setOrcamento(double orcamento) {
        if (orcamento < 0) {
            throw new IllegalArgumentException("Orçamento não pode ser negativo");
        }
        this.orcamento = orcamento;
    }

    public void setDescricao(String descricao) {
        if (descricao == null) {
            throw new IllegalArgumentException("Descrição não pode ser nula");
        }
        this.descricao = descricao;
    }


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
        if (atores.contains(ator)) {
            throw new IllegalArgumentException("Este ator já está associado a este filme");
        }
        atores.add(ator);
    }

    public boolean removerAtor(Ator ator) {
        return atores.remove(ator);
    }

    // Método de informação formatada
    public String getInfo() {
        String nomeDiretor = (diretor != null) ? diretor.getNome() : "Não definido";
        String info =  String.format("Filme: %s | Diretor: %s | %d ator(es) | Lançamento: %s | Orçamento: $%,.2f",
                titulo,
                nomeDiretor,
                atores.size(),
                dataLancamento.toString(),
                orcamento);

        if (!atores.isEmpty()) {
            info += "\nAtores:\n";
            for (Ator ator : atores) {
                info += String.format("- %s (%s)\n", ator.getNome(), ator.getNacionalidade());
            }
        } else {
            info += "\nNenhum ator associado.";
        }
        return info;
    }

    @Override
    public String toString() {
        return getInfo();
    }
}