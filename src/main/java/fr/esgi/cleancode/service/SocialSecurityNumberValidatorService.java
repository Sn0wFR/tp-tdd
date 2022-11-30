package fr.esgi.cleancode.service;

public class SocialSecurityNumberValidatorService {

    public boolean isNotNull(String socialSecurityNumber) {
        return socialSecurityNumber != null;
    }

    public boolean isEqualsTo15Numbers(String socialSecurityNumber) {
        return socialSecurityNumber.length() == 15;
    }

    public boolean isOnlyNumbers(String socialSecurityNumber) {
        return socialSecurityNumber.matches("[0-9]+");
    }

}
