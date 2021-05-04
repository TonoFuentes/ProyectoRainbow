import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class Ventana {
    private JButton btnMapas;
    private JPanel pnl;
    private JComboBox cmbMapa;
    private JLabel lblMapa;
    private JComboBox cmbBando;
    private JLabel lblBando;
    private JPanel pnl2;
    private JLabel lblMensaje;
    private String mapa;
    private String bando;

    public Ventana() throws FileNotFoundException {
        añadirMapas();
        setCmbMapa();
        cmbMapa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               mapa = cmbMapa.getSelectedItem().toString();
            }
        });
        cmbBando.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bando = cmbBando.getSelectedItem().toString();
            }
        });

    }

    public void listarTacticas() {

    }

    public void setCmbMapa() throws FileNotFoundException {
        File fichero = new File("./Ficheros/mapas.txt");
        Scanner leer = new Scanner(new FileReader(fichero));
        DefaultComboBoxModel model = new DefaultComboBoxModel();

        while (leer.hasNextLine())
            model.addElement(leer.nextLine());
        cmbMapa.setModel(model);
    }

    private static void añadirMapas() throws FileNotFoundException {
        File fichero = new File("./Ficheros/mapas.txt");
        Scanner leer = new Scanner(new FileReader(fichero));

        while (leer.hasNextLine())
            MapaInterfaz.añadirMapa(leer.nextLine());
    }

    public static class MapaInterfaz {
        private static String nombre;
        private static ArrayList<String> mapas = new ArrayList<>();

        public MapaInterfaz(String nombre) {
            this.nombre = nombre;
        }

        public MapaInterfaz() {}

        public ArrayList<String> getMapas() {
            return mapas;
        }

        public static void añadirMapa(String mapa) {
            mapas.add(mapa);
        }

        @Override
        public String toString() {
            return "Mapa - " + nombre;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().pnl);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920,1080);
        frame.setVisible(true);
    }
}
