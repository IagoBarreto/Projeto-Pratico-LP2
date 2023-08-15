package projetopratico;

import java.util.ArrayList;

public class ListaInscritos {

    private final ArrayList<Participante> listaParticipantes = new ArrayList<>();

    public boolean adicionar(Participante p) {
        if (!this.isCadastrado(p.getCpf())) {
            listaParticipantes.add(p);
            return true;
        } else {
            return false;
        }
    }

    public Participante buscarParticipante(String cpf) {
        Participante buscado = null;
        for (Participante cont : listaParticipantes) {
            if (cont.getCpf().equals(cpf)) {
                buscado = cont;
            }
        }
        return buscado;
    }

    public boolean isCadastrado(String cpf) {
        return this.buscarParticipante(cpf) != null;
    }

    public Participante getParticipante(int i) {
        return listaParticipantes.get(i);
    }

    public int tamanho() {
        return listaParticipantes.size();
    }

    public float percentualM() {

        float percentualM = 0;

        if (listaParticipantes.isEmpty()) {
            return 0;
        } else {
            for (Participante cont : listaParticipantes) {
                if (cont.getSexo().equalsIgnoreCase("masculino")) {
                    percentualM++;
                }
            }
            return percentualM / listaParticipantes.size() * 100;
        }
    }

    public float percentualF() {

        float percentualF = 0;

        if (listaParticipantes.isEmpty()) {
            return 0;
        } else {
            for (Participante cont : listaParticipantes) {
                if (cont.getSexo().equalsIgnoreCase("feminino")) {
                    percentualF++;
                }
            }
            return percentualF / listaParticipantes.size() * 100;
        }
    }

  }
