package net.djouda;


import java.math.BigDecimal;

public class CompteBancaire {
    private BigDecimal solde;
    private final String titulaire;

    public CompteBancaire(String titulaire, BigDecimal soldeInitial) {

        if(titulaire == null || titulaire.isEmpty()) {
            throw new IllegalArgumentException("Le titulaire du compte ne peut pas être vide.");
        }
        if(soldeInitial == null || soldeInitial.signum() < 0) {
            throw new IllegalArgumentException("Le solde initial ne peut pas être négatif.");
        }
        this.titulaire = titulaire;
        this.solde = soldeInitial;
    }

    public void debiter(BigDecimal montant) {
        exigeMontantPositif(montant);
        if(solde.compareTo(montant) < 0) {
            throw new IllegalArgumentException("Solde insuffisant pour débiter le montant demandé.");
        }
        this.solde = this.solde.subtract(montant);

    }

    public void crediter(BigDecimal montant) {
        exigeMontantPositif(montant);
        this.solde = this.solde.add(montant);
    }

    private static void exigeMontantPositif(BigDecimal montant) {
        if (montant == null || montant.signum() <= 0) {
            throw new IllegalArgumentException("Le montant doit être positif.");
        }
    }

    public String getTitulaire() {
        return titulaire;
    }

    public BigDecimal getSolde() {
        return solde;
    }

    public void transfert(String beneficiaire, BigDecimal montant) {
        exigeMontantPositif(montant);
        if(beneficiaire.isEmpty()) {
            throw new IllegalArgumentException("Le bénéficiaire ne peut pas être vide.");
        }
        debiter(montant);
        System.out.println("Transfert de " + montant + " vers " + beneficiaire + " effectué avec succès.");
    }

}
