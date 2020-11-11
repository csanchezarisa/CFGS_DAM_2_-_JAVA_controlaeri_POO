package controlAeri;

import java.util.ArrayList;

public class avioCombat extends avio {

    private ArrayList<missils> missils = new ArrayList<missils>();
    private int distanciaMaxDisparar;
    private String bandol;
    private int numMissils;
    private boolean encriptat;
    private int contrasenya;

    public avioCombat(String matricula, String marca, String model, int tripulants, int autonomia, int distanciaMaxDisparar, String bandol, int numMissils) {
        super(matricula, marca, model, tripulants, autonomia);

        this.distanciaMaxDisparar = distanciaMaxDisparar;
        this.bandol = bandol;
        this.numMissils = numMissils;
        this.encriptat = false;

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
    public boolean xifrar(int contrasenya) {
        boolean xifrat = false;

        if (!this.encriptat) {

            this.encriptat = true;
            this.contrasenya = contrasenya;
            xifrarAtributs(contrasenya);
            xifrat = true;

        }

        return xifrat;
    }

    // Mètode que xifra l'avió
    /* Rep per paràmetres un integer. Aquest integer es conerteix en String i es van sumant els seus dígits fins que només en queda 2.
    *  Un cop aconseguit aquest dígit es suma a tots els atributs de l'avió. En cas de ser un atribut numèric és senzill, es suma aquesta
    *  quantitat i ja està, pero si és un String, es suma aquesta quantitat per cada una de les lletres que conformen la paraula */
    private void xifrarAtributs(int contrasenya) {

        // Es converteix la contrasenya numèrica a String
        String contrasenyaText = String.valueOf(contrasenya);

        // Es sumen tots els dígits fins que només hi queda 2 i es retorna un String amb aquests dos dígits.
        while (contrasenyaText.length() > 2) {

            int contrasenyaSumada = 0;

            for (int index = 0; index < contrasenyaText.length(); index++) {

                contrasenyaSumada = contrasenyaSumada + Integer.parseInt(String.valueOf(contrasenyaText.charAt(index)));

            }

            contrasenyaText = String.valueOf(contrasenyaSumada);

        }

        // Aquest String es converteix en integer i és amb la variable amb la que es faran els càlculs
        contrasenya = Integer.parseInt(contrasenyaText);

        // Variables que s'usaran per emmagatzemar els Strings dels atributs antics i dels que es generaran nous
        String nouAtribut = "";
        String atributAntic = "";

        atributAntic = getModel();

        for (int index = 0; index < atributAntic.length(); index++) {
            int lletraNumerica = ((int) atributAntic.charAt(index)) + contrasenya;
            nouAtribut = nouAtribut + ((char) lletraNumerica);
        }

        this.setModel(nouAtribut);
    }

    // Desxifra l'avió si troba la contrasenya
    @Override
    public boolean desxifrar(int contrasenya) {
        boolean desxifrat = false;

        if (this.encriptat && this.contrasenya == contrasenya) {

            this.contrasenya = 0;
            this.encriptat = false;
            desxifrarAtributs(contrasenya);
            desxifrat = true;

        }

        return desxifrat;
    }

    // Mètode que desxifra l'avió
    /* Rep per paràmetres un integer. Aquest integer es conerteix en String i es van sumant els seus dígits fins que només en queda 2.
     * Un cop aconseguit aquest dígit es resta a tots els atributs de l'avió. En cas de ser un atribut numèric és senzill, es resta aquesta
     * quantitat i ja està, pero si és un String, es resta aquesta quantitat per cada una de les lletres que conformen la paraula,
     * donant així l'atribut original */
    private void desxifrarAtributs(int contrasenya) {

        // Es converteix la contrasenya numèrica a String
        String contrasenyaText = String.valueOf(contrasenya);

        // Es sumen tots els dígits fins que només hi queda 2 i es retorna un String amb aquests dos dígits.
        while (contrasenyaText.length() > 2) {

            int contrasenyaSumada = 0;

            for (int index = 0; index < contrasenyaText.length(); index++) {

                contrasenyaSumada = contrasenyaSumada + Integer.parseInt(String.valueOf(contrasenyaText.charAt(index)));

            }

            contrasenyaText = String.valueOf(contrasenyaSumada);

        }

        // Aquest String es converteix en integer i és amb la variable amb la que es faran els càlculs
        contrasenya = Integer.parseInt(contrasenyaText);

        // Variables que s'usaran per emmagatzemar els Strings dels atributs antics i dels que es generaran nous
        String nouAtribut = "";
        String atributAntic = "";

        atributAntic = getModel();

        for (int index = 0; index < atributAntic.length(); index++) {
            int lletraNumerica = ((int) atributAntic.charAt(index)) - contrasenya;
            nouAtribut = nouAtribut + ((char) lletraNumerica);
        }

        this.setModel(nouAtribut);

    }

    @Override
    public boolean isEncriptat() {
        return this.encriptat;
    }
}
