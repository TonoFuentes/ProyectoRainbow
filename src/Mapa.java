import java.util.ArrayList;

public class Mapa {
    private String nombre;
    private ArrayList<String> mapas = new ArrayList<>();

    public Mapa(String nombre) {
        this.nombre = nombre;
    }

    public Mapa() {}

    public ArrayList<String> getMapas() {
        return mapas;
    }

    public void añadirMapa(String mapa) {
        mapas.add(mapa);
    }

    @Override
    public String toString() {
        return "Mapa - " + nombre;
    }
}
