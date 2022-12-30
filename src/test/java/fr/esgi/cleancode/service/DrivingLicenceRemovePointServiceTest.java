package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import fr.esgi.cleancode.exception.ResourceNotFoundException;
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
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class DrivingLicenceRemovePointServiceTest {

    @InjectMocks
    private  DrivingLicenceRemovePointService drivingLicenceRemovePointService;

    @Mock
    private InMemoryDatabase database;

    @Mock
    DrivingLicenceGenerationService drivingLicenceGenerationService;

    @Test
    void should_remove_points() {

        UUID uuid = UUID.randomUUID();

        when(database.findById(uuid)).thenReturn(Optional.of(DrivingLicence.builder().id(uuid).driverSocialSecurityNumber("123456789012345").build()));

        when(database.save(uuid, DrivingLicence.builder().id(uuid).driverSocialSecurityNumber("123456789012345").availablePoints(7).build()))
                .thenReturn(DrivingLicence.builder().id(uuid).driverSocialSecurityNumber("123456789012345").availablePoints(7).build());;

        DrivingLicence drivingLicence =  drivingLicenceRemovePointService.removePoint(5, uuid);

        assertThat(drivingLicence.getAvailablePoints())
                .isNotNull()
                .isEqualTo(7);

        assertThat(drivingLicence).isInstanceOf(DrivingLicence.class);

    }

    @Test
    void should_throw_ResourceNotFoundException() {
        UUID uuid = UUID.randomUUID();

        Assertions.assertThrows(ResourceNotFoundException.class, () -> drivingLicenceRemovePointService.removePoint(5, uuid));

    }

    @Test
    void should_remove_more_points_than_driving_licence_have_and_should_be_set_at_0() {
        UUID uuid = UUID.randomUUID();

        when(database.findById(uuid)).thenReturn(Optional.of(DrivingLicence.builder().id(uuid).driverSocialSecurityNumber("123456789012345").build()));

        when(database.save(uuid, DrivingLicence.builder().id(uuid).driverSocialSecurityNumber("123456789012345").availablePoints(0).build()))
                .thenReturn(DrivingLicence.builder().id(uuid).driverSocialSecurityNumber("123456789012345").availablePoints(0).build());

        DrivingLicence drivingLicence =  drivingLicenceRemovePointService.removePoint(15, uuid);

        assertThat(drivingLicence.getAvailablePoints())
                .isNotNull()
                .isEqualTo(0);

        assertThat(drivingLicence).isInstanceOf(DrivingLicence.class);

    }

}
