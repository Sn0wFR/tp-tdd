package fr.esgi.cleancode.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class SocialSecurityNumberValidatorServiceTest {

    private SocialSecurityNumberValidatorService socialSecurityNumberValidatorService;

    @Test
    public void should_return_true_when_number_is_not_null(){
        Assertions.assertTrue(socialSecurityNumberValidatorService.isNotNull("123456789012345"));
    }

    @Test
    public void should_return_false_when_number_is_null(){
        Assertions.assertFalse(socialSecurityNumberValidatorService.isNotNull(null));
    }

    @Test
    public void should_return_true_when_number_is_equals_to_15_numbers(){
        Assertions.assertTrue(socialSecurityNumberValidatorService.isEqualsTo15Numbers("123456789012345"));
    }

    @Test
    public void should_return_false_when_number_is_not_equals_to_15_numbers() {
        Assertions.assertFalse(socialSecurityNumberValidatorService.isEqualsTo15Numbers("123456789"));
    }

    @Test
    public void should_return_true_when_number_is_only_numbers(){
        Assertions.assertTrue(socialSecurityNumberValidatorService.isOnlyNumbers("123456789012345"));
    }

    @Test
    public void should_return_false_when_number_is_not_only_numbers() {
        Assertions.assertFalse(socialSecurityNumberValidatorService.isOnlyNumbers("12345678901234a"));
    }

}
