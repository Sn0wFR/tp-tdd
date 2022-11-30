package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.model.DrivingLicence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DrivingLicenceFinderServiceTest {

    @InjectMocks
    private DrivingLicenceFinderService service;

    @Mock
    private InMemoryDatabase database;

    @Test
    void should_find() {
        UUID uuid = UUID.randomUUID();

        when(database.findById(uuid)).thenReturn(Optional.of(DrivingLicence.builder().id(uuid).build()));

        Optional<DrivingLicence> drivingLicence = service.findById(uuid);

        Assertions.assertTrue(drivingLicence.isPresent());
    }

    @Test
    void should_not_find() {
        UUID uuid = UUID.randomUUID();

        when(database.findById(uuid)).thenReturn(Optional.empty());

        Optional<DrivingLicence> drivingLicence = service.findById(uuid);

        Assertions.assertFalse(drivingLicence.isPresent());
    }
}