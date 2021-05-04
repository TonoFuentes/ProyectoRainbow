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
    private JList lstTacticas;
    private JRadioButton btnAtacante;
    private JRadioButton btnDefensor;
    private String mapa;
    private String bando;

    public Ventana() throws FileNotFoundException {
        setCmbMapa();
        cmbMapa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               mapa = cmbMapa.getSelectedItem().toString();
                try {
                    listarTacticas();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });
        cmbBando.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bando = cmbBando.getSelectedItem().toString();
                try {
                    listarTacticas();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });
        lblMensaje.setText("Made by Tono Fuentes");
    }

    public void listarTacticas() throws FileNotFoundException {
        File fichero = new File("./Ficheros/Tacticas.csv");
        Scanner leer = new Scanner(new FileReader(fichero));
        DefaultListModel modelo = new DefaultListModel();

        while (leer.hasNextLine()) {
            String linea[] = leer.nextLine().split(",");

            if (mapa.equals(linea[0])){
                if (bando.equals(linea[2])){
                    modelo.addElement(linea[1] + " - " + linea[3] + " - " + linea[4] + " - " + linea[5]);
                }
            }
        }

        lstTacticas.setModel(modelo);
    }

    public void setCmbMapa() throws FileNotFoundException {
        File fichero = new File("./Ficheros/mapas.txt");
        Scanner leer = new Scanner(new FileReader(fichero));
        DefaultComboBoxModel model = new DefaultComboBoxModel();

        while (leer.hasNextLine())
            model.addElement(leer.nextLine());
        cmbMapa.setModel(model);
    }

    public static void main(String[] args) throws FileNotFoundException {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().pnl);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920,1080);
        frame.setVisible(true);
    }
}
