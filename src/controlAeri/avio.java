package controlAeri;

import java.util.ArrayList;

public class avio {

    private String matricula;
    private String marca;
    private String model;
    private int tripulants;
    private coordenades coordenades;
    private int autonomia;
    private int rumb;
    private int velocitat;
    private int velocitatMaxima;
    private boolean motor;
    private boolean trenAterratge;
    private int acceleracio;
    private boolean accelerar;

    public avio(String matricula, String marca, String model, int tripulants, int autonomia, int acceleracio, int velocitatMaxima) {

        this.matricula = matricula;
        this.marca = marca;
        this.model = model;
        this.tripulants = tripulants;
        this.coordenades = new coordenades(100, 100);
        this.autonomia = autonomia;
        this.rumb = 0;
        this.velocitat = 0;
        this.velocitatMaxima = velocitatMaxima;
        this.motor = false;
        this.trenAterratge = true;
        this.acceleracio = acceleracio;
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
    public boolean accelerar() {
        boolean resposta = false;

        if (motor) {
            this.accelerar = true;
            resposta = true;
        }

        return resposta;
    }

    // Frena l'avio
    public boolean frenar() {
        boolean resposta = false;

        if (motor) {
            this.accelerar = false;
            resposta = true;
        }

        return resposta;
    }

    // Canvia el rumb de l'avio per que agafi alçada
    public boolean pujar() {
        boolean resposta = false;

        if (this.velocitat >= 180 && accelerar) {
            this.rumb = 45;
            resposta = true;
        }

        return resposta;
    }

    // Canvia el rumb de l'avio per que perdi alçada
    public boolean baixar() {
        boolean resposta = false;

        if (accelerar) {
            this.rumb = 315;
            resposta = true;
        }

        return resposta;
    }

    // Permet canviar les coordenades de l'avio
    public void canviarCoordenades(int x, int y) {

        this.coordenades.setX(x);
        this.coordenades.setY(y);

    }

    // Puja el tren d'aterratge
    public boolean pujarTrenAterratge() {
        boolean resposta = false;

        if (this.coordenades.getY() != 100 && !(this.coordenades.getX() >= 100 || this.coordenades.getX() <= 120)) {

            this.trenAterratge = false;
            resposta = true;

        }

        return resposta;
    }

    // Baixa el tren d'aterratge
    public boolean baixarTrenAterratge() {
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

    // Getters de les classes heretades. Aquí retornen null. Es sobreescriuen en les subclasses.
    public ArrayList<missils> getMissils() {
        return null;
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
}