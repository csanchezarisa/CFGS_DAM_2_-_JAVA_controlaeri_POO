package controlAeri;

import complements.Colors;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class controlAeri {

    public Scanner teclat = new Scanner(System.in);

    private ArrayList<avio> avions = new ArrayList<avio>();

    // S'executa el mètode launch, per evitar haver de treballar amb static
    public static void main(String[] args) throws InterruptedException {

        controlAeri launcher = new controlAeri();
        launcher.launch();

    }


    /* .: 1. MENU PRINCIPAL :. */
    // Metode principal del programa. Mostra el menu principal i permet excollir la primera opcio
    public void launch() throws InterruptedException {

        char seleccioUsuari = '0';

        do {

            resetTeclat();

            netejarPantalla();
            mostrarMenuPrincipal();
            seleccioUsuari = teclat.nextLine().charAt(0);

            switch (seleccioUsuari) {

                case '1':
                    afegirAvio();
                    break;

                case '2':
                    break;

                case '3':
                    break;

                case '4':
                    break;

                case '5':
                    break;

                case '6':
                    break;

                default:
                    System.out.println(Colors.RED + "INTRODUEIX UNA OPCIO VALIDA" + Colors.RESET);
                    atura();

            }

        }
        while (seleccioUsuari != '6');

    }

    // Mostra el menu principal
    private void mostrarMenuPrincipal() {

        System.out.println(Colors.BLUE + "------------------------------" + Colors.RESET);
        System.out.println(Colors.BLUE + "-------- " + Colors.PURPLE + "CONTROL AERI" + Colors.BLUE + " --------" + Colors.RESET);
        System.out.println(Colors.BLUE + "------------------------------" + Colors.RESET);
        System.out.println(Colors.BLUE + "- " + Colors.RESET + "1. Afegir avions" + Colors.BLUE + "           -" + Colors.RESET);
        System.out.println(Colors.BLUE + "- " + Colors.RESET + "2. Gestionar avio" + Colors.BLUE + "          -" + Colors.RESET);
        System.out.println(Colors.BLUE + "- " + Colors.RESET + "3. Mostrar informacio" + Colors.BLUE + "      -" + Colors.RESET);
        System.out.println(Colors.BLUE + "- " + Colors.RESET + "4. Xifrar avions" + Colors.BLUE + "           -" + Colors.RESET);
        System.out.println(Colors.BLUE + "- " + Colors.RESET + "5. Desxifrar avions" + Colors.BLUE + "        -" + Colors.RESET);
        System.out.println(Colors.BLUE + "- " + Colors.RESET + "6. Sortir" + Colors.BLUE + "                  -");
        System.out.println(Colors.BLUE + "------------------------------" + Colors.RESET);

    }


    /* .: 1.1. MENU AFEGIR AVIO :. */
    // Comprova si hi ha espai per crear-ne mes avions i permet crear-ne de nous
    private void afegirAvio() throws InterruptedException {

        if (avions.size() < 10) {

            char seleccioUsuari = '0';

            resetTeclat();

            netejarPantalla();
            mostrarMenuAfegirAvioSeleccioTipus();
            seleccioUsuari = teclat.nextLine().charAt(0);

            switch (seleccioUsuari) {

                case '1':
                    afegirAvioComercial();
                    break;

                case '2':
                    afegirAvioCombat();
                    break;

                case '3':
                    break;

                default:
                    System.out.println(Colors.RED + "INTRODUEIX UNA OPCIO VALIDA" + Colors.RESET);
                    atura();

            }

        }
        else {

            System.out.println(Colors.RED + "JA S'HAN AFEGIT EL MAXIM NUMERO D'AVIONS" + Colors.RESET);
            atura();

        }

    }

    // Mostra el menu de seleccio del tipus d'avio
    private void mostrarMenuAfegirAvioSeleccioTipus() {
        String pistaLliure;
        String colorEmergencia;

        if (pistaOcupada()) {
            pistaLliure = "La pista no està lliure";
            colorEmergencia = Colors.BLACK + Colors.RED_BACKGROUND;
        }
        else {
            pistaLliure = "La pista està lliure";
            colorEmergencia = Colors.GREEN;
        }

        System.out.println(Colors.BLUE + "------------------------------" + Colors.RESET);
        System.out.println(Colors.BLUE + "---- " + Colors.PURPLE + "SELECCIONA EL TIPUS" + Colors.BLUE + " -----" + Colors.RESET);
        System.out.println(Colors.BLUE + "------------------------------" + Colors.RESET);
        System.out.println(Colors.BLUE + "- " + Colors.RESET + "1. Comercial" + Colors.BLUE + "               -" + Colors.RESET);
        System.out.println(Colors.BLUE + "- " + Colors.RESET + "2. Combat" + Colors.BLUE + "                  -" + Colors.RESET);
        System.out.println(Colors.BLUE + "- " + Colors.RESET + "3. Sortir" + Colors.BLUE + "                  -" + Colors.RESET);
        System.out.println(Colors.BLUE + "------------------------------" + Colors.RESET);
        System.out.println(Colors.BLUE + "- " + colorEmergencia + pistaLliure + Colors.RESET + Colors.BLUE + " ".repeat(27 - pistaLliure.length()) + "-" + Colors.RESET);
        System.out.println(Colors.BLUE + "------------------------------" + Colors.RESET);


    }


    /* .: 1.1.1. AFEGIR AVIÓ COMERCIAL :. */
    private void afegirAvioComercial() throws InterruptedException {

        netejarPantalla();

        try {

            System.out.println(Colors.WHITE_BACKGROUND + Colors.BLACK + "Introdueix la marca de l'avió" + Colors.RESET);
            String marca = teclat.nextLine().toUpperCase();
            marca = (marca.length() > 7) ? marca.substring(0, 7) : marca;

            System.out.println(Colors.WHITE_BACKGROUND + Colors.BLACK + "Introdueix el model de l'avió" + Colors.RESET);
            String model = teclat.nextLine().toUpperCase();
            model = (model.length() > 8) ? model.substring(0, 7) : model;

            System.out.println(Colors.WHITE_BACKGROUND + Colors.BLACK + "Introdueix la matrícula de l'avió" + Colors.RESET);
            String matricula = teclat.nextLine().toUpperCase();
            if (matricula.length() > 3)
                matricula = matricula.substring(0, 2);
            else
                matricula = matricula + "0".repeat(3 - matricula.length());

            System.out.println(Colors.WHITE_BACKGROUND + Colors.BLACK + "Introdueix l'origen de l'avió" + Colors.RESET);
            String origen = teclat.nextLine().toUpperCase();
            origen = (origen.length() > 3) ? origen.substring(0, 2) : origen;

            System.out.println(Colors.WHITE_BACKGROUND + Colors.BLACK + "Introdueix el destí de l'avió" + Colors.RESET);
            String desti = teclat.nextLine().toUpperCase();
            desti = (desti.length() > 3) ? desti.substring(0, 2) : desti;

            System.out.println(Colors.WHITE_BACKGROUND + Colors.BLACK + "Introdueix els tripulants de l'avió" + Colors.RESET);
            int tripulants = teclat.nextInt();

            System.out.println(Colors.WHITE_BACKGROUND + Colors.BLACK + "Introdueix l'autonomia de l'avió" + Colors.RESET);
            int autonomia = teclat.nextInt();

            System.out.println(Colors.WHITE_BACKGROUND + Colors.BLACK + "Introdueix la velocitat màxima de l'avió" + Colors.RESET);
            int velocitatMaxima = teclat.nextInt();

            System.out.println(Colors.WHITE_BACKGROUND + Colors.BLACK + "Introdueix l'acceleració de l'avió" + Colors.RESET);
            int acceleracio = teclat.nextInt();

            System.out.println(Colors.WHITE_BACKGROUND + Colors.BLACK + "Introdueix la capacitat de passatgers de l'avió" + Colors.RESET);
            int capacitatPassatgers = teclat.nextInt();

            resetTeclat();

            if (matriculaRepetida(matricula))
                throw new Exception("Matricula repetida");
            else if (tripulants <= 0 || autonomia <= 0 || velocitatMaxima < 180 || capacitatPassatgers < 0)
                throw new Exception("Valors no vàlids");
            else if (origen.equals(desti))
                throw new Exception("Origen es igual que desti");

            avioComercial avioComercial = new avioComercial(matricula, marca, model, tripulants, autonomia, acceleracio, velocitatMaxima, origen, desti, capacitatPassatgers);

            if (avions.size() < 10)
                avions.add(avioComercial);
            else
                throw new Exception("Maxim nombre d'avions creats");

        }
        catch (Exception e) {
            System.out.println(Colors.RED + "NO S'HA POGUT INTRODUIR L'AVIÓ");
            System.out.println("MOTIU: " + e + Colors.RESET);
            atura();
        }

    }


    /* .: 1.1.2. AFEGIR AVIÓ DE COMBAT :. */
    private void afegirAvioCombat() throws InterruptedException {

        netejarPantalla();

        try {

            System.out.println(Colors.WHITE_BACKGROUND + Colors.BLACK + "Introdueix la marca de l'avió" + Colors.RESET);
            String marca = teclat.nextLine().toUpperCase();
            marca = (marca.length() > 7) ? marca.substring(0, 7) : marca;

            System.out.println(Colors.WHITE_BACKGROUND + Colors.BLACK + "Introdueix el model de l'avió" + Colors.RESET);
            String model = teclat.nextLine().toUpperCase();
            model = (model.length() > 8) ? model.substring(0, 7) : model;

            System.out.println(Colors.WHITE_BACKGROUND + Colors.BLACK + "Introdueix la matrícula de l'avió" + Colors.RESET);
            String matricula = teclat.nextLine().toUpperCase();
            if (matricula.length() > 3)
                matricula = matricula.substring(0, 2);
            else
                matricula = matricula + "0".repeat(3 - matricula.length());

            System.out.println(Colors.WHITE_BACKGROUND + Colors.BLACK + "Introdueix el bàndol de l'avió" + Colors.RESET);
            String bandol = teclat.nextLine().toUpperCase();
            bandol = (bandol.length() > 8) ? bandol.substring(0, 7) : bandol;

            System.out.println(Colors.WHITE_BACKGROUND + Colors.BLACK + "Introdueix els tripulants de l'avió" + Colors.RESET);
            int tripulants = teclat.nextInt();

            System.out.println(Colors.WHITE_BACKGROUND + Colors.BLACK + "Introdueix l'autonomia de l'avió" + Colors.RESET);
            int autonomia = teclat.nextInt();

            System.out.println(Colors.WHITE_BACKGROUND + Colors.BLACK + "Introdueix la velocitat màxima de l'avió" + Colors.RESET);
            int velocitatMaxima = teclat.nextInt();

            System.out.println(Colors.WHITE_BACKGROUND + Colors.BLACK + "Introdueix l'acceleració de l'avió" + Colors.RESET);
            int acceleracio = teclat.nextInt();

            System.out.println(Colors.WHITE_BACKGROUND + Colors.BLACK + "Introdueix la distància màxima per disparar de l'avió" + Colors.RESET);
            int distanciaMaximaDisparar = teclat.nextInt();

            resetTeclat();

            if (matriculaRepetida(matricula))
                throw new Exception("Matricula repetida");
            else if (tripulants <= 0 || autonomia <= 0 || velocitatMaxima < 180 || distanciaMaximaDisparar < 0)
                throw new Exception("Valors no vàlids");

            avioCombat avioCombat = new avioCombat(matricula, marca, model, tripulants, autonomia, acceleracio, velocitatMaxima, distanciaMaximaDisparar, bandol);

            if (avions.size() < 10)
                avions.add(avioCombat);
            else
                throw new Exception("Maxim nombre d'avions creats");

        }
        catch (Exception e) {
            System.out.println(Colors.RED + "NO S'HA POGUT INTRODUIR L'AVIÓ");
            System.out.println("MOTIU: " + e + Colors.RESET);
            atura();
        }

    }





    // Revisa si s'ha trobat alguna repetició amb la matricula introduida
    private boolean matriculaRepetida(String matriculaPerRevisar) {

        boolean repetida = false;

        for (avio avio: avions) {
            if (avio.getMatricula().equals(matriculaPerRevisar)) {
                repetida = true;
                break;
            }
        }
        
        return repetida;

    }


    // Neteja la consola
    public void netejarPantalla() {

        for (int i = 0; i < 50; i++) {
            System.out.println();
        }

    }

    // Pausa el programa
    public void atura() throws InterruptedException {

        Thread.sleep(3000);

    }

    // Reseteja el valor de teclat. Ja que al llegir INT de vegades peta
    public void resetTeclat() {
        teclat = new Scanner(System.in);
    }

    // Revisa si la pista d'einlairament es troba ocupada
    public boolean pistaOcupada() {
        boolean ocupada = false;

        for (avio avio: avions) {
            if (avio.getCoordenadaY() == 100 && (avio.getCoordenadaX() >= 100 && avio.getCoordenadaX() <= 120)) {
                ocupada = true;
                break;
            }
        }

        return ocupada;
    }

    // Busca la posicio en l'array de l'avió amb la matricula desitjada. Retorna -1 si no ha trobat res
    public int buscarPerMatricula(String matriculaPerBuscar) {
        int posicio = -1;

        for (int index = 0; index < avions.size(); index++) {
            if (avions.get(index).getMatricula().equals(matriculaPerBuscar)) {
                posicio = index;
                break;
            }
        }

        return posicio;
    }

}
