import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;


public class Ventana extends Component{
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
    private String mapaElegido;
    private String bandoElegido;
    private String agregarMapa;
    private String agregarBando;
    private String agregarAgente;
    private String agregarRol;
    private String agregarPersonas;
    private String descripcionTactica;
    private static JMenuBar barraDeMenus;
    private JMenu importar;
    private JMenuItem menuAgentes;
    private JMenuItem menuMapas;
    private JMenuItem menuTacticas;
    private JFileChooser archivo;
    private Scanner archivoTacticas;
    private Scanner archivoMapas;
    private Scanner archivoAgentes;
    private File ficheroTacticas;
    private File ficheroAgentes;
    private File ficheroMapas;

    public Ventana() {
        //Instanciacion de atributos
        this.barraDeMenus = new JMenuBar();
        this.importar = new JMenu("Importar");
        this.menuAgentes = new JMenuItem("Agentes");
        this.menuMapas = new JMenuItem("Mapas");
        this.menuTacticas = new JMenuItem("Tacticas");

        //Creacion e importacion de la barra de menus y los items
        barraDeMenus.add(importar);
        importar.add(menuAgentes);
        importar.add(menuMapas);
        importar.add(menuTacticas);


        cmbMapa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mapaElegido = cmbMapa.getSelectedItem().toString();
            }
        });
        rbnAtacante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bandoElegido = rbnAtacante.getText();
            }
        });
        rbnDefensor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bandoElegido = rbnDefensor.getText();
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
                try {
                    escribirTactica();
                    textPane1.setText("");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        menuAgentes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                archivo = new JFileChooser();
                archivo.showOpenDialog(null);
                ficheroAgentes = archivo.getSelectedFile();
            }
        });
        menuMapas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                archivo = new JFileChooser();
                archivo.showOpenDialog(null);
                ficheroMapas = archivo.getSelectedFile();
                try {
                    setMapas();
                    setMapasAgregar();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });
        menuTacticas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                archivo = new JFileChooser();
                archivo.showOpenDialog(null);
                ficheroTacticas = archivo.getSelectedFile();
            }
        });
    }

    public void listarTacticas() throws FileNotFoundException {
        DefaultListModel modelo = new DefaultListModel();
        archivoTacticas = new Scanner(new FileReader(ficheroTacticas));

        while (archivoTacticas.hasNextLine()) {
            String[] linea = archivoTacticas.nextLine().split(",");

            if (mapaElegido.equals(linea[0])){
                if (bandoElegido.equals(linea[2])){
                    modelo.addElement(linea[1] + " - " + linea[3] + " - " + linea[4] + " - " + linea[5]);
                }
            }
        }

        lstTacticas.setModel(modelo);
    }

    public void setMapas() throws FileNotFoundException {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
        archivoMapas = new Scanner(new FileReader(ficheroMapas));

        while (archivoMapas.hasNextLine())
            model.addElement(archivoMapas.nextLine());
        cmbMapa.setModel(model);
    }

    public void setMapasAgregar() throws FileNotFoundException {
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<String>();
        archivoMapas = new Scanner(new FileReader(ficheroMapas));

        while (archivoMapas.hasNextLine())
            modelo.addElement(archivoMapas.nextLine());

        cmbMapasAgregar.setModel(modelo);
    }

    public void setCmbAgentesAgregar() throws FileNotFoundException {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
        archivoAgentes = new Scanner(new FileReader(ficheroAgentes));

        while (archivoAgentes.hasNextLine()) {
            String[] linea = archivoAgentes.nextLine().split(",");

            if (agregarBando.equals(linea[0]))
                model.addElement(linea[1]);
        }
        cmbAgentesAgregar.setModel(model);
    }

    public void escribirTactica() throws IOException {
        descripcionTactica = textPane1.getText();
        BufferedWriter escribir = new BufferedWriter(new FileWriter(ficheroTacticas, true));

        escribir.write(agregarMapa + "," + agregarAgente + "," + agregarBando + "," + agregarRol + "," + agregarPersonas + "," + descripcionTactica);
        escribir.write("\n");
        escribir.close();
    }

    public void fondo(Graphics grafico) {
        Dimension tamanio = getSize();
        ImageIcon imagenFondo = new ImageIcon(getClass().getResource("fondoR6.jpg"));
        grafico.drawImage(imagenFondo.getImage(),0,0,tamanio.width, tamanio.height, null);
    }

    public static void main(String[] args) throws FileNotFoundException {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().pnl);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,650);
        frame.setVisible(true);
        frame.setJMenuBar(barraDeMenus);
    }
}
