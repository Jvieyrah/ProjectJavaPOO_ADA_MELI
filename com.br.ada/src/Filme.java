import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Filme {
    private String titulo;
    private LocalDate dataLancamento;
    private double orcamento;
    private String descricao;
    private List<Diretor> diretores;
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
        this.diretores = new ArrayList<>();
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

    public List<Diretor> getDiretores() {
        return diretores;
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
        if (!diretores.contains(diretor)) {
            diretores.add(diretor);
            diretor.dirigirFilme(this);
        }
    }


    public boolean verificarOuAdicionarDiretor(Diretor diretor) {
        if (diretor == null) {
            throw new IllegalArgumentException("Diretor não pode ser nulo");
        }

        if (this.diretores == null) {
            this.diretores = new ArrayList<>();
        }

        if (!this.diretores.contains(diretor)) {
            this.diretores.add(diretor);
            diretor.dirigirFilme(this);
            return true;
        }

        System.out.println("Este diretor já está associado ao filme: " + this.getTitulo());
        return false;
    }



    // Métodos para manipulação de atores
    public void adicionarAtor(Ator ator) {
        if (ator == null) {
            throw new IllegalArgumentException("Ator não pode ser nulo");
        }

        if (!atores.contains(ator)) {
            atores.add(ator);
        } else {
            System.out.println("Este ator já está associado a este filme.");
        }
    }

    public boolean removerAtor(Ator ator) {
        return atores.remove(ator);
    }

    // Método de informação formatada
    public String getInfo() {
        String nomeDiretores = (diretores.isEmpty()) ? "Não definido" :
                diretores.stream()
                        .map(Diretor::getNome)
                        .collect(Collectors.joining(", "));
        String info = String.format("Filme: %s | Diretores: %s | %d ator(es) | Lançamento: %s | Orçamento: $%,.2f",
                titulo, nomeDiretores, atores.size(), dataLancamento.toString(), orcamento);

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

    public void removerDiretor(Diretor diretor) {
        if (diretor == null) {
            throw new IllegalArgumentException("Diretor não pode ser nulo");
        }
        if (diretores.contains(diretor)) {
            diretores.remove(diretor);

            System.out.println("Diretor removido com sucesso.");
        } else {
            System.out.println("Este diretor não está associado a este filme.");
        }
    }




    @Override
    public String toString() {
        return getInfo();
    }
}