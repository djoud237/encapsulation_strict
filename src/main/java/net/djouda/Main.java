package net.djouda;

import java.math.BigDecimal;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le nom du titulaire du compte : ");
        String titulaire = scanner.nextLine();

        CompteBancaire compte = null;
        while (compte == null) {
            try {
                System.out.print("Entrez le solde initial du compte : ");
                compte = new CompteBancaire(titulaire, lireMontant(scanner));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " Saisie invalide, recommencez.");
                scanner.nextLine();  //saut de ligne

            }
        }

        boolean continuer = true;
        while (continuer) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Déposer");
            System.out.println("2. Retirer");
            System.out.println("3. Afficher le solde");
            System.out.println("4. Effectuer un transfert");
            System.out.println("0. Quitter");
            System.out.print("Choix : ");

            String choix = scanner.nextLine().trim();

            try {
                switch (choix) {
                    case "1":
                        System.out.print("Entrez le montant à déposer : ");
                        compte.crediter(lireMontant(scanner));
                        System.out.println("Dépôt effectué. Nouveau solde : " + compte.getSolde());
                        break;
                    case "2":
                        System.out.print("Entrez le montant à retirer : ");
                        compte.debiter(lireMontant(scanner));
                        System.out.println("Retrait effectué. Nouveau solde : " + compte.getSolde());
                        break;
                    case "3":
                        System.out.println("Solde actuel : " + compte.getSolde());
                        break;
                    case "4":
                        System.out.print("Entrez le nom du bénéficiaire : ");
                        String beneficiaire = scanner.nextLine();
                        System.out.print("Entrez le montant à transférer : ");
                        compte.transfert(beneficiaire, lireMontant(scanner));
                        System.out.println("Transfert effectué. Nouveau solde : " + compte.getSolde());
                        break;
                    case "0":
                        continuer = false;
                        break;
                    default:
                        System.out.println("Choix invalide, veuillez réessayer.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " Saisie invalide, recommencez.");
            }
        }
        System.out.println("Au revoir!");
        scanner.close();

    }

    private static BigDecimal lireMontant (Scanner sc){
        String ligne = sc.nextLine().trim();
        if (ligne.isEmpty()) {
            throw new IllegalArgumentException("Aucun montant saisir");
        }
        try {
            return new BigDecimal(ligne);
        }catch (NumberFormatException e) {
            throw new IllegalArgumentException("<<" + ligne + ">> n'est pas un montant valide.");
        }
    }
}