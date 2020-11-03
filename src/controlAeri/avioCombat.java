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

    // Afageix tants missils com s'indiquin
    @Override
    public boolean afegirMissils(int missilsNous) {
        boolean resposta = false;

        if ((missils.size() + missilsNous) <= numMissils) {
            for (int i = 0; i < missilsNous; i++) {
                    missils missil = new missils();
                    this.missils.add(missil);
            }
            resposta = true;
        }

        return resposta;
    }

    @Override
    public int getMissils() {
        return this.missils.size();
    }

    @Override
    public String getBandol() {
        return bandol;
    }

    @Override
    public int getDistanciaMaxTir() {
        return this.distanciaMaxDisparar;
    }
}
