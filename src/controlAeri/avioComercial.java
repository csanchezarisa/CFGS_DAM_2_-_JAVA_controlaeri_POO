package controlAeri;

public class avioComercial extends avio {

    private String origen;
    private String desti;
    private int capacitatPassatgers;

    public avioComercial(String matricula, String marca, String model, int tripulants, int autonomia, int acceleracio, int velocitatMaxima, String origen, String desti, int capacitatPassatgers) {
        super(matricula, marca, model, tripulants, autonomia, acceleracio, velocitatMaxima);

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
