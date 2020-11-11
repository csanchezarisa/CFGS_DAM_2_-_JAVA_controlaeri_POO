package controlAeri;

import java.util.ArrayList;

public class avio {

    private String matricula;
    private String marca;
    private String model;
    private int tripulants;
    private coordenades coordenades;
    private int alcada;
    private int autonomia;
    private int rumb;
    private int velocitat;
    private int velocitatMaxima;
    private boolean motor;
    private boolean trenAterratge;
    private boolean accelerar;

    public avio(String matricula, String marca, String model, int tripulants, int autonomia) {

        this.matricula = matricula;
        this.marca = marca;
        this.model = model;
        this.tripulants = tripulants;
        this.coordenades = new coordenades(100, 100);
        this.alcada = alcada;
        this.autonomia = autonomia;
        this.rumb = 0;
        this.velocitat = 0;
        this.motor = false;
        this.trenAterratge = true;
        this.accelerar = false;

    }

    // "encen" el motor
    public void encendreMotor() {
        this.motor = true;
    }

    // Apaga el motor
    public void apagarMotor() {

        this.motor = false;
        this.accelerar = false;

    }

    // Accelera l'avio
    public boolean accelerar(int velocitat) {
        boolean resposta = false;

        if (motor) {
            this.accelerar = true;
            this.velocitat = velocitat;
            resposta = true;
        }

        return resposta;
    }

    // Frena l'avio
    public boolean frenar() {
        boolean resposta = false;

        if (motor) {
            this.accelerar = false;
            this.velocitat = 0;
            resposta = true;
        }

        return resposta;
    }

    // Revisa si l'avio pot agafar alçada
    public boolean canviarAlcada(int alcada) {
        boolean resposta = false;

        if (this.velocitat >= 180 && accelerar && alcada >= 0) {

            if (alcada > 500 && !this.trenAterratge && this.accelerar) {
                this.alcada = alcada;
                resposta = true;
            }
            else if (alcada <= 500 && this.accelerar) {
                this.alcada = alcada;
                resposta = true;
            }
        }

        return resposta;
    }

    // Permet canviar les coordenades de l'avio. Es pot moure a qualsevol coordenada, després el control actualitza les dades i esborra els avions que es troben a fora del marge
    public boolean canviarCoordenades(int x, int y) {
        boolean resposta = false;

        if (this.accelerar) {

            this.coordenades.setX(x);
            this.coordenades.setY(y);
            resposta = true;
        }

        return resposta;
    }

    // Canvia l'estat del tren d'aterratge
    public boolean canviarEstatTrenAterratge() {
        boolean resposta = false;

        if (this.trenAterratge) {
            resposta = pujarTrenAterratge();
        }
        else {
            resposta = baixarTrenAterratge();
        }

        return resposta;
    }

    // Puja el tren d'aterratge
    private boolean pujarTrenAterratge() {
        boolean resposta = false;

        if ((this.coordenades.getY() != 100 && !(this.coordenades.getX() >= 100 && this.coordenades.getX() <= 120)) && this.alcada > 0) {

            this.trenAterratge = false;
            resposta = true;

        }

        return resposta;
    }

    // Baixa el tren d'aterratge
    private boolean baixarTrenAterratge() {
        boolean resposta = false;

        if (this.coordenades.getX() <= 500 && this.velocitat <= 300) {

            this.trenAterratge = true;
            resposta = true;

        }

        return resposta;
    }

    // Permet canviar el rumb de l'avio. Si es possible
    public boolean canviarRumb(int rumb) {
        boolean resposta = false;

        if (rumb >= 0 && rumb <= 360) {
            if (motor && accelerar) {
                this.rumb = rumb;
                resposta = true;
            }
        }

        return resposta;
    }

    // Aterra l'avió
    public boolean aterra() {
        boolean resposta = false;

        // Es troba dins de la pista d'aterratge?
        if ((this.coordenades.getY() == 100 && (this.coordenades.getX() >= 100 || this.coordenades.getX() <= 120)) && this.alcada == 0) {
            // Velocitat adequada i tren d'aterratge preparat?
            if (this.velocitat <= 200 && this.trenAterratge) {

                this.velocitat = 0;
                this.accelerar = false;
                resposta = true;

            }
        }

        return resposta;
    }

    // GETTERS
    public String getMatricula() {
        return matricula;
    }

    public String getMarca() {
        return marca;
    }

    public String getModel() {
        return model;
    }

    public int getCoordenadaX() {
        return coordenades.getX();
    }

    public int getCoordenadaY() {
        return coordenades.getY();
    }

    public int getVelocitat() {
        return velocitat;
    }

    public boolean isTrenAterratge() {
        return trenAterratge;
    }

    public boolean isMotor() {
        return motor;
    }

    public int getAlcada() {
        return alcada;
    }

    // Mètodes de les classes heretades. Aquí retornen null. Es sobreescriuen en les subclasses.
    public int getMissils() {
        return 0;
    }

    public String getOrigen() {
        return "";
    }

    public String getDesti() {
        return "";
    }

    public int getCapacitatPassatgers() {
        return 0;
    }

    public boolean dispararMissil() {
        return false;
    }

    public String getBandol() {
        return "";
    }

    public boolean afegirMissils(int numMissils) {
        return false;
    }

    public int getDistanciaMaxTir() {
        return 0;
    }

    public boolean xifrar(int contrasenya) {
        return false;
    }

    public boolean desxifrar(int contrasenya) {
        return false;
    }

    public boolean isEncriptat() {
        return false;
    }

    // SETTERS protegits per poder utilitzar-los en les clases heretades
    protected void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    protected void setModel(String model) {
        this.model = model;
    }

    protected void setTripulants(int tripulants) {
        this.tripulants = tripulants;
    }

    protected void setCoordenades(int x, int y) {
        this.coordenades.setX(x);
        this.coordenades.setY(y);
    }

    protected void setAlcada(int alcada) {
        this.alcada = alcada;
    }

    protected void setAutonomia(int autonomia) {
        this.autonomia = autonomia;
    }

    protected void setRumb(int rumb) {
        this.rumb = rumb;
    }

    protected void setVelocitat(int velocitat) {
        this.velocitat = velocitat;
    }

    protected void setVelocitatMaxima(int velocitatMaxima) {
        this.velocitatMaxima = velocitatMaxima;
    }
}