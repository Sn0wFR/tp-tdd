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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class DrivingLicenceGenerationServiceTest {

    @Mock
    InMemoryDatabase database;

    @InjectMocks
    DrivingLicenceGenerationService drivingLicenceGenerationService;

    @Test
    void should_generate_driving_licence() {
        UUID uuid = UUID.randomUUID();

        when(database.save(any(),any())).thenReturn(DrivingLicence.builder().id(uuid).driverSocialSecurityNumber("123456789012345").build());

        DrivingLicence drivingLicence = drivingLicenceGenerationService.generateNewDrivingLicence("123456789012345");

        assertThat(drivingLicence.getId())
                .isNotNull();
        assertThat(drivingLicence.getAvailablePoints())
                .isNotNull()
                .isEqualTo(12);
        assertThat(drivingLicence.getDriverSocialSecurityNumber())
                .isNotNull();

        when(database.findById(drivingLicence.getId())).thenReturn(Optional.of(drivingLicence));

        assertThat(database.findById(drivingLicence.getId()).isPresent())
                .isTrue();

        assertThat(drivingLicence).isInstanceOf(DrivingLicence.class);
    }
}
