package com.javarush.bikeservice.services;

import com.javarush.bikeservice.entities.bike_service_entities.Staff;
import com.javarush.bikeservice.repositories.StaffRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.javarush.bikeservice.constants.ExceptionConstants.STAFF_NOT_FOUND_EXCEPTION;
import static java.util.Objects.isNull;

@Service
public class StaffService {
    private final StaffRepository stRepo;

    public StaffService(StaffRepository stRepo) {
        this.stRepo = stRepo;
    }
    public List<Staff> findAllStaff() {
        return stRepo.findAll();
    }
    public Staff findByPassport(String passport) {
        Staff staff = null;
        StringBuilder passportBuilder = new StringBuilder(passport);
        for(Staff s : stRepo.findAll()) {
            StringBuilder staffPassport = new StringBuilder(s.getPassport());
            if (staffPassport.compareTo(passportBuilder) == 0) {
                staff = s;
                break;
            }
        }
        if (isNull(staff)) {
            throw new NullPointerException(STAFF_NOT_FOUND_EXCEPTION);
        }
        return staff;
    }
    public Staff findStaffById(Integer id) {
        Staff staff = stRepo.findById(id);

        if(!isNull(staff)) {
            return staff;
        } else {
            throw new NullPointerException(STAFF_NOT_FOUND_EXCEPTION);
        }
    }
    public void addNewStaff(@NotNull Staff staff) {
        stRepo.save(staff);
    }
    public void updateStaff(@NotNull Staff staff) {
        stRepo.update(staff);
    }
    public void deleteStaffById(Integer id) {
        if(!isNull(stRepo.findById(id))) {
            stRepo.deleteById(id);
        } else {
            throw new NullPointerException(STAFF_NOT_FOUND_EXCEPTION);
        }
    }
}
