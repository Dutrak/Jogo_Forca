/*
 * Autores: Fernando Dutra de Oliveira
 *          Heitor Gonçalez Bovi
 */
package forca;

public class App {
    public static String[][] Matriz = new String[9][6];
    public static String Palavras[] = { "CACHORRO", "GATO", "ELEFANTE", "GIRAFA", "LEAO", "TIGRE", "GOLFINHO",
            "TARTARUGA", "MACACO", "GALO", "GALINHA", "GALINHEIRO", "VACA", "PORCO", "PASSARINHO" };
    public static int rand = (int) (Math.random() * 15);
    public static Entrada e = new Entrada();
    public static String[] letras = new String[Palavras[rand].length()];
    public static int cont = 0;
    public static String jogadas = "";
    public static String letra = "";


    public static void main(String[] args) {
        System.out.print("\nJOGO DA FORCA: ANIMAIS.");
        tabela();
        Imprime();
        while (cont != 6) {
            Entrada();
            Valida();
            Imprime();
        }
            System.out.println("\n\nVocê perdeu!");
            System.out.println("A palavra era: " + Palavras[rand]);
            System.exit(0);
    }

    public static void tabela() {

        for (int i = 0; i < 9; i++) {
            for (int j = 1; j < 6; j++) {
                Matriz[i][j] = "   ";
            }
        }

        for (int i = 0; i < 9; i++) {
            Matriz[i][0] = "|";
        }
        for (int j = 1; j < 4; j++) {
            Matriz[0][j] = " - ";
        }

        for (int i = 0; i < Palavras[rand].length(); i++) {
            letras[i] = "_";
        }
        System.out.println();
    }

    public static void Imprime() {
        System.out.print("\t\t\t\t " + jogadas);
        for (int i = 0; i < 9; i++) {
            System.out.println();
            for (int j = 0; j < 6; j++) {
                System.out.print(Matriz[i][j]);
            }
        }

        System.out.println();
        System.out.println();
        System.out.println();
        for (int i = 0; i < Palavras[rand].length(); i++) {
            System.out.print(letras[i] + " ");
        }
    }

    public static void Entrada() {
        boolean erro;
        do {
            erro = false;
            letra = e.entradaTexto("\n\nDigite uma letra: ");
            letra = letra.toUpperCase();

            // verifica se o valor já foi digitado pelo usuario anteriormente
            if (jogadas.contains(letra)) {
                System.out.println("Você já digitou essa letra");
                erro = true;
            }

            // valida se a letra é um valor valido
            if (letra == "" || letra.length() != 1 || letra.matches("[^A-Z]*$")) {
                System.out.println("Digite uma letra válida de A a Z");
                erro = true;
            }
        } while (erro);

        jogadas = jogadas + letra + " ";
    }

    public static void Valida() {

        // verifica se a letra existe na palavra, caso exista, substitui o valor no
        // vetor
        for (int i = 0; i < Palavras[rand].length(); i++) {
            if (letra.charAt(0) == Palavras[rand].charAt(i)) {
                letras[i] = letra;
            }
        }

        // verifica se a letra não existe na palavra, caso não exista, chama a função
        // que desenha o boneco e adiciona 1 ao contador
        if (Palavras[rand].indexOf(letra) == -1 && (jogadas.contains(letra))) {
            cont++;
            Boneco();
        }

        // verifica se houve um ganhador
        if (Palavras[rand].equals(String.join("", letras))) {
            Imprime();
            System.out.println("\n\nVocê ganhou!");
            System.exit(0);
        }
    }

    public static void Boneco() {
        switch (cont) {
            case 1:
                Matriz[1][3] = " O";
                Matriz[2][3] = " |";
                Matriz[3][3] = " +";
                break;
            case 2:
                Matriz[3][2] = " - ";
                Matriz[3][1] = " - ";
                Matriz[4][1] = " |";
                break;
            case 3:
                Matriz[3][4] = " - ";
                Matriz[3][5] = " - ";
                Matriz[4][5] = " |";
                break;
            case 4:
                Matriz[4][3] = "  |";
                Matriz[5][3] = " |";
                Matriz[6][3] = " - ";
                break;
            case 5:
                Matriz[6][2] = " - ";
                Matriz[7][2] = " | ";
                Matriz[8][2] = " | ";
                break;
            case 6:
                Matriz[6][4] = " - ";
                Matriz[7][4] = " |";
                Matriz[8][4] = " |";
                break;
        }
    }
}