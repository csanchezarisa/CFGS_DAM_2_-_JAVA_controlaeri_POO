package controlAeri;

import complements.Colors;

import java.awt.*;
import java.beans.beancontext.BeanContextServiceRevokedEvent;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Scanner;

public class controlAeri {

    // Constants que defineixen l'alcanç que té el controlador aeri
    static public final int RANGESPAIAERI_X = 1000;
    static public final int RANGESPAIAERI_Y = 1000;
    static public final int RANGESPAIAERI_ALCADA = 5000;

    public Scanner teclat = new Scanner(System.in);

    // Llistat amb els avions que s'estan gestionant
    private ArrayList<avio> avions = new ArrayList<avio>();

    // S'executa el mètode launch, per evitar haver de treballar amb static
    public static void main(String[] args) throws InterruptedException {

        controlAeri launcher = new controlAeri();
        launcher.launch();

    }


    /* .: 1. MENU PRINCIPAL :. */
    // Metode principal del programa. Mostra el menu principal i permet excollir la primera opcio. A més, sempre que s'hi accedeix actualitza les dades del controlador, avisant dels problemes que ha hagut
    public void launch() throws InterruptedException {

        char seleccioUsuari = '0';

        do {

            resetTeclat();

            netejarPantalla();
            mostrarMenuPrincipal();
            try {
                seleccioUsuari = teclat.nextLine().charAt(0);
            }
            catch (Exception e) {
                seleccioUsuari = ' ';
            }

            switch (seleccioUsuari) {

                case '1':
                    // Afegir avió
                    afegirAvio();
                    break;

                case '2':
                    // Gestionar avió
                    menuSeleccioAvio();
                    break;

                case '3':
                    // Mostrar la informació de l'espai aeri
                    mostrarInformacio();
                    break;

                case '4':
                    // Xifrar els avions
                    menuXifrarAvions();
                    break;

                case '5':
                    // Desxifar els avions
                    menuDesxifrarAvions();
                    break;

                case '6':
                    // Sortir
                    break;

                default:
                    System.out.println(Colors.RED + "INTRODUEIX UNA OPCIO VALIDA" + Colors.RESET);
                    atura();

            }

            actualitzarEstatControladorAeri();

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
            try {
                seleccioUsuari = teclat.nextLine().charAt(0);
            }
            catch (Exception e) {
                seleccioUsuari = ' ';
            }

            switch (seleccioUsuari) {

                case '1':
                    afegirAvioComercial();
                    break;

                case '2':
                    afegirAvioCombat();
                    break;

                case '3':
                    // Sortir
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
            model = (model.length() > 7) ? model.substring(0, 7) : model;

            System.out.println(Colors.WHITE_BACKGROUND + Colors.BLACK + "Introdueix la matrícula de l'avió" + Colors.RESET);
            String matricula = teclat.nextLine().toUpperCase();
            if (matricula.length() > 3)
                matricula = matricula.substring(0, 3);
            else
                matricula = matricula + "0".repeat(3 - matricula.length());

            System.out.println(Colors.WHITE_BACKGROUND + Colors.BLACK + "Introdueix l'origen de l'avió" + Colors.RESET);
            String origen = teclat.nextLine().toUpperCase();
            origen = (origen.length() > 3) ? origen.substring(0, 3) : origen;

            System.out.println(Colors.WHITE_BACKGROUND + Colors.BLACK + "Introdueix el destí de l'avió" + Colors.RESET);
            String desti = teclat.nextLine().toUpperCase();
            desti = (desti.length() > 3) ? desti.substring(0, 3) : desti;

            System.out.println(Colors.WHITE_BACKGROUND + Colors.BLACK + "Introdueix els tripulants de l'avió" + Colors.RESET);
            int tripulants = teclat.nextInt();

            System.out.println(Colors.WHITE_BACKGROUND + Colors.BLACK + "Introdueix l'autonomia de l'avió" + Colors.RESET);
            int autonomia = teclat.nextInt();

            System.out.println(Colors.WHITE_BACKGROUND + Colors.BLACK + "Introdueix la capacitat de passatgers de l'avió" + Colors.RESET);
            int capacitatPassatgers = teclat.nextInt();

            resetTeclat();

            if (matriculaRepetida(matricula))
                throw new Exception("Matricula repetida");
            else if (tripulants <= 0 || autonomia <= 0 || capacitatPassatgers < 0)
                throw new Exception("Valors no vàlids");
            else if (origen.equals(desti))
                throw new Exception("Origen es igual que desti");

            avioComercial avioComercial = new avioComercial(matricula, marca, model, tripulants, autonomia, origen, desti, capacitatPassatgers);

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
            model = (model.length() > 7) ? model.substring(0, 7) : model;

            System.out.println(Colors.WHITE_BACKGROUND + Colors.BLACK + "Introdueix la matrícula de l'avió" + Colors.RESET);
            String matricula = teclat.nextLine().toUpperCase();
            if (matricula.length() > 3)
                matricula = matricula.substring(0, 3);
            else
                matricula = matricula + "0".repeat(3 - matricula.length());

            System.out.println(Colors.WHITE_BACKGROUND + Colors.BLACK + "Introdueix el bàndol de l'avió" + Colors.RESET);
            String bandol = teclat.nextLine().toUpperCase();
            bandol = (bandol.length() > 7) ? bandol.substring(0, 7) : bandol;

            System.out.println(Colors.WHITE_BACKGROUND + Colors.BLACK + "Introdueix els tripulants de l'avió" + Colors.RESET);
            int tripulants = teclat.nextInt();

            System.out.println(Colors.WHITE_BACKGROUND + Colors.BLACK + "Introdueix l'autonomia de l'avió" + Colors.RESET);
            int autonomia = teclat.nextInt();

            System.out.println(Colors.WHITE_BACKGROUND + Colors.BLACK + "Introdueix la distància màxima per disparar de l'avió" + Colors.RESET);
            int distanciaMaximaDisparar = teclat.nextInt();

            System.out.println(Colors.WHITE_BACKGROUND + Colors.BLACK + "Introdueix la número màxim de missils de l'avió" + Colors.RESET);
            int numMissils = teclat.nextInt();

            resetTeclat();

            if (matriculaRepetida(matricula))
                throw new Exception("Matricula repetida");
            else if (tripulants <= 0 || autonomia <= 0 || distanciaMaximaDisparar < 0)
                throw new Exception("Valors no vàlids");

            avioCombat avioCombat = new avioCombat(matricula, marca, model, tripulants, autonomia, distanciaMaximaDisparar, bandol, numMissils);

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


    /* .: 2. GESTIONAR AVIONS :. */
    /* .: 2.1. MENÚ SELECCIO AVIO :. */
    private void menuSeleccioAvio() throws InterruptedException {

        netejarPantalla();
        System.out.print("Introdueix la matrícula de l'avió que vols gestionar: ");
        resetTeclat();
        String matricula;
        try {
            matricula = teclat.nextLine();
        }
        catch (Exception e) {
            matricula = "";
        }

        int posicioArray = buscarPerMatricula(matricula.toUpperCase());

        if (posicioArray >= 0) {
            gestionarAvio(posicioArray, avions.get(posicioArray) instanceof avioComercial);
        }
        else {
            System.out.println(Colors.RED + "NO S'HA TROBAT L'AVIO AMB LA MATRÍCULA " + matricula + " PROVA-HO DE NOU" + Colors.RESET);
            atura();
        }

    }

    /* .: 2.2. GESTIONAR AVIO :. */
    private void gestionarAvio(int avioSeleccionat, boolean esComercial) throws InterruptedException {

        int seleccioUsuari = 0;

        // Aquí es diferencien dos menús, un per si és comercial i un altre per si és de combat
        if (esComercial) {

            do {

                netejarPantalla();
                menuGestionarAvioComercial(avions.get(avioSeleccionat).getMatricula());
                resetTeclat();
                try {

                    seleccioUsuari = teclat.nextInt();

                }
                catch (Exception e) {

                    seleccioUsuari = 0;

                }

                resetTeclat();
                switch (seleccioUsuari) {

                    case 1:
                        // Encén motor
                        avions.get(avioSeleccionat).encendreMotor();
                        break;

                    case 2:
                        // Atura motor
                        avions.get(avioSeleccionat).apagarMotor();
                        break;

                    case 3:
                        // Accelera l'avió
                        int velocitat;
                        try {

                            System.out.println("Introdueix la velocitat de l'avió:");
                            velocitat = teclat.nextInt();

                        }
                        catch (Exception e) {

                            System.out.println(Colors.RED + "NO S'HA POGUT CANVIAR LA VELOCITAT");
                            System.out.println("MOTIU: " + e + Colors.RESET);
                            atura();
                            break;

                        }

                        if (!avions.get(avioSeleccionat).accelerar(velocitat)) {
                            mostrarError("NO S'HA POGUT ACCELERAR, REVISA L'ESTAT DEL MOTOR");
                        }
                        break;

                    case 4:
                        // Frena l'avió
                        if (!avions.get(avioSeleccionat).frenar()) {
                            mostrarError("NO S'HA POGUT ACCELERAR, REVISA L'ESTAT DEL MOTOR");
                        }
                        break;

                    case 5:
                        // Canvia l'alçada
                        int alcada;
                        try {
                            System.out.println("Introdueix l'alçada de l'avió:");
                            alcada = teclat.nextInt();
                        }
                        catch (Exception e) {

                            System.out.println(Colors.RED + "NO S'HA POGUT CANVIAR L'ALÇADA");
                            System.out.println("MOTIU: " + e + Colors.RESET);
                            atura();
                            break;

                        }
                        if (!avions.get(avioSeleccionat).canviarAlcada(alcada)) {
                            mostrarError("NO S'HA POGUT CANVIAR L'ALÇADA DE L'AVIÓ, REVISA L'ESTAT DE L'AVIÓ");
                        }

                        break;

                    case 6:
                        // Puja/baixa el tren aterratge
                        if (!avions.get(avioSeleccionat).canviarEstatTrenAterratge()) {
                            mostrarError("NO S'HA POGUT CANVIAR L'ESTAT DEL TREN D'ATERRATGE, REVISA LA VELOCITAT I LA POSICIÓ DE L'AVIÓ");
                        }
                        break;

                    case 7:
                        // Canviar posició avió
                        int eixY;
                        int eixX;

                        try {
                            System.out.println("Introdueix la coordenada X:");
                            eixX = teclat.nextInt();
                            System.out.println("Introdueix la coordenada Y:");
                            eixY = teclat.nextInt();
                        }
                        catch (Exception e) {

                            System.out.println(Colors.RED + "NO S'HAN POGUT CANVIAR LES COORDENADES");
                            System.out.println("MOTIU: " + e + Colors.RESET);
                            atura();
                            break;

                        }

                        if (!avions.get(avioSeleccionat).canviarCoordenades(eixX, eixY)){
                            mostrarError("NO S'HAN POGUT CANVIAR LES COORDENADES, REVISA L'ESTAT DE L'AVIÓ");
                        }

                        break;

                    case 8:
                        // Canviar rumb avió
                        int rumb;

                        try {

                            System.out.println("Introdueix el rumb de l'avió:");
                            rumb = teclat.nextInt();

                        }
                        catch (Exception e) {
                            System.out.println(Colors.RED + "NO S'HA POGUT CANVIAR EL RUMB");
                            System.out.println("MOTIU: " + e + Colors.RESET);
                            atura();
                            break;
                        }

                        if (!avions.get(avioSeleccionat).canviarRumb(rumb)) {
                            mostrarError("NO S'HA POGUT CANVIAR EL RUMB DE L'AVIÓ");
                        }

                        break;

                    case 9:
                        // Aterrar avió
                        if (!avions.get(avioSeleccionat).aterra()) {
                            noPotAterrar(avioSeleccionat);
                            seleccioUsuari = 10;
                        }
                        break;

                    case 10:
                        // Sortir
                        break;

                    default:
                        mostrarError("INTRODUEIX UNA OPVIÓ VÀLIDA!");
                        break;

                }

            }
            while (seleccioUsuari != 10);

        }
        else {

            // En cas que l'avió de combat estigui encriptat mostrarà un error. Sinó, deixarà treballar amb ell
            if (avions.get(avioSeleccionat).isEncriptat()) {

                mostrarError("L'AVIÓ AMB MATRÍCULA " + avions.get(avioSeleccionat).getMatricula() + " ES TROBA ENCRIPTAT\n" +
                        "PER OPERAR AMB ELL PRIMER S'HA DE DESENCRIPTAR");

            }
            else {

                do {

                    netejarPantalla();
                    menuGestionarAvioCombat(avions.get(avioSeleccionat).getMatricula());
                    resetTeclat();
                    try {

                        seleccioUsuari = teclat.nextInt();

                    } catch (Exception e) {

                        seleccioUsuari = 0;

                    }

                    resetTeclat();
                    switch (seleccioUsuari) {

                        case 1:
                            // Encén motor
                            avions.get(avioSeleccionat).encendreMotor();
                            break;

                        case 2:
                            // Atura motor
                            avions.get(avioSeleccionat).apagarMotor();
                            break;

                        case 3:
                            // Accelera l'avió
                            int velocitat;
                            try {

                                System.out.println("Introdueix la velocitat de l'avió:");
                                velocitat = teclat.nextInt();

                            } catch (Exception e) {

                                System.out.println(Colors.RED + "NO S'HA POGUT CANVIAR LA VELOCITAT");
                                System.out.println("MOTIU: " + e + Colors.RESET);
                                atura();
                                break;

                            }

                            if (!avions.get(avioSeleccionat).accelerar(velocitat)) {
                                mostrarError("NO S'HA POGUT ACCELERAR, REVISA L'ESTAT DEL MOTOR");
                            }
                            break;

                        case 4:
                            // Frena l'avió
                            if (!avions.get(avioSeleccionat).frenar()) {
                                mostrarError("NO S'HA POGUT ACCELERAR, REVISA L'ESTAT DEL MOTOR");
                            }
                            break;

                        case 5:
                            // Canvia l'alçada
                            int alcada;
                            try {
                                System.out.println("Introdueix l'alçada de l'avió:");
                                alcada = teclat.nextInt();
                            } catch (Exception e) {

                                System.out.println(Colors.RED + "NO S'HA POGUT CANVIAR L'ALÇADA");
                                System.out.println("MOTIU: " + e + Colors.RESET);
                                atura();
                                break;

                            }
                            if (!avions.get(avioSeleccionat).canviarAlcada(alcada)) {
                                mostrarError("NO S'HA POGUT CANVIAR L'ALÇADA DE L'AVIÓ, REVISA L'ESTAT DE L'AVIÓ");
                            }

                            break;

                        case 6:
                            // Puja/baixa el tren aterratge
                            if (!avions.get(avioSeleccionat).canviarEstatTrenAterratge()) {
                                mostrarError("NO S'HA POGUT CANVIAR L'ESTAT DEL TREN D'ATERRATGE, REVISA LA VELOCITAT I LA POSICIÓ DE L'AVIÓ");
                            }
                            break;

                        case 7:
                            // Canviar posició avió
                            int eixY;
                            int eixX;

                            try {
                                System.out.println("Introdueix la coordenada X:");
                                eixX = teclat.nextInt();
                                System.out.println("Introdueix la coordenada Y:");
                                eixY = teclat.nextInt();
                            } catch (Exception e) {

                                System.out.println(Colors.RED + "NO S'HAN POGUT CANVIAR LES COORDENADES");
                                System.out.println("MOTIU: " + e + Colors.RESET);
                                atura();
                                break;

                            }

                            if (!avions.get(avioSeleccionat).canviarCoordenades(eixX, eixY)) {
                                mostrarError("NO S'HAN POGUT CANVIAR LES COORDENADES, REVISA L'ESTAT DE L'AVIÓ");
                            }

                            break;

                        case 8:
                            // Canviar rumb avió
                            int rumb;

                            try {

                                System.out.println("Introdueix el rumb de l'avió:");
                                rumb = teclat.nextInt();

                            } catch (Exception e) {
                                System.out.println(Colors.RED + "NO S'HA POGUT CANVIAR EL RUMB");
                                System.out.println("MOTIU: " + e + Colors.RESET);
                                atura();
                                break;
                            }

                            if (!avions.get(avioSeleccionat).canviarRumb(rumb)) {
                                mostrarError("NO S'HA POGUT CANVIAR EL RUMB DE L'AVIÓ");
                            }

                            break;

                        case 9:
                            // Aterrar avió
                            if (!avions.get(avioSeleccionat).aterra()) {
                                noPotAterrar(avioSeleccionat);
                                seleccioUsuari = 12;
                            }
                            break;

                        case 10:
                            // Afegir missils
                            int missils;

                            try {

                                System.out.println("Introdueix el número de missils a carregar en l'avió:");
                                missils = teclat.nextInt();

                            } catch (Exception e) {
                                System.out.println(Colors.RED + "NO S'HAN POGUT CARREGAR ELS MISSILS");
                                System.out.println("MOTIU: " + e + Colors.RESET);
                                atura();
                                break;
                            }

                            if (!avions.get(avioSeleccionat).afegirMissils(missils) || missils < 0) {
                                mostrarError("INTRODUEIX UN VALOR VÀLID");
                            }
                            break;

                        case 11:
                            // Disparar missil
                            dispararMissil(avioSeleccionat);
                            seleccioUsuari = 12;
                            break;

                        case 12:
                            // Sortir
                            break;

                        default:
                            mostrarError("INTRODUEIX UNA OPVIÓ VÀLIDA!");
                            break;

                    }

                }
                while (seleccioUsuari != 12);
            }

        }

    }

    /* .: 2.2.1. MENÚ GESTIONAR AVIO COMERCIAL :. */
    private void menuGestionarAvioComercial(String matricula) {

        System.out.println(Colors.PURPLE + "Avió: " + matricula + Colors.RESET);
        System.out.println("1. Encendre motor");
        System.out.println("2. Apagar motor");
        System.out.println("3. Accelerar");
        System.out.println("4. Frenar");
        System.out.println("5. Canviar alçada");
        System.out.println("6. Pujar/Baixar tren aterratge");
        System.out.println("7. Posicionar avió");
        System.out.println("8. Establir rumb");
        System.out.println("9. Aterrar avió");
        System.out.println("10. Sortir");

    }

    /* .: 2.2.2. MENÚ GESTIONAR AVIÓ DE COMBAT :. */
    private void menuGestionarAvioCombat(String matricula) {

        System.out.println(Colors.PURPLE + "Avió: " + matricula + Colors.RESET);
        System.out.println("1. Encendre motor");
        System.out.println("2. Apagar motor");
        System.out.println("3. Accelerar");
        System.out.println("4. Frenar");
        System.out.println("5. Canviar alçada");
        System.out.println("6. Pujar/Baixar tren aterratge");
        System.out.println("7. Posicionar avió");
        System.out.println("8. Establir rumb");
        System.out.println("9. Aterrar avió");
        System.out.println("10. Afegir missils");
        System.out.println("11. Disparar missil");
        System.out.println("12. Sortir");

    }

    /* .: 2.2.2.1 DISPARAR MISSIL :. */
    private void dispararMissil(int posicioAvio) throws InterruptedException {

        netejarPantalla();
        ArrayList<String> matriculesDisponibles =  menuDispararMissil(posicioAvio);

        // Comprova si hi ha missils disponibles i si hi ha algun avió a tir
        if (matriculesDisponibles.size() > 0 && avions.get(posicioAvio).getMissils() > 0) {

            resetTeclat();
            String matriculaPerDisparar = teclat.nextLine().toUpperCase();

            int posicioAvioPerDerribar = buscarPerMatricula(matriculaPerDisparar, matriculesDisponibles);

            // Es comprova si s'ha torbat alguna coincidencia amb la matrícula que ha introduit l'usuari
            if (posicioAvioPerDerribar >= 0 && avions.get(posicioAvio).dispararMissil()) {

                derribarAvio(matriculesDisponibles.get(posicioAvioPerDerribar));

            }
            else {
                mostrarError("NO ES POT DERRIBAR L'AVIÓ AMB MATRÍCULA " + matriculaPerDisparar);
            }

        }
        else {
            mostrarError("NO ES POT DERRIBAR CAP AVIÓ");
        }

    }

    // Menú disparar missil, a més, retorna un array amb els avions que es troben a tir
    private ArrayList<String> menuDispararMissil(int posicioAvio) throws InterruptedException {

        ArrayList<String> matriculesATir = avionsATir(posicioAvio);

        System.out.println(Colors.PURPLE + "Avió: " + avions.get(posicioAvio).getMatricula() + Colors.RESET);
        System.out.println("Missils disponibles: " + avions.get(posicioAvio).getMissils());

        if (matriculesATir.size() > 0) {

            System.out.println("Els avions a tir són:");

            for (String matriculaAvio : matriculesATir) {
                System.out.println("\t" + "- " + matriculaAvio);
            }

            System.out.println("Selecciona la matrícula de l'avió al que vols disparar:");
        }
        else
            System.out.println("No hi ha cap avió a tir");

        return matriculesATir;
    }

    // Comprova quins avions estan a tir i retorna un array amb les matricules
    private ArrayList<String> avionsATir(int posicioAvio) {
        int distanciaMaximaDeTir = avions.get(posicioAvio).getDistanciaMaxTir();
        int maxX = avions.get(posicioAvio).getCoordenadaX() + distanciaMaximaDeTir;
        int minX = avions.get(posicioAvio).getCoordenadaX() - distanciaMaximaDeTir;
        int maxY = avions.get(posicioAvio).getCoordenadaY() + distanciaMaximaDeTir;
        int minY = avions.get(posicioAvio).getCoordenadaY() - distanciaMaximaDeTir;
        int maxAlcada = avions.get(posicioAvio).getAlcada() + distanciaMaximaDeTir;
        int minAlcada = avions.get(posicioAvio).getAlcada() - distanciaMaximaDeTir;
        String bandol = avions.get(posicioAvio).getBandol();
        ArrayList<String> matriculesATir = new ArrayList<String>();

        for (avio avio : avions) {

            // S'extreu la informació necessaria per no haver d'estar fent consultes
            String bandolComprovar = avio.getBandol();
            int x = avio.getCoordenadaX();
            int y = avio.getCoordenadaY();
            int alcada = avio.getAlcada();

            // Comprova que el bàndol sigui diferent, que les coordenades X i Y i alçada estiguin dins del rang de tir
            if (!(bandolComprovar.equals(bandol))) {
                if (x >= minX && x <= maxX) {
                    if (y >= minY && y <= maxY) {
                        if (alcada >= minAlcada && alcada <= maxAlcada) {

                            matriculesATir.add(avio.getMatricula());

                        }
                    }
                }

            }
        }

        return matriculesATir;
    }


    /* .: 3. MOSTRAR INFORMACIÓ CONTROL AERI :. */
    private void mostrarInformacio() throws InterruptedException {

        // Revisa que hi hagi algun avió inicialitzat. Sino, mostra un error
        if (avions.size() > 0) {

            netejarPantalla();
            String[][] informacio = muntarTaula();
            mostrarTaula(informacio);
            System.out.println();
            mostrarAlertes();

            System.out.println("Prem un botó per sortir...");
            try {
                resetTeclat();
                teclat.nextLine();
            }
            catch (Exception e) {

            }

        }
        else {
            mostrarError("NO HI HA CAP AVIÓ INICIALITZAT!");
        }
    }

    /* .: 3.1. PREPARAR I MOSTAR TAULA AMB INFORMACIÓ DELS AVIONS :. */
    // Prepara la taula amb la informació
    private String[][] muntarTaula() {

        String[][] taula = new String[14][avions.size() + 1];

        // Registra tots els camps de la primera columna de la taula
        for (int i = 0; i < taula.length; i++) {

            String textPerIntroduir = Colors.PURPLE;

            switch (i) {

                case 0:
                case 1:
                    textPerIntroduir = textPerIntroduir + "\t\t\t\t";
                    break;

                case 2:
                    textPerIntroduir = textPerIntroduir + "Marca\t\t\t";
                    break;

                case 3:
                    textPerIntroduir = textPerIntroduir + "Model\t\t\t";
                    break;

                case 4:
                    textPerIntroduir = textPerIntroduir + "Matrícula\t\t";
                    break;

                case 5:
                    textPerIntroduir = textPerIntroduir + "X\t\t\t\t";
                    break;

                case 6:
                    textPerIntroduir = textPerIntroduir + "Y\t\t\t\t";
                    break;

                case 7:
                    textPerIntroduir = textPerIntroduir + "Alçada\t\t\t";
                    break;

                case 8:
                    textPerIntroduir = textPerIntroduir + "Velocitat\t\t";
                    break;

                case 9:
                    textPerIntroduir = textPerIntroduir + "Tren aterratge\t";
                    break;

                case 10:
                    textPerIntroduir = textPerIntroduir + "Motor\t\t\t";
                    break;

                case 11:
                    textPerIntroduir = textPerIntroduir + "Missils\t\t";
                    break;

                case 12:
                    textPerIntroduir = textPerIntroduir + "Origen\t\t\t";
                    break;

                case 13:
                    textPerIntroduir = textPerIntroduir + "Destí\t\t\t";
                    break;

            }

            taula[i][0] = textPerIntroduir;

        }

        // Registra tots els camps de cada avió
        for (int fila = 0; fila < taula.length; fila++) {
            for (int columna = 1; columna < taula[fila].length; columna++) {
                String textPerIntroduir = "";

                switch (fila) {
                    case 0:
                        // Capçaleres de la taula
                        textPerIntroduir = "Aeronau " + columna;
                        textPerIntroduir = textPerIntroduir + "\t".repeat(numTabulacions(textPerIntroduir.length()));
                        textPerIntroduir = Colors.PURPLE + textPerIntroduir + Colors.RESET;
                        break;

                    case 1:
                        // Espaiat
                        textPerIntroduir = "-".repeat(13) + " ";
                        textPerIntroduir = Colors.BLUE + textPerIntroduir + Colors.RESET;
                        break;

                    case 2:
                        // Marca
                        textPerIntroduir = avions.get(columna - 1).getMarca();
                        textPerIntroduir = textPerIntroduir + "\t".repeat(numTabulacions(textPerIntroduir.length() + 1));
                        break;

                    case 3:
                        // Model
                        textPerIntroduir = avions.get(columna - 1).getModel();
                        textPerIntroduir = textPerIntroduir + "\t".repeat(numTabulacions(textPerIntroduir.length() + 1));
                        break;

                    case 4:
                        // Matrícula
                        textPerIntroduir = avions.get(columna - 1).getMatricula();
                        textPerIntroduir = textPerIntroduir + "\t".repeat(numTabulacions(textPerIntroduir.length() + 1));
                        break;

                    case 5:
                        // X
                        textPerIntroduir = String.valueOf(avions.get(columna - 1).getCoordenadaX());
                        textPerIntroduir = textPerIntroduir + "\t".repeat(numTabulacions(textPerIntroduir.length() + 1));
                        break;

                    case 6:
                        // Y
                        textPerIntroduir = String.valueOf(avions.get(columna - 1).getCoordenadaY());
                        textPerIntroduir = textPerIntroduir + "\t".repeat(numTabulacions(textPerIntroduir.length() + 1));
                        break;

                    case 7:
                        // Alçada
                        textPerIntroduir = String.valueOf(avions.get(columna - 1).getAlcada());
                        textPerIntroduir = textPerIntroduir + "\t".repeat(numTabulacions(textPerIntroduir.length() + 1));
                        break;

                    case 8:
                        // Velocitat
                        textPerIntroduir = String.valueOf(avions.get(columna - 1).getVelocitat());
                        textPerIntroduir = textPerIntroduir + "\t".repeat(numTabulacions(textPerIntroduir.length() + 1));
                        break;

                    case 9:
                        // Tren aterratge
                        textPerIntroduir = (avions.get(columna - 1).isTrenAterratge()) ? "ON" : "OFF";
                        textPerIntroduir = textPerIntroduir + "\t".repeat(numTabulacions(textPerIntroduir.length() + 1));
                        break;

                    case 10:
                        // Motor
                        textPerIntroduir = (avions.get(columna - 1).isMotor()) ? "ON" : "OFF";
                        textPerIntroduir = textPerIntroduir + "\t".repeat(numTabulacions(textPerIntroduir.length() + 1));
                        break;

                    case 11:
                        // Missils
                        if (avions.get(columna - 1) instanceof avioCombat) {
                            textPerIntroduir = String.valueOf(avions.get(columna - 1).getMissils());
                        }
                        textPerIntroduir = textPerIntroduir + "\t".repeat(numTabulacions(textPerIntroduir.length() + 1));
                        break;

                    case 12:
                        // Origen
                        if (avions.get(columna - 1) instanceof avioComercial) {
                            textPerIntroduir = avions.get(columna - 1).getOrigen();
                        }
                        textPerIntroduir = textPerIntroduir + "\t".repeat(numTabulacions(textPerIntroduir.length() + 1));
                        break;

                    case 13:
                        // Destí
                        if (avions.get(columna - 1) instanceof avioComercial) {
                            textPerIntroduir = avions.get(columna - 1).getDesti();
                        }
                        textPerIntroduir = textPerIntroduir + "\t".repeat(numTabulacions(textPerIntroduir.length() + 1));
                        break;
                }

                taula[fila][columna] = textPerIntroduir;
            }
        }
        return taula;
    }

    // Mostra la taula que se li pasi, separant els elements
    private void mostrarTaula(String[][] taula) {
        for (int fila = 0; fila < taula.length; fila++) {
            for (int columna = 0; columna < taula[fila].length; columna++) {
                System.out.print(" " + taula[fila][columna] + Colors.BLUE + "|" + Colors.RESET);
            }
            System.out.println();
        }
    }

    // Formula per calcular quantes tabulacions s'han de fer per que la casella amb la paraula introduida mesuri 16 espais
    private int numTabulacions(int midaParaula) {
        int numTabulacions = 0;

        if (midaParaula < 16) {

            double numero = 16 - midaParaula;
            numero = numero / 4;
            numTabulacions = (int) Math.round(numero);

        }

        return numTabulacions;
    }

    /* .: 3.2. PREPARAR I MOSTRAR WARNINGS :. */
    private Object[] prepararAlertes() {
        ArrayList<String> alertes = new ArrayList<String>();

        for (int index = 0; index < avions.size(); index++) {

            int maxCoordenadaX = avions.get(index).getCoordenadaX() + 50;
            int minCoordenadaX = avions.get(index).getCoordenadaX() - 50;
            int maxCoordenadaY = avions.get(index).getCoordenadaY() + 50;
            int minCoordenadaY = avions.get(index).getCoordenadaY() - 50;
            int maxAlcada = avions.get(index).getAlcada() + 500;
            int minAlcada = avions.get(index).getAlcada() - 500;

            for (int subIndex = index + 1; subIndex < avions.size(); subIndex++) {
                boolean perillX = false;
                boolean perillY = false;
                boolean perillAlcada = false;
                boolean perill = false;

                if (avions.get(subIndex).getCoordenadaX() >= minCoordenadaX && avions.get(subIndex).getCoordenadaX() <= maxCoordenadaX) {
                    perill = true;
                    perillX= true;
                }
                if (avions.get(subIndex).getCoordenadaY() >= minCoordenadaY && avions.get(subIndex).getCoordenadaY() <= maxCoordenadaY) {
                    perill = true;
                    perillY= true;
                }
                if (avions.get(subIndex).getAlcada() >= minAlcada && avions.get(subIndex).getAlcada() <= maxAlcada) {
                    perill = true;
                    perillAlcada= true;
                }

                if (perill) {
                    String alerta = "Perill de col·lisió entre els avions " + avions.get(index).getMatricula() + " i " + avions.get(subIndex).getMatricula() + "\n" +
                            "\t";

                    if (perillX) {
                        alerta = alerta + "Coordenades X molt properes.\n\t";
                    }
                    if (perillY) {
                        alerta = alerta + "Coordenades Y molt properes.\n\t";
                    }
                    if (perillAlcada) {
                        alerta = alerta + "Alçades molt properes.\n\t";
                    }

                    alertes.add(alerta + "\n");
                }
            }
        }

        return alertes.toArray();
    }

    private void mostrarAlertes() throws InterruptedException {
        Object[] alertesObjecte = prepararAlertes();

        if (alertesObjecte.length > 0) {
            for (Object alerta : alertesObjecte) {
                System.out.println(Colors.RED +  String.valueOf(alerta) + Colors.RESET);
            }
        }
        else {
            System.out.println(Colors.GREEN + "NO HI HA PERILLS" + Colors.RESET);
        }
        atura();
    }


    /* .: 4. XIFRAR AVIONS DE COMBAT BÀNDOL AMIC :. */
    private void menuXifrarAvions() throws InterruptedException {

         netejarPantalla();
         resetTeclat();

         String bandol;
         String contrasenya;

         // L'usuari introdueix un bàndol. Si hi ha cap problema se'n va
         try {

             System.out.println("Introdueix el bàndol que vols xifrar:");
             bandol = teclat.nextLine().toUpperCase();

         }
         catch (Exception e) {

             System.out.println(Colors.RED + "NO S'HAN POGUT INTRODUIR EL BÀNDOL");
             System.out.println("MOTIU: " + e + Colors.RESET);
             atura();
             return;

         }
        resetTeclat();
        // L'usuari introdueix una contrasenya. Si hi ha cap problema se'n va
         try {

             System.out.println("Introdueix la contrasenya per desxifrar:");
             contrasenya = teclat.nextLine();

         }
         catch (Exception e) {

             System.out.println(Colors.RED + "NO S'HAN POGUT INTRODUIR LA CONTRASENYA");
             System.out.println("MOTIU: " + e + Colors.RESET);
             atura();
             return;

         }

         // Es fa el hash de la contrasenya
         int hashContrasenya = contrasenya.hashCode();

         int avionsXifrats = xifrarAvions(hashContrasenya, bandol);

         if (avionsXifrats > 0) {
             System.out.println("S'han xifrat " + avionsXifrats + " avions.");
             atura();
         }
         else {
             mostrarError("NO S'HA XIFRAT CAP AVIÓ");
         }

    }

    private int xifrarAvions(int contrasenyaHashejada, String bandol) {

        int avionsXifrats = 0;

        // Recòrre els avions, compara el bàndol, si és correcte, executa el mètode de xifrat. Si s'executa correctament, incrementa 1
        for (avio avio : avions) {
            if (avio.getBandol().equals(bandol)) {
                if (avio.xifrar(contrasenyaHashejada)) {
                    avionsXifrats++;
                }

            }
        }

        return avionsXifrats;
    }


    /* .: 5. DESXIFRAR AVIONS DE COMBAT BÀNDOL AMIC :. */
    private void menuDesxifrarAvions() throws InterruptedException {

        netejarPantalla();
        resetTeclat();

        String bandol;
        String contrasenya;

        // L'usuari introdueix un bàndol. Si hi ha cap problema se'n va
        try {

            System.out.println("Introdueix el bàndol que vols desxifrar:");
            bandol = teclat.nextLine().toUpperCase();

        }
        catch (Exception e) {

            System.out.println(Colors.RED + "NO S'HAN POGUT INTRODUIR EL BÀNDOL");
            System.out.println("MOTIU: " + e + Colors.RESET);
            atura();
            return;

        }

        // L'usuari introdueix la contrasenya de xifrat
        try {

            System.out.println("Introdueix la contrasenya per desxifrar:");
            contrasenya = teclat.nextLine();

        }
        catch (Exception e) {

            System.out.println(Colors.RED + "NO S'HAN POGUT INTRODUIR LA CONTRASENYA");
            System.out.println("MOTIU: " + e + Colors.RESET);
            atura();
            return;

        }

        // Es fa el hash de la contrasenya
        int hashContrasenya = contrasenya.hashCode();

        int avionsDesxifrats = desxifrarAvions(hashContrasenya, bandol);

        if (avionsDesxifrats > 0) {
            System.out.println("S'han desxifrat " + avionsDesxifrats + " avions.");
            atura();
        }
        else {
            mostrarError("NO S'HA DESXIFRAT CAP AVIÓ");
        }

    }

    private int desxifrarAvions(int contrasenyaHashejada, String bandol) {

        int avionsDesxifrats = 0;

        // Recòrre els avions, compara el bàndol, si és correcte, executa el mètode de desxifrat. Si s'executa correctament, incrementa 1
        for (avio avio : avions) {
            if (avio.getBandol().equals(bandol)) {
                if (avio.desxifrar(contrasenyaHashejada)) {
                    avionsDesxifrats++;
                }

            }
        }

        return avionsDesxifrats;
    }


    /* .: FUNCIONS PRÒPIES DEL CONTROL AERI */
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

    // Sobreescriptura del mètode anterior. Busca la posicio en l'array especificat de l'avió amb la matricula desitjada. Retorna -1 si no ha trobat res
    public int buscarPerMatricula(String matriculaPerBuscar, ArrayList<String> llistatMatricules) {
        int posicio = -1;

        for (int index = 0; index < llistatMatricules.size(); index++) {
            if (llistatMatricules.get(index).equals(matriculaPerBuscar)) {
                posicio = index;
                break;
            }
        }

        return posicio;
    }

    // Estimba l'avió que no ha pogut aterrar
    private void noPotAterrar(int posicioAvio) throws InterruptedException {
        System.out.println(Colors.RED + "L'AVIÓ AMB MATRÍCULA " + avions.get(posicioAvio).getMatricula() + " NO HA POGUT ATERRAR I S'HA ESTIMBAT" + Colors.RESET);
        avions.remove(posicioAvio);
        atura();
    }

    // Revisa les posicions dels avions creats i col·lisiona els que es troben en les mateixes coordenades
    private void colisionarAvions() throws InterruptedException {

        // S'emmagatzemaran els avions que s'han d'estimbar
        ArrayList<avio> avionsPerEstimbar = new ArrayList<avio>();

        // Es comproba la posició de cada avió, per veure si hi ha alguna repetida
        for (int index = 0; index < avions.size(); index++) {
            int eixXAvioComparat = avions.get(index).getCoordenadaX();
            int eixYAvioComparat = avions.get(index).getCoordenadaY();
            int alcadaAvioComparat = avions.get(index).getAlcada();

            for (int subIndex = 0; subIndex < avions.size(); subIndex++) {
                if (eixXAvioComparat == avions.get(subIndex).getCoordenadaX() && eixYAvioComparat == avions.get(subIndex).getCoordenadaY() && alcadaAvioComparat == avions.get(subIndex).getAlcada() && index != subIndex) {
                    avionsPerEstimbar.add(avions.get(index));
                    break;
                }
            }
        }

        // En cas que en l'array d'avions per estimbar hi hagi alguna cosa, es mostrarà el missatge pertinent
        if (avionsPerEstimbar.size() > 0) {

            System.out.print(Colors.RED + "Els avions ");

            for (avio avioEstimbat : avionsPerEstimbar) {
                int index = avions.indexOf(avioEstimbat);
                System.out.print(avions.get(index).getMatricula() + " ");
                avions.remove(index);
            }

            System.out.print("HAN COL·LISIONAT" + Colors.RESET);
            atura();

        }
    }

    // Derriba l'avió amb la matrícula introduida
    private void derribarAvio(String matricula) throws InterruptedException {
        int posicioAvio = buscarPerMatricula(matricula);

        System.out.println("S'ha derribat l'avió amb matrícula: " + avions.get(posicioAvio).getMatricula());
        avions.remove(posicioAvio);
        atura();
    }

    // Revisa les posicions de tots els avions que s'estan gestionant, i n'elimina els que es troben fora de les constants definides al inici del codi
    private void eliminarAvionsForaDeRang() throws InterruptedException {
        boolean eliminat = false;

        for (avio avio : avions) {
            // Es recuperen les dades per no haver d'estar fent consultes
            int posicioX = avio.getCoordenadaX();
            int posicioY = avio.getCoordenadaY();
            int alcada = avio.getAlcada();

            if ((posicioX < 0 || posicioX > RANGESPAIAERI_X) ||
                    (posicioY < 0 || posicioY > RANGESPAIAERI_Y) ||
                    (alcada > RANGESPAIAERI_ALCADA)) {
                System.out.println(Colors.RED + "L'avió amb matrícula " + avio.getMatricula() + " es troba fora del rang de l'espai aeri." + Colors.RESET);
                eliminat = true;
                avions.remove(avio);
            }
        }

        if (eliminat)
            atura();
    }

    // Estimba els avions que es troben a una alçada negativa
    private void estimbarAvionsPocaAlcada() throws InterruptedException {
        boolean estimbat = false;

        for (avio avio: avions) {
            if (avio.getAlcada() < 0) {
                System.out.println(Colors.RED + "L'avió amb matrícula " + avio.getMatricula() + " s'ha estimbat contra el terra." + Colors.RESET);
                estimbat = true;
                avions.remove(avio);
            }
        }

        if (estimbat)
            atura();
    }

    // Executa tots els mètodes per tal d'actualitzar l'estat del control aeri i mostrar els missatges pertinents per informar a l'usuari
    public void actualitzarEstatControladorAeri() throws InterruptedException {

        eliminarAvionsForaDeRang();
        estimbarAvionsPocaAlcada();
        colisionarAvions();

    }


    /* .: ALTRES FUNCIONS ADDICIONALS */
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

    public void mostrarError(String error) throws InterruptedException {
        System.out.println(Colors.RED + error + Colors.RESET);
        atura();
    }

}
