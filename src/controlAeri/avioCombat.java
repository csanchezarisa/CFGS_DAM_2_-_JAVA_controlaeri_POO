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
        String nouAtributString = "";
        String atributAnticString = "";
        int nouAtributInt = 0;
        int atributAnticInt = 0;

        // Xifrar model
        atributAnticString = getModel();
        for (int index = 0; index < atributAnticString.length(); index++) {
            int lletraNumerica = ((int) atributAnticString.charAt(index)) + contrasenya;
            nouAtributString = nouAtributString + ((char) lletraNumerica);
        }
        this.setModel(nouAtributString);

        // Xifrar marca
        atributAnticString = getMarca();
        nouAtributString = "";
        for (int index = 0; index < atributAnticString.length(); index++) {
            int lletraNumerica = ((int) atributAnticString.charAt(index)) + contrasenya;
            nouAtributString = nouAtributString + ((char) lletraNumerica);
        }
        this.setMarca(nouAtributString);

        // Xifrar tripulants
        atributAnticInt = this.getTripulants();
        nouAtributInt = atributAnticInt + contrasenya;
        this.setTripulants(nouAtributInt);

        // Xifrar coodenades
        atributAnticInt = this.getCoordenadaX();
        nouAtributInt = atributAnticInt + contrasenya;
        int x = nouAtributInt;
        atributAnticInt = this.getCoordenadaY();
        nouAtributInt = atributAnticInt + contrasenya;
        int y = nouAtributInt;
        this.setCoordenades(x, y);

        // Xifrar tripulants
        atributAnticInt = this.getTripulants();
        nouAtributInt = atributAnticInt + contrasenya;
        this.setTripulants(nouAtributInt);

        // Xifrar alçada
        atributAnticInt = this.getAlcada();
        nouAtributInt = atributAnticInt + contrasenya;
        this.setAlcada(nouAtributInt);

        // Xifrar autonomia
        atributAnticInt = this.getAutonomia();
        nouAtributInt = atributAnticInt + contrasenya;
        this.setAutonomia(nouAtributInt);

        // Xifrar rumb
        atributAnticInt = this.getRumb();
        nouAtributInt = atributAnticInt + contrasenya;
        this.setRumb(nouAtributInt);

        // Xifrar velocitat
        atributAnticInt = this.getVelocitat();
        nouAtributInt = atributAnticInt + contrasenya;
        this.setVelocitat(nouAtributInt);

        // Xifrar velocitat màxima
        atributAnticInt = this.getVelocitatMaxima();
        nouAtributInt = atributAnticInt + contrasenya;
        this.setVelocitatMaxima(nouAtributInt);

        // Xifrar distància màxima de dispar
        atributAnticInt = this.distanciaMaxDisparar;
        nouAtributInt = atributAnticInt + contrasenya;
        this.distanciaMaxDisparar = nouAtributInt;

        // Xifrar número de missils
        atributAnticInt = this.numMissils;
        nouAtributInt = atributAnticInt + contrasenya;
        this.numMissils = nouAtributInt;
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
        String nouAtributString = "";
        String atributAnticString = "";
        int nouAtributInt = 0;
        int atributAnticInt = 0;

        // Desxifrar model
        atributAnticString = getModel();
        for (int index = 0; index < atributAnticString.length(); index++) {
            int lletraNumerica = ((int) atributAnticString.charAt(index)) - contrasenya;
            nouAtributString = nouAtributString + ((char) lletraNumerica);
        }
        this.setModel(nouAtributString);

        // Desxifrar marca
        atributAnticString = getMarca();
        nouAtributString = "";
        for (int index = 0; index < atributAnticString.length(); index++) {
            int lletraNumerica = ((int) atributAnticString.charAt(index)) - contrasenya;
            nouAtributString = nouAtributString + ((char) lletraNumerica);
        }
        this.setMarca(nouAtributString);

        // Desxifrar tripulants
        atributAnticInt = this.getTripulants();
        nouAtributInt = atributAnticInt - contrasenya;
        this.setTripulants(nouAtributInt);

        // Desxifrar coodenades
        atributAnticInt = this.getCoordenadaX();
        nouAtributInt = atributAnticInt - contrasenya;
        int x = nouAtributInt;
        atributAnticInt = this.getCoordenadaY();
        nouAtributInt = atributAnticInt - contrasenya;
        int y = nouAtributInt;
        this.setCoordenades(x, y);

        // Desxifrar tripulants
        atributAnticInt = this.getTripulants();
        nouAtributInt = atributAnticInt - contrasenya;
        this.setTripulants(nouAtributInt);

        // Desxifrar alçada
        atributAnticInt = this.getAlcada();
        nouAtributInt = atributAnticInt - contrasenya;
        this.setAlcada(nouAtributInt);

        // Desxifrar autonomia
        atributAnticInt = this.getAutonomia();
        nouAtributInt = atributAnticInt - contrasenya;
        this.setAutonomia(nouAtributInt);

        // Desxifrar rumb
        atributAnticInt = this.getRumb();
        nouAtributInt = atributAnticInt - contrasenya;
        this.setRumb(nouAtributInt);

        // Desxifrar velocitat
        atributAnticInt = this.getVelocitat();
        nouAtributInt = atributAnticInt - contrasenya;
        this.setVelocitat(nouAtributInt);

        // Desxifrar velocitat màxima
        atributAnticInt = this.getVelocitatMaxima();
        nouAtributInt = atributAnticInt - contrasenya;
        this.setVelocitatMaxima(nouAtributInt);

        // Desxifrar distància màxima de dispar
        atributAnticInt = this.distanciaMaxDisparar;
        nouAtributInt = atributAnticInt - contrasenya;
        this.distanciaMaxDisparar = nouAtributInt;

        // Desxifrar número de missils
        atributAnticInt = this.numMissils;
        nouAtributInt = atributAnticInt - contrasenya;
        this.numMissils = nouAtributInt;

    }

    @Override
    public boolean isEncriptat() {
        return this.encriptat;
    }
}
