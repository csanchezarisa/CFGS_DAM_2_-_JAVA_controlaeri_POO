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
                    stop();

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
        System.out.println(Colors.BLUE + "- " + Colors.RESET + "3. Mostrar espai" + Colors.BLUE + "           -" + Colors.RESET);
        System.out.println(Colors.BLUE + "- " + Colors.RESET + "4. Xifrar avions" + Colors.BLUE + "           -" + Colors.RESET);
        System.out.println(Colors.BLUE + "- " + Colors.RESET + "5. Desxifrar avions" + Colors.BLUE + "        -" + Colors.RESET);
        System.out.println(Colors.BLUE + "- " + Colors.RESET + "6. Sortir" + Colors.BLUE + "                  -");
        System.out.println(Colors.BLUE + "------------------------------" + Colors.RESET);

    }


    /* .: 1.2. MENU AFEGIR AVIO :. */
    // Comprova si hi ha espai per crear-ne mes avions i permet crear-ne de nous
    private void afegirAvio() throws InterruptedException {

        if (avions.size() < 10) {

            char seleccioUsuari = '0';

            do {

                netejarPantalla();
                mostrarMenuAfegirAvioSeleccioTipus();
                seleccioUsuari = teclat.nextLine().charAt(0);

                switch (seleccioUsuari) {

                    case '1':
                        break;

                    case '2':
                        break;

                    case '3':
                        break;

                    default:
                        System.out.println(Colors.RED + "INTRODUEIX UNA OPCIO VALIDA" + Colors.RESET);
                        stop();

                }

            }
            while (seleccioUsuari != '3');

        }
        else {

            System.out.println(Colors.RED + "JA S'HAN AFEGIT EL MAXIM NUMERO D'AVIONS" + Colors.RESET);
            stop();

        }

    }

    // Mostra el menu de seleccio del tipus d'avio
    private void mostrarMenuAfegirAvioSeleccioTipus() {

        System.out.println(Colors.BLUE + "------------------------------" + Colors.RESET);
        System.out.println(Colors.BLUE + "---- " + Colors.PURPLE + "SELECCIONA EL TIPUS" + Colors.BLUE + " -----" + Colors.RESET);
        System.out.println(Colors.BLUE + "------------------------------" + Colors.RESET);
        System.out.println(Colors.BLUE + "- " + Colors.RESET + "1. Comercial" + Colors.BLUE + "               -" + Colors.RESET);
        System.out.println(Colors.BLUE + "- " + Colors.RESET + "2. Combat" + Colors.BLUE + "                  -" + Colors.RESET);
        System.out.println(Colors.BLUE + "- " + Colors.RESET + "3. Sortir" + Colors.BLUE + "                  -" + Colors.RESET);
        System.out.println(Colors.BLUE + "------------------------------" + Colors.RESET);


    }


    // Neteja la consola
    public void netejarPantalla() {

        for (int i = 0; i < 50; i++) {
            System.out.println();
        }

    }

    // Pausa el programa
    public void stop() throws InterruptedException {

        Thread.sleep(3000);

    }

}
