package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import fr.esgi.cleancode.model.DrivingLicence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class DrivingLicenceSaveServiceTest {

        @Mock
        SocialSecurityNumberValidatorService socialSecurityNumberValidatorService;
        @InjectMocks
        DrivingLicenceSaveService drivingLicenceSaveService;
        @Mock
        DrivingLicence drivingLicence;
        @Mock
        InMemoryDatabase database;

        @Test
        public void should_throw_invalid_driver_social_security_number_exception_when_social_security_number_is_null() {
                when(drivingLicence.getDriverSocialSecurityNumber()).thenReturn(null);

                Assertions.assertThrows(InvalidDriverSocialSecurityNumberException.class, () -> drivingLicenceSaveService.save(drivingLicence));
        }

        @Test
        public void should_throw_invalid_driver_social_security_number_exception_when_social_security_number_is_not_equals_to_15_numbers() {
                when(drivingLicence.getDriverSocialSecurityNumber()).thenReturn("123456789");

                Assertions.assertThrows(InvalidDriverSocialSecurityNumberException.class, () -> drivingLicenceSaveService.save(drivingLicence));
        }

        @Test
        public void should_throw_invalid_driver_social_security_number_exception_when_social_security_number_is_not_only_numbers() {
                when(drivingLicence.getDriverSocialSecurityNumber()).thenReturn("12345678901234a");

                Assertions.assertThrows(InvalidDriverSocialSecurityNumberException.class, () -> drivingLicenceSaveService.save(drivingLicence));
        }

        @Test
        public void should_save_driver_licence(){

                UUID id = UUID.randomUUID();
                when(drivingLicence.getDriverSocialSecurityNumber()).thenReturn("123456789012345");
                when(database.save(id,drivingLicence)).thenReturn(drivingLicence);

                Assertions.assertDoesNotThrow(() -> drivingLicenceSaveService.save(drivingLicence));
                Assertions.assertEquals(drivingLicence, drivingLicenceSaveService.save(drivingLicence));
        }



}
