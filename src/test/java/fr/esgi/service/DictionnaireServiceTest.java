package fr.esgi.service;
import fr.esgi.service.impl.DictionnaireServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class DictionnaireServiceTest {
    @Test
    void testInitialisationGlobaleVariableTest(){
        DictionnaireService dictionnaireService = new DictionnaireServiceImpl();
        assertNotNull(dictionnaireService.recupererListeMots());
    }

    @Test
    void testTesterMotPresentDictionnaire(){
        DictionnaireService dictionnaireService = new DictionnaireServiceImpl();
        dictionnaireService.creerListeMots();
        String motInexistant = "aaaaaaa";
        String motExistant = "orange";
        assertFalse(dictionnaireService.recupererListeMots().isEmpty());
        assertFalse(dictionnaireService.testerMotPresentDictionnaire(motInexistant));
        assertTrue(dictionnaireService.testerMotPresentDictionnaire(motExistant));
    }
}
