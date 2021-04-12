import java.util.ArrayList;

public class Agente {
    private String nombre;
    private String bando;
    private int numeroAtacantes;
    private int numeroDefensores;
    private ArrayList<Agente> agentes = new ArrayList<>();

    public Agente(String bando, String nombre) {
        this.bando = bando;
        this.nombre = nombre;
    }

    public Agente() {}

    public ArrayList<Agente> getAgentes() {
        return agentes;
    }

    public void añadirAgente(String bando, String nombre) {
        agentes.add(new Agente(bando, nombre));
    }

    public ArrayList<Agente> listarAgentes(){
        for (int contador = 0; contador < agentes.size(); contador++){
            agentes.get(contador);
        }

        return agentes;
    }

    public ArrayList<Agente> listarAgentesAtacantes(){
        ArrayList<Agente> atacantes = new ArrayList<>();

        for (int contador = 0; contador < agentes.size(); contador++){
            if (agentes.get(contador).bando.equals("Atacante")) {
                atacantes.add(agentes.get(contador));
            }
        }

        return atacantes;
    }

    public ArrayList<Agente> listarAgentesDefensores(){
        ArrayList<Agente> defensores = new ArrayList<>();

        for (int contador = 0; contador < agentes.size(); contador++){
            if (agentes.get(contador).bando.equals("Defensor")){
                defensores.add(agentes.get(contador));
            }
        }

        return defensores;
    }

    public void numeroDeAgentes(){
        int atacantes = 0;
        int defensores = 0;

        for (int contador = 0; contador < agentes.size(); contador++){
            if (agentes.get(contador).bando.equals("Defensor")){
                defensores++;
            } else {
                atacantes++;
            }
        }

        numeroAtacantes = atacantes;
        numeroDefensores = defensores;
    }

    public int getNumeroAtacantes() {
        return numeroAtacantes;
    }

    public int getNumeroDefensores() {
        return numeroDefensores;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
