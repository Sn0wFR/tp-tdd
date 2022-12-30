package fr.esgi.cleancode.service;

import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import fr.esgi.cleancode.model.DrivingLicence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DrivingLicenceSaveServiceTest {

        @InjectMocks
        DrivingLicenceSaveService drivingLicenceSaveService;
        @Mock
        DrivingLicence drivingLicence;

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

                when(drivingLicence.getDriverSocialSecurityNumber()).thenReturn("123456789012345");

                Assertions.assertDoesNotThrow(() -> drivingLicenceSaveService.save(drivingLicence));
                Assertions.assertEquals(drivingLicence, drivingLicenceSaveService.save(drivingLicence));
        }



}
