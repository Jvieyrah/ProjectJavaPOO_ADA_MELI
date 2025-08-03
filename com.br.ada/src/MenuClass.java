import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MenuClass {
  Catalogo catalogo = new Catalogo();
  private boolean isRunning = true;

  public void exibirMenu() {
  seed(); // Popula o catálogo com dados iniciais
  while (isRunning) {
    System.out.println("Menu:");
    System.out.println("1. Cadastrar Filme");
    System.out.println("2. Cadastrar Diretor");
    System.out.println("3. Cadastrar Ator");
    System.out.println("4. Buscar Filme por Nome");
    System.out.println("5. Editar um filme cadastrado");
    System.out.println("6. Editar um diretor cadastrado");
    System.out.println("7. Editar um ator cadastrado");
    System.out.println("0. Sair");

    int opcao = new Scanner(System.in).nextInt();

    switch (opcao) {
      case 1:
        cadastrarFilme();
        break;
      case 2:
        cadastrarDiretor();
        break;
      case 3:
        cadastrarAtor();
        break;
      case 4:
        buscarFilmePorNome();
        break;
      case 5:
        editarfilme();
        break;
      case 6:
        editarDiretor();
        break;
      case 7:
        editarAtor();
        break;
      case 0:
        System.out.println("Saindo...");
        isRunning = false;
        return;
      default:
        System.out.println("Opção inválida, tente novamente.");
    }
  }
  }

  private void editarAtor() {
    boolean isEditing = true;
    List<Ator> atores = catalogo.listarAtores();
    if (atores.isEmpty()) {
      System.out.println("Nenhum ator cadastrado para editar.");
      return;
    }
    System.out.println("Selecione o ator que deseja editar:");
    for (int i = 0; i < atores.size(); i++) {
      System.out.println((i + 1) + ". " + atores.get(i).getNome());
    }

    Scanner scanner = new Scanner(System.in);
    int escolha = scanner.nextInt() - 1;
    if (escolha < 0 || escolha >= atores.size()) {
      System.out.println("Opção inválida.");
      return;
    }
    Ator atorSelecionado = atores.get(escolha);
    scanner.nextLine(); // Consumir a quebra de linha
    System.out.print(atorSelecionado.getInfo() + "\n");
    while (isEditing) {
      System.out.println("O que você deseja editar?");
      System.out.println("1. Nome");
      System.out.println("2. Data de Nascimento");
      System.out.println("3. Nacionalidade");
      System.out.println("4. Adicionar Filmes Participados");
      System.out.println("0. Voltar");

      int opcao = scanner.nextInt();
      scanner.nextLine(); // Consumir a quebra de linha

      switch (opcao) {
        case 1:
          System.out.print("Digite o novo nome: ");
          String novoNome = scanner.nextLine();
          atorSelecionado.setNome(novoNome);
          break;
        case 2:
          System.out.print("Digite a nova data de nascimento (AAAA-MM-DD): ");
          String novaData = scanner.nextLine();
          atorSelecionado.setDataNascimento(LocalDate.parse(novaData));
          break;
        case 3:
          System.out.print("Digite a nova nacionalidade: ");
          String novaNacionalidade = scanner.nextLine();
          atorSelecionado.setNacionalidade(novaNacionalidade);
          break;
        case 4:
          adicionarFilmeAtor(atorSelecionado);
        case 0:
          isEditing = false;
          break;
        default:
          System.out.println("Opção inválida, tente novamente.");
      }
    }
  }

  private void adicionarFilmeAtor(Ator atorSelecionado) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Selecione um filme já cadastrado:");
    List<Filme> filmes = catalogo.listarFilmes();
    if (filmes.isEmpty()) {
      System.out.println("Nenhum filme cadastrado.");
      return;
    }
    for (int i = 0; i < filmes.size(); i++) {
      if (filmes.get(i).getAtores().stream().noneMatch(ator -> ator.getNome().equals(atorSelecionado.getNome()))) {
        // Exibe apenas filmes que ainda não têm o ator associado
        System.out.println((i + 1) + ". " + filmes.get(i).getTitulo());
      }
    }
    int escolhaFilme = scanner.nextInt() - 1;
    if (escolhaFilme >= 0 && escolhaFilme < filmes.size()) {
      Filme filmeSelecionado = filmes.get(escolhaFilme);
      if (filmeSelecionado.getAtores().stream().anyMatch(ator -> ator.getNome().equals(atorSelecionado.getNome()))) {
        System.out.println("Este ator já está associado a este filme.");
        return;
      }
      System.out.println("Deseja adicionar " + atorSelecionado.getNome()
          + " como ator do Filme " + filmeSelecionado.getTitulo() + "? (s/n)");
      String confirmacao = scanner.next();
      if (confirmacao.equalsIgnoreCase("s")) {
        atorSelecionado.atuarFilme(filmeSelecionado);
        System.out.println("Ator adicionado com sucesso!");
      } else {
        System.out.println("Operação cancelada.");
      }
    } else {
      System.out.println("Opção inválida.");
    }
  }

  private void editarDiretor() {
    boolean isEditing = true;
    List<Diretor> diretores = catalogo.listarDiretores();
    if (diretores.isEmpty()) {
      System.out.println("Nenhum diretor cadastrado para editar.");
      return;
    }
    System.out.println("Selecione o diretor que deseja editar:");
    for (int i = 0; i < diretores.size(); i++) {
      System.out.println((i + 1) + ". " + diretores.get(i).getNome());
    }

    Scanner scanner = new Scanner(System.in);
    int escolha = scanner.nextInt() - 1;
    if (escolha < 0 || escolha >= diretores.size()) {
      System.out.println("Opção inválida.");
      return;
    }
    Diretor diretorSelecionado = diretores.get(escolha);
    scanner.nextLine(); // Consumir a quebra de linha
    System.out.print(diretorSelecionado.getInfo() + "\n");
    while (isEditing) {
      System.out.println("O que você deseja editar?");
      System.out.println("1. Nome");
      System.out.println("2. Data de Nascimento");
      System.out.println("3. Nacionalidade");
      System.out.println("4. Adicionar Filmes Dirigidos");
      System.out.println("0. Voltar");

      int opcao = scanner.nextInt();
      scanner.nextLine(); // Consumir a quebra de linha

      switch (opcao) {
        case 1:
          System.out.print("Digite o novo nome: ");
          String novoNome = scanner.nextLine();
          diretorSelecionado.setNome(novoNome);
          break;
        case 2:
          System.out.print("Digite a nova data de nascimento (AAAA-MM-DD): ");
          String novaData = scanner.nextLine();
          diretorSelecionado.setDataNascimento(LocalDate.parse(novaData));
          break;
        case 3:
          System.out.print("Digite a nova nacionalidade: ");
          String novaNacionalidade = scanner.nextLine();
          diretorSelecionado.setNacionalidade(novaNacionalidade);
          break;
        case 4:
          adicionarFilmeDiretor(diretorSelecionado);
        case 0:
          isEditing = false;
          break;
        default:
          System.out.println("Opção inválida, tente novamente.");
      }
    }
  }

  private void adicionarFilmeDiretor(Diretor diretorSelecionado) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Selecione um filme já cadastrado:");
    List<Filme> filmes = catalogo.listarFilmes();
    if (filmes.isEmpty()) {
      System.out.println("Nenhum filme cadastrado.");
      return;
    }
    for (int i = 0; i < filmes.size(); i++) {
      if (filmes.get(i).getDiretor() == null || !filmes.get(i).getDiretor().getNome().equals(diretorSelecionado.getNome())){
        System.out.println((i + 1) + ". " + filmes.get(i).getTitulo());
      }
    }
    int escolhaFilme = scanner.nextInt() - 1;
    if (escolhaFilme >= 0 && escolhaFilme < filmes.size()) {
      Filme filmeSelecionado = filmes.get(escolhaFilme);
      if ( filmeSelecionado.getDiretor() != null && filmeSelecionado.getDiretor().getNome().equals(diretorSelecionado.getNome())) {
        System.out.println("Este diretor já está associado a este filme.");
        return;
      }
      System.out.println("Deseja adicionar " + diretorSelecionado.getNome()
          + " como diretor do Filme " + filmeSelecionado.getTitulo() + "? (s/n)");
      String confirmacao = scanner.next();
      if (confirmacao.equalsIgnoreCase("s")) {
        diretorSelecionado.dirigirFilme(filmeSelecionado);
        //filmeSelecionado.setDiretor(diretorSelecionado);
        System.out.println("Diretor adicionado com sucesso!");
      } else {
        System.out.println("Operação cancelada.");
      }
    } else {
      System.out.println("Opção inválida.");
    }
  }

  private void editarfilme() {
    boolean isEditing = true;
    List<Filme> filmes = catalogo.listarFilmes();
    if (filmes.isEmpty()) {
      System.out.println("Nenhum filme cadastrado para editar.");
      return;
    }
    System.out.println("Selecione o filme que deseja editar:");
    for (int i = 0; i < filmes.size(); i++) {
      System.out.println((i + 1) + ". " + filmes.get(i).getTitulo());
    }

    Scanner scanner = new Scanner(System.in);
    int escolha = scanner.nextInt() - 1;
    if (escolha < 0 || escolha >= filmes.size()) {
      System.out.println("Opção inválida.");
      return;
    }
    Filme filmeSelecionado = filmes.get(escolha);
    scanner.nextLine(); // Consumir a quebra de linha
    System.out.print(filmeSelecionado.getInfo() + "\n");
    while (isEditing) {
      System.out.println("O que você deseja editar?");
      System.out.println("1. Título");
      System.out.println("2. Data de Lançamento");
      System.out.println("3. Orçamento");
      System.out.println("4. Descrição");
      System.out.println("5. Adicionar um diretor já cadastrado");
      System.out.println("6. Adicionar um novo diretor não cadastrado");
      System.out.println("7. Adicionar um ator já cadastrado");
      System.out.println("8. Adicionar um novo ator não cadastrado");
      System.out.println("0. Voltar");

      int opcao = scanner.nextInt();
      scanner.nextLine(); // Consumir a quebra de linha

      switch (opcao) {
        case 1:
          System.out.print("Digite o novo título: ");
          String novoTitulo = scanner.nextLine();
          filmeSelecionado.setTitulo(novoTitulo);
          break;
        case 2:
          System.out.print("Digite a nova data de lançamento (AAAA-MM-DD): ");
          String novaData = scanner.nextLine();
          filmeSelecionado.setDataLancamento(LocalDate.parse(novaData));
          break;
        case 3:
          System.out.print("Digite o novo orçamento: ");
          double novoOrcamento = scanner.nextDouble();
          filmeSelecionado.setOrcamento(novoOrcamento);
          break;
        case 4:
          System.out.print("Digite a nova descrição: ");
          String novaDescricao = scanner.nextLine();
          filmeSelecionado.setDescricao(novaDescricao);
          break;
        case 5:
           adicionarDiretorCadastrado(filmeSelecionado);
          break;
        case 6:
          adicionarDiretorNaoCadastrado(filmeSelecionado);
          break;
        case 7:
          adicionarAtorCadastrado(filmeSelecionado);
          break;
        case 8:
          adicionarAtorNaoCadastrado(filmeSelecionado);
          break;
        case 0:
          isEditing = false;
          break;
        default:
          System.out.println("Opção inválida, tente novamente.");
      }
    }
  }

  private void adicionarAtorNaoCadastrado(Filme filmeSelecionado) {
    Scanner scanner = new Scanner(System.in);
    cadastrarAtor();
    adicionarAtorCadastrado(filmeSelecionado);
  }

  private void adicionarAtorCadastrado(Filme filmeSelecionado) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Selecione um ator já cadastrado:");
    List<Ator> atores = catalogo.listarAtores();
    if (atores.isEmpty()) {
      System.out.println("Nenhum ator cadastrado.");
      return;
    }
    for (int i = 0; i < atores.size(); i++) {
      String atorNome = atores.get(i).getNome();
      if (filmeSelecionado.getAtores().stream().noneMatch(ator -> ator.getNome().equals(atorNome))) {
        // Exibe apenas atores que ainda não estão associados ao filme
        System.out.println((i + 1) + ". " + atorNome);
      }
    }
    int escolhaAtor = scanner.nextInt() - 1;
    if (escolhaAtor >= 0 && escolhaAtor < atores.size()) {
      Ator atorCadastrado = atores.get(escolhaAtor);
      if (filmeSelecionado.getAtores().stream().anyMatch(ator -> ator.getNome().equals(atorCadastrado.getNome()))) {
        System.out.println("Este ator já está associado a este filme.");
        return;
      }
      System.out.println("Deseja adicionar " + atorCadastrado.getNome()
          + " como ator do Filme " + filmeSelecionado.getTitulo() + "? (s/n)");
      String confirmacao = scanner.next();
      if (confirmacao.equalsIgnoreCase("s")) {
        atorCadastrado.atuarFilme(filmeSelecionado);
        filmeSelecionado.adicionarAtor(atorCadastrado);
        System.out.println("Ator adicionado com sucesso!");
      } else {
        System.out.println("Operação cancelada.");
      }
    } else {
      System.out.println("Opção inválida.");
    }
  }

  private void buscarFilmePorNome() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Digite o nome do filme que deseja buscar: ");
    String nome = scanner.nextLine();
    var filmesEncontrados = catalogo.buscarFilmePorNome(nome);
    if (filmesEncontrados.isEmpty()) {
      System.out.println("Nenhum filme encontrado com o nome: " + nome);
    } else {
      System.out.println("Filmes encontrados:");
      filmesEncontrados.forEach(filme -> System.out.println(filme.getInfo()));
    }
  }

  private void cadastrarAtor() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Digite o nome do ator: ");
    String nome = scanner.nextLine();
    System.out.print("Digite a data de nascimento (AAAA-MM-DD): ");
    String dataNascimento = scanner.nextLine();
    System.out.print("Digite a nacionalidade do ator: ");
    String nacionalidade = scanner.nextLine();

    Ator ator = new Ator(nome, LocalDate.parse(dataNascimento), nacionalidade);
    catalogo.cadastrarAtor(ator);
    System.out.println("Ator cadastrado com sucesso!");
  }

  private void cadastrarDiretor() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Digite o nome do diretor: ");
    String nome = scanner.nextLine();
    System.out.print("Digite a data de nascimento (AAAA-MM-DD): ");
    String dataNascimento = scanner.nextLine();
    System.out.print("Digite a nacionalidade do diretor: ");
    String nacionalidade = scanner.nextLine();

    Diretor diretor = new Diretor(nome, LocalDate.parse(dataNascimento), nacionalidade);
    catalogo.cadastrarDiretor(diretor);
    System.out.println("Diretor cadastrado com sucesso!");
  }

  private void cadastrarFilme() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Digite o título do filme: ");
    String titulo = scanner.nextLine();
    System.out.print("Digite a data de lançamento (AAAA-MM-DD): ");
    String dataLancamento = scanner.nextLine();
    System.out.print("Digite o orçamento do filme: ");
    double orcamento = scanner.nextDouble();
    scanner.nextLine(); // Consumir a quebra de linha
    System.out.print("Digite a descrição do filme: ");
    String descricao = scanner.nextLine();

    Filme filme = new Filme(titulo, LocalDate.parse(dataLancamento), orcamento, descricao);
    catalogo.cadastrarFilme(filme);
    System.out.println("Filme cadastrado com sucesso!");
  }

  private void adicionarDiretorCadastrado(Filme filmeSelecionado){
    Scanner scanner = new Scanner(System.in);
    System.out.println("Selecione um diretor já cadastrado:");
    List<Diretor> diretores = catalogo.listarDiretores();
    if (diretores.isEmpty()) {
      System.out.println("Nenhum diretor cadastrado.");
    }
    for (int i = 0; i < diretores.size(); i++) {
      System.out.println((i + 1) + ". " + diretores.get(i).getNome());
    }
    int escolhaDiretor = scanner.nextInt() - 1;
    if (escolhaDiretor >= 0 && escolhaDiretor < diretores.size()) {
      Diretor diretorCadastrado = diretores.get(escolhaDiretor);
      System.out.println("Deseja cadastrar " + diretorCadastrado.getNome()
          + " como diretor do Filme " + filmeSelecionado.getTitulo() + "? (s/n)");
      String confirmacao = scanner.next();
      if (confirmacao.equalsIgnoreCase("s")) {
        diretorCadastrado.dirigirFilme(filmeSelecionado);
        //filmeSelecionado.setDiretor(diretorCadastrado);
        System.out.println("Diretor adicionado com sucesso!");
      } else {
        System.out.println("Operação cancelada.");
      }
    } else {
      System.out.println("Opção inválida.");
    }
  }

  private void adicionarDiretorNaoCadastrado(Filme filmeSelecionado) {
    cadastrarDiretor();
    adicionarDiretorCadastrado(filmeSelecionado);
  }

  private void seed(){
    // Método para popular o catálogo com dados iniciais
    Diretor diretor1 = new Diretor("Christopher Nolan", LocalDate.of(1970, 7, 30), "Britânico");
    catalogo.cadastrarDiretor(diretor1);

    Diretor diretor2 = new Diretor("Martin Scorsese", LocalDate.of(1942, 11, 17), "Americano");
    catalogo.cadastrarDiretor(diretor2);

    Diretor diretor3 = new Diretor("Steven Spielberg", LocalDate.of(1946, 12, 18), "Americano");
    catalogo.cadastrarDiretor(diretor3);

    Ator ator1 = new Ator("Leonardo DiCaprio", LocalDate.of(1974, 11, 11), "Americano");
    catalogo.cadastrarAtor(ator1);

    Ator ator2 = new Ator("Tom Hanks", LocalDate.of(1956, 7, 9), "Americano");
    catalogo.cadastrarAtor(ator2);

    Ator ator3 = new Ator("Meryl Streep", LocalDate.of(1949, 6, 22), "Americana");
    catalogo.cadastrarAtor(ator3);

    Filme filme1 = new Filme("Inception", LocalDate.of(2010, 7, 16), 160000000, "Um ladrão que rouba segredos corporativos...");
    catalogo.cadastrarFilme(filme1);

    Filme filme2 = new Filme("The Wolf of Wall Street", LocalDate.of(2013, 12, 25), 100000000, "A história de Jordan Belfort...");
    catalogo.cadastrarFilme(filme2);

    Filme filme3 = new Filme("Schindler's List", LocalDate.of(1993, 12, 15), 22000000, "A história de Oskar Schindler...");
    catalogo.cadastrarFilme(filme3);
  }
}
