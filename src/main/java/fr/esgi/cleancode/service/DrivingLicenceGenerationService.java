package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import fr.esgi.cleancode.model.DrivingLicence;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DrivingLicenceGenerationService {

    private final InMemoryDatabase database;

    private final SocialSecurityNumberValidatorService socialSecurityNumberValidatorService  = new SocialSecurityNumberValidatorService();

    private final DrivingLicenceIdGenerationService drivingLicenceIdGenerationService  = new DrivingLicenceIdGenerationService();

    public DrivingLicence generateNewDrivingLicence(String driverSocialSecurityNumber) {

        if(!this.socialSecurityNumberValidatorService.isNotNull(driverSocialSecurityNumber))
            throw new InvalidDriverSocialSecurityNumberException("Social security number is null");

        if(!this.socialSecurityNumberValidatorService.isEqualsTo15Numbers(driverSocialSecurityNumber))
            throw new InvalidDriverSocialSecurityNumberException("Social security number is not equals to 15 numbers");

        if(!this.socialSecurityNumberValidatorService.isOnlyNumbers(driverSocialSecurityNumber))
            throw new InvalidDriverSocialSecurityNumberException("Social security number is not only numbers");

        DrivingLicence drivingLicence = DrivingLicence.builder()
                .id(drivingLicenceIdGenerationService.generateNewDrivingLicenceId())
                .driverSocialSecurityNumber(driverSocialSecurityNumber)
                .build();

        return database.save(drivingLicence.getId(), drivingLicence);
    }
}
