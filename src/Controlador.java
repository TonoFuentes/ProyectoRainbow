import java.io.*;
import java.util.Scanner;

public class Controlador {

    //Atributos
    private static Mapa mapas = new Mapa();
    private static Agente agentes = new Agente();
    private static Scanner teclado = new Scanner(System.in);
    private static int respuesta;
    private static int mapaSeleccionado;
    private static String bando;

    public static void main(String[] args) throws IOException {
        añadirMapas();
        añadirAgentes();
        agentes.numeroDeAgentes();

        System.out.println("Bienvenido Agente");
        System.out.println("1. Listar Tacticas");
        System.out.println("2. Añadir Tacticas");
        System.out.println("3. Salir");
        System.out.print("> ");
        respuesta = teclado.nextInt();

        switch (respuesta){
            case 1:
                listarTacticas();
                break;

            case 2:
                añadirTacticas();
                break;
        }


    }

    private static void listarTacticas() throws FileNotFoundException {
        System.out.println("Elige el mapa:");

        for (int contador = 0; contador < mapas.getMapas().size(); contador++) {
            System.out.println(contador + ". " + mapas.getMapas().get(contador));
        }

        System.out.print("> ");
        mapaSeleccionado = teclado.nextInt();

        System.out.println();

        System.out.println("================");
        System.out.println("     " + mapas.getMapas().get(mapaSeleccionado));
        System.out.println("================");

        System.out.println();

        System.out.println("1. Atacante");
        System.out.println("2. Defensor");
        System.out.print("> ");
        respuesta = teclado.nextInt();

        if (respuesta == 1) {
            bando = "Atacante";

            System.out.println("================");
            System.out.println("|   Atacante   |");
            System.out.println("================");

            mostrarTacticas(mapas.getMapas().get(mapaSeleccionado),bando);
        } else {
            bando = "Defensor";

            System.out.println("================");
            System.out.println("|   Defensor   |");
            System.out.println("================");

            mostrarTacticas(mapas.getMapas().get(mapaSeleccionado),bando);
        }
    }

    private static void añadirMapas() throws FileNotFoundException {
        File fichero = new File("./Ficheros/mapas.txt");
        Scanner leer = new Scanner(new FileReader(fichero));

        while (leer.hasNextLine())
            mapas.añadirMapa(leer.nextLine());
    }

    private static void añadirAgentes() throws FileNotFoundException {
        File fichero = new File("./Ficheros/agentes.csv");
        Scanner leer = new Scanner(new FileReader(fichero));

        while (leer.hasNextLine()) {
            String linea[] = leer.nextLine().split(",");

            if (linea[0].equals("Bando")){

            } else {
                agentes.añadirAgente(linea[0], linea[1]);
            }
        }
    }

    private static void mostrarTacticas(String mapa, String bando) throws FileNotFoundException {
        File fichero = new File("./Ficheros/Banco.csv");
        Scanner leer = new Scanner(new FileReader(fichero));

        while (leer.hasNextLine()) {
            String linea[] = leer.nextLine().split(",");

            if (mapa.equals(linea[0])){
                if (bando.equals(linea[2])){
                    System.out.println(linea[1] + " - " + linea[3] + " - " + linea[4] + " - " + linea[5]);
                }
            }
        }
    }

    private static void añadirTacticas() throws IOException {
        File fichero = new File("./Ficheros/Banco.csv");
        BufferedWriter escribir = new BufferedWriter(new FileWriter(fichero, true));
        int agenteSeleccionado;

        System.out.println("En que mapa se desarrolla la tactica:");

        for (int contador = 0; contador < mapas.getMapas().size(); contador++) {
            System.out.println(contador + ". " + mapas.getMapas().get(contador));
        }

        System.out.print("> ");
        mapaSeleccionado = teclado.nextInt();

        System.out.println("Bando:");
        System.out.println("1. Atacante");
        System.out.println("2. Defensor");
        System.out.print("> ");
        respuesta = teclado.nextInt();

        if (respuesta == 1){
            bando = "Atacante";
            System.out.println("Atacantes:");

            for (int contador = 0; contador < agentes.getNumeroAtacantes(); contador++){
                System.out.println(contador + ". " + agentes.listarAgentesAtacantes().get(contador));
            }

            System.out.println("Selecciona un agente");
            System.out.print("> ");
            agenteSeleccionado = teclado.nextInt();

            escribir.write("\n");
            escribir.write(mapas.getMapas().get(mapaSeleccionado) + "," + agentes.listarAgentesAtacantes().get(agenteSeleccionado) + "," + bando);
            escribir.close();
        } else {
            bando = "Defensor";
            System.out.println("Defensores");

            for (int contador = 0; contador < agentes.getNumeroDefensores(); contador++){
                System.out.println(contador + ". " + agentes.listarAgentesDefensores().get(contador));
            }

            System.out.println("Selecciona un agente");
            System.out.print("> ");
            agenteSeleccionado = teclado.nextInt();

            escribir.write("\n");
            escribir.write(mapas.getMapas().get(mapaSeleccionado) + "," + agentes.listarAgentesDefensores().get(agenteSeleccionado) + "," + bando);
            escribir.close();
        }
    }
}
