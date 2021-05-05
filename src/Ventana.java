import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


public class Ventana {
    private JButton btnMapas;
    private JPanel pnl;
    private JComboBox<String> cmbMapa;
    private JLabel lblMapa;
    private JComboBox cmbBando;
    private JLabel lblBando;
    private JPanel pnl2;
    private JLabel lblMensaje;
    private JList<String> lstTacticas;
    private JRadioButton rbnAtacante;
    private JRadioButton rbnDefensor;
    private JButton btnConfirmar;
    private JLabel lblEspacio;
    private JLabel lblAgregarTacticas;
    private JTextPane textPane1;
    private JLabel lblAgentesAgregar;
    private JComboBox cmbAgentesAgregar;
    private JLabel lblRolesAgregar;
    private JComboBox cmbRolesAgregar;
    private JLabel lblListarTacticas;
    private JLabel lblMapaAgregar;
    private JComboBox cmbMapasAgregar;
    private JLabel lblBandosAgregar;
    private JRadioButton rbnAtacnteAgregar;
    private JRadioButton rbnDefensorAgregar;
    private JButton btnConfirmarAgregar;
    private JLabel lblPersonasAgregar;
    private JRadioButton rdbSolitarioAgregar;
    private JRadioButton rdbGrupoAgregar;
    private JLabel lblMensajeAgregar;
    private String mapa;
    private String bando;
    private String agregarMapa;
    private String agregarBando;
    private String agregarAgente;
    private String agregarRol;
    private String agregarPersonas;

    public Ventana() throws FileNotFoundException {
        setMapas();
        cmbMapa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               mapa = cmbMapa.getSelectedItem().toString();
            }
        });
        rbnAtacante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bando = rbnAtacante.getText();
            }
        });
        rbnDefensor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bando = rbnDefensor.getText();
            }
        });
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    listarTacticas();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });
        cmbMapasAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarMapa = cmbMapasAgregar.getSelectedItem().toString();
            }
        });
        rbnAtacnteAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarBando = rbnAtacnteAgregar.getText();
                try {
                    setCmbAgentesAgregar();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });
        rbnDefensorAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarBando = rbnDefensorAgregar.getText();
                try {
                    setCmbAgentesAgregar();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });
        cmbAgentesAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarAgente = cmbAgentesAgregar.getSelectedItem().toString();
            }
        });
        cmbRolesAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarRol = cmbRolesAgregar.getSelectedItem().toString();
            }
        });
        rdbSolitarioAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarPersonas = rdbSolitarioAgregar.getText();
            }
        });
        rdbGrupoAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarPersonas = rdbGrupoAgregar.getText();
            }
        });
        btnConfirmarAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
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

    public void setMapas() throws FileNotFoundException {
        File fichero = new File("./Ficheros/mapas.txt");
        Scanner leer = new Scanner(new FileReader(fichero));
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();

        while (leer.hasNextLine())
            model.addElement(leer.nextLine());
        cmbMapa.setModel(model);
        cmbMapasAgregar.setModel(model);
    }

    public void setCmbAgentesAgregar() throws FileNotFoundException {
        File fichero = new File("./Ficheros/agentes.csv");
        Scanner leer = new Scanner(new FileReader(fichero));
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();

        while (leer.hasNextLine()) {
            String[] linea = leer.nextLine().split(",");

            if (agregarBando.equals(linea[0]))
                model.addElement(linea[1]);
        }
        cmbAgentesAgregar.setModel(model);
    }

    public static void main(String[] args) throws FileNotFoundException {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().pnl);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,650);
        frame.setVisible(true);
    }
}
