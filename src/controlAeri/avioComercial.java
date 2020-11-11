package controlAeri;

public class avioComercial extends avio {

    private String origen;
    private String desti;
    private int capacitatPassatgers;

    // Constructor utilitzat per generar avions de manera manual
    public avioComercial(String matricula, String marca, String model, int tripulants, int autonomia, String origen, String desti, int capacitatPassatgers) {
        super(matricula, marca, model, tripulants, autonomia);

        this.origen = origen;
        this.desti = desti;
        this.capacitatPassatgers = capacitatPassatgers;

    }

    // Constructor utilitzat per crear avions de manera automàtica per fer el test
    public avioComercial(String matricula, String marca, String model, int tripulants, coordenades coordenades, int alcada, int autonomia, int rumb, int velocitat, int velocitatMaxima, boolean motor, boolean trenAterratge, boolean accelerar, String origen, String desti, int capacitatPassatgers) {
        super(matricula, marca, model, tripulants, coordenades, alcada, autonomia, rumb, velocitat, velocitatMaxima, motor, trenAterratge, accelerar);
        this.origen = origen;
        this.desti = desti;
        this.capacitatPassatgers = capacitatPassatgers;
    }

    @Override
    public String getOrigen() {
        return origen;
    }

    @Override
    public String getDesti() {
        return desti;
    }

    @Override
    public int getCapacitatPassatgers() {
        return capacitatPassatgers;
    }

}
