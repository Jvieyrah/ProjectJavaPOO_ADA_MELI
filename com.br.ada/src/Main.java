import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Catalogo catalogo = new Catalogo();

        // Cadastro de diretores
        Diretor diretor1 = new Diretor("Christopher Nolan", LocalDate.of(1970, 7, 30), "Britânico");
        catalogo.cadastrarDiretor(diretor1);

        // Cadastro de atores
        Ator ator1 = new Ator("Leonardo DiCaprio", LocalDate.of(1974, 11, 11), "Americano");
        catalogo.cadastrarAtor(ator1);

        // Cadastro de filmes
        Filme filme1 = new Filme("Inception", LocalDate.of(2010, 7, 16), 160000000, "Um ladrão que rouba segredos corporativos...");
        catalogo.cadastrarFilme(filme1);

        // Associações
        diretor1.dirigirFilme(filme1);
        ator1.atuarFilme(filme1);

        // Busca
        System.out.println("Resultados da busca:\n");
        catalogo.buscarFilmePorNome("incep").forEach(f -> System.out.println(f.getInfo() + "\n"));
//        List<Filme> filmesEncontrados = catalogo.buscarFilmePorNome("incep");
//        for (Filme f : filmesEncontrados) {
//            System.out.println(f.getInfo());
//        }
    }
}