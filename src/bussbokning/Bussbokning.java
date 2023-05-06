/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bussbokning;

/**
 *
 * @author usukhireedui.batkhu
 */
import java.util.Scanner;
public class Bussbokning {
   
    public static void main(String[] args) {
        int kontroll = 0;
        int[] buss = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        String[] namnFör = {"a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
        String[] namnEfter = {"a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
        while (kontroll == 0){
            Scanner scan = new Scanner(System.in);
            System.out.println("\nMeny");
            System.out.println("1. Lägg till bokning");
            System.out.println("2. Visa innehåll");
            System.out.println("3. Beräkna vinst sålda biljetter");
            System.out.println("4. Avsluta");
            System.out.println("5. Hitta bokning");
            System.out.println("6. Ta bort bokning");
            System.out.print("\nInput: ");
            int felKontroll = 0;
            int meny = scan.nextInt();
                if (meny == 1){
                    System.out.print("Ange förnamn: ");
                    String förnamn = scan.next();
                    System.out.print("Ange efternamn: ");
                    String efternamn = scan.next();
                    System.out.println("[aaaammdd]");
                    System.out.print("Ange personnummer: ");
                    int personNummer = scan.nextInt();
                    System.out.print("Vilken plats vill du boka?(Välj mellan 1-21.): ");
                    int stolNummer = scan.nextInt();
                    stolNummer--;
                    bokaStol(buss, personNummer, stolNummer, förnamn, efternamn, namnFör, namnEfter);
                    felKontroll++;
            }
            if (meny == 2){
                int antalStolar = antalStol(buss);
                lista(buss);
                System.out.println("Det finns "+antalStolar+" stolar kvar.");
                felKontroll++;
            }
            if (meny == 3){
                double vinst = hittaVinst(buss);
                int vinstUngefär = (int)vinst+1;
                felKontroll++;
                System.out.println("Vinsten är "+vinst+" kr. Ungefär "+vinstUngefär+" kr.");
            }
            if (meny == 4){
                System.out.println("Tack och hej!");
                felKontroll++;
                kontroll++;
            }
            if (meny == 5){
                System.out.println("Vill du söka bokning genom personnummer eller namn?(Välj 1 för personnummer eller 2 för namn.): ");
                int val = scan.nextInt();
                if (val == 1){
                    System.out.print("Ange personnummer: ");
                    int input = scan.nextInt();
                    hittaBokning1(buss, input);
                }
                if (val == 2){
                    System.out.println("Ange namn: ");
                    String input = scan.next();
                    hittaBokning2(namnFör, namnEfter, input);
                }
                felKontroll++;
            }
            if (meny == 6){
                System.out.println("Vill du ta bort bokning genom personnummer eller namn?(Välj 1 för personnummer eller 2 för namn.): ");
                int val = scan.nextInt();
                if (val == 1){
                    System.out.print("Ange personnummer: ");
                    int input = scan.nextInt();
                    taBortBokning1(buss, input, namnFör, namnEfter);
                }
                if (val == 2){
                    System.out.println("Ange namn: ");
                    String input = scan.next();
                    taBortBokning2(buss, input, namnFör, namnEfter);
                }
                felKontroll++;
            }
            if (felKontroll == 0){
                System.out.println("Fel input. Försök igen.");
            }  
        }
    }
   
    static void bokaStol(int[] buss, int personNummer, int stolNummer, String förnamn, String efternamn, String[] namnFör, String[] namnEfter){
        if (stolNummer < 21){
            int nr = 0;
            for (int i = 0;i<buss.length;i++){
                if (i == stolNummer){
                    if (buss[i] == 0){
                        buss[i] = personNummer;
                        namnFör[i] = förnamn;
                        namnEfter[i] = efternamn;
                        nr= nr+i+1;
                        System.out.println("Varsågod, du har bokat stol nr. "+nr+".");
                    }
                    else {
                        System.out.println("Stolen är tagen, försök igen.");
                    }
                }
            }
        }
    }
   
    static int antalStol(int[] buss){
        int antalStolar = 0;
        for (int i = 0; i<buss.length;i++) {
            if (buss[i] == 0) {
                antalStolar++;
            }
        }
        return antalStolar;
    }
   
    static void lista(int[] buss){
        int[] lista = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        for (int i = 0; i<buss.length;i++){
            if (buss[i] > 0){
                int ålder = 20230400 - buss[i];
                if (ålder < 180000){
                    lista[i] = 1;
                }
                else if (ålder > 180000){
                    lista[i] = 2;
                }
            }
        }
        int plats = 0;
        for (int i = 0; i<lista.length;i++){
            if (lista[i] == 1){
                plats= plats+i+1;
                System.out.println("Plats "+plats+" Barn");
            }
            if (lista[i] == 2){
                plats= plats+i+1;
                System.out.println("Plats "+plats+" Vuxen");
            }
        }    
    }
   
    static double hittaVinst(int[] buss){
        int antalPensionär = 0;
        int antalVuxna = 0;
        int antalBarn = 0;
        for (int i = 0;i<buss.length;i++){
            if (buss[i] > 0) {
                int ålder = 20230400 - buss[i];
                if (ålder < 180000){
                    antalBarn++;
                }
                if (ålder > 180000 && ålder < 690000){
                    antalVuxna++;
                }
                if (ålder < 690000){
                    antalPensionär++;
                }
            }
        }
        double vinstBarn = antalBarn * 149.90;
        double vinstVuxna = antalVuxna * 299.90;
        double vinstPensionär = antalPensionär * 199.90;
        double vinst = (vinstVuxna+ vinstBarn + vinstPensionär);
        return vinst;
    }
   
    static void hittaBokning1(int[] buss, int personNummer){
        int bokningKontroll = 0;
        for (int i = 0; i<buss.length;i++){
            if (buss[i] == personNummer){
                i++;
                bokningKontroll++;
                System.out.println("Ja, det finns en bokning vid stol "+i+".");
            }
        }
        if (bokningKontroll == 0){
                System.out.println("Det finns ingen bokning av det personnumret.");
        }
    }
       
    static void hittaBokning2(String[] namnFör, String[] namnEfter, String namn){
        int bokningKontroll = 0;
        int nr = 0;
        for (int i = 0;i<namnFör.length;i++){
            if (namnFör[i].equals(namn)){
                bokningKontroll++;
                nr= nr+i+1;
                System.out.println("Ja, bokningen av "+namnFör[i]+" finns vid stol "+nr+".");
            }
            if (namnEfter[i].equals(namn)){
                bokningKontroll++;
                nr= nr+i+1;
                System.out.println("Ja, bokningen av "+namnEfter[i]+" finns vid stol "+nr+".");
            }
        }
        if (bokningKontroll == 0){
                System.out.println("Det finns ingen bokning av det namnet.");
            }
    }
   
    static void taBortBokning1(int[] buss, int personNummer, String[] namnFör, String[] namnEfter){
        int bokningKontroll = 0;
        for (int i = 0;i<buss.length;i++) {
            if (buss[i] == personNummer) {
                buss[i] = 0;
                namnFör[i] = "a";
                namnEfter[i] = "a";
                bokningKontroll++;
                System.out.println("Tog bort bokning: "+personNummer);
            }
        }
        if (bokningKontroll == 0){
                System.out.println("Det finns ingen bokning av det personnumret.");
            }
    }
   
    static void taBortBokning2(int[] buss, String namn, String[] namnFör, String[] namnEfter){
        int bokningKontroll = 0;
        for (int i = 0;i<namnFör.length;i++) {
            if (namnFör[i].equals(namn) || namnEfter[i].equals(namn)) {
                buss[i] = 0;
                namnFör[i] = "a";
                namnEfter[i] = "a";
                bokningKontroll++;
                System.out.println("Tog bort bokning: "+namn);
            }
        }
        if (bokningKontroll == 0){
                System.out.println("Det finns ingen bokning av det namnet.");
            }
    }
}