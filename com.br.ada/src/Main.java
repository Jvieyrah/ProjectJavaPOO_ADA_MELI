import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Catalogo catalogo = new Catalogo();
        MenuClass menu = new MenuClass(catalogo);
        menu.exibirMenu();
    }
}