package controlAeri;

import java.util.ArrayList;

public class avioCombat extends avio {

    private ArrayList<missils> missils = new ArrayList<missils>();
    private int distanciaMaxDisparar;
    private String bandol;
    private int numMissils;

    public avioCombat(String matricula, String marca, String model, int tripulants, int autonomia, int distanciaMaxDisparar, String bandol, int numMissils) {
        super(matricula, marca, model, tripulants, autonomia);

        this.distanciaMaxDisparar = distanciaMaxDisparar;
        this.bandol = bandol;
        this.numMissils = numMissils;

    }
}
