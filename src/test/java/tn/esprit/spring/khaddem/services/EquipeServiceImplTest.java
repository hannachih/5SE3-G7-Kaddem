package tn.esprit.spring.khaddem.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.spring.khaddem.entities.*;
import tn.esprit.spring.khaddem.repositories.ContratRepository;
import tn.esprit.spring.khaddem.repositories.EquipeRepository;
import tn.esprit.spring.khaddem.repositories.EtudiantRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.*;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class EquipeServiceImplTest {
    @Mock
    private EtudiantRepository etudiantRepository;

    @Mock
    private EquipeRepository equipeRepository;

    @Mock
    private ContratRepository contratRepository;

    @InjectMocks
    private EquipeServiceImpl equipeService;

    @Test
    void retrieveAllEquipes() {
        // Arrange
        List<Equipe> mockEquipes = Arrays.asList(new Equipe(), new Equipe());
        when(equipeRepository.findAll()).thenReturn(mockEquipes);

        // Act
        List<Equipe> result = equipeService.retrieveAllEquipes();

        // Assert
        assertEquals(mockEquipes, result);
    }

    @Test
    void addEquipe() {
        // Arrange
        Etudiant etudiant1 = new Etudiant(/* set etudiant properties */);
        Etudiant etudiant2 = new Etudiant(/* set etudiant properties */);

        DetailEquipe detailEquipe = new DetailEquipe(/* set detailEquipe properties */);

        Equipe equipeToAdd = Equipe.builder()
                .idEquipe(5)
                .nomEquipe("TestEquipe")
                .niveau(Niveau.JUNIOR)
                .etudiants(Arrays.asList(etudiant1, etudiant2))
                .detailEquipe(detailEquipe)
                .build();

        when(equipeRepository.save(any(Equipe.class))).thenReturn(equipeToAdd);

        // Act
        Equipe addedEquipe = equipeService.addEquipe(equipeToAdd);

        // Assert
        verify(equipeRepository).save(any(Equipe.class));

        assertThat(addedEquipe).isNotNull();
        assertThat(addedEquipe.getIdEquipe()).isNotNull();
        assertEquals(addedEquipe.getIdEquipe(),5);
        assertThat(addedEquipe.getNomEquipe()).isEqualTo("TestEquipe");
        assertThat(addedEquipe.getNiveau()).isEqualTo(Niveau.JUNIOR);
        assertThat(addedEquipe.getEtudiants()).containsExactlyInAnyOrder(etudiant1, etudiant2);
        assertThat(addedEquipe.getDetailEquipe()).isEqualTo(detailEquipe);
    }

    @Test
    void updateEquipe() {
    }

    @Test
    void retrieveEquipe() {
        // Arrange
        int equipeId = 1;
        Equipe mockEquipe = new Equipe();
        when(equipeRepository.findById(equipeId)).thenReturn(Optional.of(mockEquipe));

        // Act
        Equipe result = equipeService.retrieveEquipe(equipeId);

        // Assert
        assertEquals(mockEquipe, result);
    }

    @Test
    void evoluerEquipes() {

    }

    private List<Contrat> createContrats(Etudiant etudiant, int count) {
        List<Contrat> contrats = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Contrat contrat = new Contrat();
            contrat.setEtudiant(etudiant);
            contrat.setDateDebutContrat(new Date());
            // Set other properties as needed
            contrats.add(contrat);
        }
        return contrats;
    }
}

