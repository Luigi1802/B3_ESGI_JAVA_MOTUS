package fr.esgi.service;

import fr.esgi.business.Mot;
import fr.esgi.service.impl.MotServiceImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MotServiceTest {
    @Test
    void testInitialisationVariableGlobales(){
        MotService motService = new MotServiceImpl();
        assertNotNull(motService.getMotATrouver());
        assertNotNull(motService.getMotIntermediaire());
        assertNotNull(motService.getMotSaisi());
        Mot motIntermediaire = new Mot();
        Mot motSaisi = new Mot();
        Mot motATrouver = new Mot();
        motService.setMotIntermediaire(motIntermediaire);
        motService.setMotSaisi(motSaisi);
        motService.setMotATrouver(motATrouver);
        assertEquals(motIntermediaire.getId(), motService.getMotIntermediaire().getId());
        assertEquals(motSaisi.getId(), motService.getMotSaisi().getId());
        assertEquals(motATrouver.getId(), motService.getMotATrouver().getId());
    }

    @Test
    void testStringEnMotEnString(){
        MotService motService = new MotServiceImpl();
        String motEnString = "TestMot";
        Mot mot1 = motService.retournerStringEnMot(motEnString);
        Mot mot2 = motService.retournerStringEnMot(motEnString);
        System.out.println(mot1.retournerMotEnString());
        assertEquals(motEnString, mot1.retournerMotEnString());
        assertEquals(mot1.getLettres(), mot2.getLettres());
    }

    @Test
    void testComparateurMotsSaisiATrouve(){
        MotService motService = new MotServiceImpl();

        String motTestSucces = "Testmot";
        String motTestSansSucces1 = "Txxxoxx";
        String motTestSansSucces2 = "Tyzexxo";
        String motTestSansSucces3 = "Tezexxx";
        int longueurMotIntermediaireTemp1 = 3;
        int longueurMotIntermediaireTemp2 = 6;
        int longueurMotIntermediaireFinal = 10;

        Mot motATrouver = motService.retournerStringEnMot(motTestSucces);
        Mot motSaisiSansSucces1 = motService.retournerStringEnMot(motTestSansSucces1);
        Mot motSaisiSansSucces2 = motService.retournerStringEnMot(motTestSansSucces2);
        Mot motSaisiSansSucces3 = motService.retournerStringEnMot(motTestSansSucces3);
        Mot motSaisiSucces = motService.retournerStringEnMot(motTestSucces);

        motService.setMotSaisi(motSaisiSansSucces1);
        motService.setMotATrouver(motATrouver);
        motService.comparateurMotsSaisiATrouver();
        assertFalse(motService.testerValiditeMotSaisi());
        assertNotEquals(motService.getMotIntermediaire().retournerMotEnString(), motTestSucces);
        assertEquals(longueurMotIntermediaireTemp1, motService.getMotIntermediaire().getLettres().size());

        motService.setMotSaisi(motSaisiSansSucces2);
        motService.comparateurMotsSaisiATrouver();
        assertEquals(longueurMotIntermediaireTemp2, motService.getMotIntermediaire().getLettres().size());
        assertFalse(motService.testerValiditeMotSaisi());

        motService.setMotSaisi(motSaisiSansSucces3);
        motService.comparateurMotsSaisiATrouver();
        assertEquals(longueurMotIntermediaireTemp2, motService.getMotIntermediaire().getLettres().size());
        assertFalse(motService.testerValiditeMotSaisi());

        motService.setMotSaisi(motSaisiSucces);
        motService.comparateurMotsSaisiATrouver();
        assertEquals(motService.getMotSaisi().retournerMotEnString(), motTestSucces);
        assertEquals(longueurMotIntermediaireFinal, motService.getMotIntermediaire().getLettres().size());
        assertTrue(motService.testerValiditeMotSaisi());


    }
}

