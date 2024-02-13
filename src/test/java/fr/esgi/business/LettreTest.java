package fr.esgi.business;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LettreTest {
    @Test
    void testDefaultConstructor(){
        Lettre lettreVide = new Lettre();
        assertNotNull(lettreVide.getId(), "L'id de l'instance de Lettre initée à vide est Null.");
        assertNotNull(lettreVide.getStatut(), "Le statut de l'instance de Lettre initée à vide est Null.");
        assertTrue(lettreVide.getId()>0, "L'id de l'instance de Lettre initée à vide doit être supérieure à 0.");
        assertEquals(StatutLettre.DEFAUT, lettreVide.getStatut(), "Le statut de l'instance de Lettre initée à vide n'est pas par DEFAUT.");

        Lettre lettreInitialisee = new Lettre('c', 1, StatutLettre.DEFAUT, 2);
        assertNotNull(lettreVide.getStatut(), "Le statut de l'instance de Lettre initée est Null.");
        assertEquals(1, lettreInitialisee.getPosition(), "La position de l'instance de Lettre n'a pas la bonne valeur.");
        assertEquals(StatutLettre.DEFAUT, lettreInitialisee.getStatut(),"Le statut de l'instance de Lettre initée n'est pas par DEFAUT.");
        assertNotNull(lettreInitialisee.getStatut(), "Le statut de l'instance de Lettre initée avec information est Null.");
        assertNotNull(lettreInitialisee.getCaractere(), "Le charactère de l'instance de Lettre initée avec information est Null.");

    }
    @Test
    void testEqualsObject(){
        Lettre lettreInitialisee = new Lettre('c', 1, StatutLettre.VALIDE, 2);
        Lettre lettreInitialisee2 = new Lettre('c', 1, StatutLettre.VALIDE, 2);
        assertEquals(lettreInitialisee, lettreInitialisee2);
        assertTrue(lettreInitialisee.getId() < lettreInitialisee2.getId());
        assertEquals(lettreInitialisee.getCaractere(), lettreInitialisee2.getCaractere());
        assertEquals(lettreInitialisee.getPosition(), lettreInitialisee2.getPosition());
        assertEquals(lettreInitialisee.getStatut(), lettreInitialisee2.getStatut());

        lettreInitialisee.setStatutValide();
        lettreInitialisee2.setStatutValide();
        assertTrue(lettreInitialisee.estValide(lettreInitialisee2));

        lettreInitialisee.setStatutTrouve();
        lettreInitialisee2.setStatutTrouve();
        assertTrue(lettreInitialisee.estTrouve(lettreInitialisee2));

        lettreInitialisee.setStatutAbsent();
        lettreInitialisee2.setStatutAbsent();
        assertTrue(lettreInitialisee.estAbsent(lettreInitialisee2));
    }
    @Test
    void testerGettersSetters(){
        Lettre lettre = new Lettre();
        Character caractere = 'e';
        int position = 8;
        lettre.setCaractere(caractere);
        lettre.setPosition(position);

        lettre.setStatutValide();
        assertEquals(StatutLettre.VALIDE, lettre.getStatut());
        lettre.setStatutTrouve();
        assertEquals(StatutLettre.TROUVE, lettre.getStatut());
        lettre.setStatutAbsent();
        assertEquals(StatutLettre.ABSENTE, lettre.getStatut());
        lettre.setStatutDefaut();
        assertEquals(StatutLettre.DEFAUT, lettre.getStatut());

        assertNotNull(lettre.getCaractere());
        assertEquals(caractere, lettre.getCaractere());
        assertEquals(position, lettre.getPosition()); //Ne peut pas être nul

    }
    @Test
    void testHashCode(){
        Lettre lettre = new Lettre();
        assertTrue(lettre.hashCode()!=0);
    }

    @Test
    void testToString(){
        Lettre lettre = new Lettre();
        assertNotNull(lettre.toString());
        Lettre lettreInitialisee = new Lettre('c', 1, StatutLettre.VALIDE, 2);
        assertNotNull(lettreInitialisee.toString());
    }
}
