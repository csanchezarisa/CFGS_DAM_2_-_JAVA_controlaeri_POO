package controlAeri;

import java.util.ArrayList;

public class avioCombat extends avio {

    private ArrayList<missils> missils = new ArrayList<missils>();
    private int distanciaMaxDisparar;
    private String bandol;

    public avioCombat(String matricula, String marca, String model, int tripulants, int autonomia, int acceleracio, int velocitatMaxima, int distanciaMaxDisparar, String bandol) {
        super(matricula, marca, model, tripulants, autonomia, acceleracio, velocitatMaxima);

        this.distanciaMaxDisparar = distanciaMaxDisparar;
        this.bandol = bandol;

    }
}
