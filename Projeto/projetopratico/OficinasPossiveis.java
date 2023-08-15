package projetopratico;

import java.util.ArrayList;

public class OficinasPossiveis {

    private final ArrayList<Oficina> listaOficinas = new ArrayList<>();

    public OficinasPossiveis() {
        listaOficinas.add(new Oficina("jQuery", 30));
        listaOficinas.add(new Oficina("Arduino", 30));
        listaOficinas.add(new Oficina("Desenvolvimento para Android", 30));
        listaOficinas.add(new Oficina("Layout Responsivo com HTML5 e CSS3", 30));
        listaOficinas.add(new Oficina("C++:Desenvolvimento para iOS", 30));
        listaOficinas.add(new Oficina("Google Apps", 30));
    }

    public ArrayList<Oficina> getListaOficinas() {
        return listaOficinas;
    }

    public Oficina getOficina(int i) {
        return listaOficinas.get(i);
    }

    public int getNumerosOficinas() {
        return listaOficinas.size();
    }
    

}
