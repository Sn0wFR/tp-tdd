package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.exception.ResourceNotFoundException;
import fr.esgi.cleancode.model.DrivingLicence;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class DrivingLicenceRemovePointService {

    private final InMemoryDatabase database;

    public DrivingLicence removePoint(int pointToRemove, UUID driverLicenceId) {

        Optional<DrivingLicence> optionalDrivingLicence = database.findById(driverLicenceId);

        if(!optionalDrivingLicence.isPresent())
            throw new ResourceNotFoundException("this licence does not exist");

        DrivingLicence drivingLicence = optionalDrivingLicence.get();

        int pointNumber = drivingLicence.getAvailablePoints() - pointToRemove;

        if( pointNumber < 0)
            pointNumber = 0;

        DrivingLicence newDrivingLicence = DrivingLicence.builder().id(driverLicenceId)
                .driverSocialSecurityNumber(drivingLicence.getDriverSocialSecurityNumber())
                .availablePoints(pointNumber).build();
        return database.save(driverLicenceId, newDrivingLicence);
    }



}
