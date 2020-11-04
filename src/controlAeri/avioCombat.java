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

    // Retorna el número de missils que té l'avió carregat
    @Override
    public int getMissils() {
        return this.missils.size();
    }

    // Retorna el bàndol de l'avió
    @Override
    public String getBandol() {
        return bandol;
    }

    // Restorna la distància màxima de tir que té l'avió
    @Override
    public int getDistanciaMaxTir() {
        return this.distanciaMaxDisparar;
    }

    // Determina si es pot disparar el missil, si ho pot fer ho realitza i retorna un true, sino, retorna un false
    @Override
    public boolean dispararMissil() {
        boolean resposta = false;

        if (missils.size() > 0) {

            missils.remove(0);
            resposta = true;

        }

        return resposta;
    }

    // Xifra l'avió mitjançant una contrasenya
    @Override
    public void xifrar(String contrasenya) {

    }

    // Desxifra l'avió si troba la contrasenya
    @Override
    public void desxifrar(String contrasenya) {

    }
}
