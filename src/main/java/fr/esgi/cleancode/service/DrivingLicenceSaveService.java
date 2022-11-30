package fr.esgi.cleancode.service;

import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import fr.esgi.cleancode.model.DrivingLicence;

public class DrivingLicenceSaveService {

    private final SocialSecurityNumberValidatorService socialSecurityNumberValidatorService = new SocialSecurityNumberValidatorService();

    public DrivingLicence save(DrivingLicence drivingLicence) throws InvalidDriverSocialSecurityNumberException {

        if(!socialSecurityNumberValidatorService.isNotNull(drivingLicence.getDriverSocialSecurityNumber())){
            throw new InvalidDriverSocialSecurityNumberException("Social security number is null");
        }

        if(!socialSecurityNumberValidatorService.isEqualsTo15Numbers(drivingLicence.getDriverSocialSecurityNumber())){
            throw new InvalidDriverSocialSecurityNumberException("Social security number is not equals to 15 numbers");
        }

        if(!socialSecurityNumberValidatorService.isOnlyNumbers(drivingLicence.getDriverSocialSecurityNumber())){
            throw new InvalidDriverSocialSecurityNumberException("Social security number is not only numbers");
        }

        return drivingLicence;
    }

}
