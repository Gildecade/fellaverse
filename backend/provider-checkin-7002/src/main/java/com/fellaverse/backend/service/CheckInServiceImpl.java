package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.CheckIn;
import com.fellaverse.backend.bean.CheckInId;
import com.fellaverse.backend.bean.User;
import com.fellaverse.backend.projection.CheckInInfo;
import com.fellaverse.backend.repository.CheckInRepository;
import com.fellaverse.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CheckInServiceImpl implements CheckInService{
    @Autowired
    CheckInRepository checkInRepository;

    @Autowired
    UserRepository userRepository;
    @Override
    public List<CheckIn> findUserCheckIn(Long userId) {
        return checkInRepository.findByUser_Id(userId);
    }

    @Override
    public Boolean isCheckInDuplicate(CheckIn checkIn) {
        return !checkInRepository.isOverlap(checkIn.getStartDateTime(), checkIn.getEndDateTime()).isEmpty();
    }

    @Override
    public Boolean addCheckIn(CheckIn checkIn) {
        if (isCheckInDuplicate(checkIn))
            return false;
        else {
            checkInRepository.save(checkIn);
            return true;
        }
    }

    @Override
    public Boolean editCheckIn(CheckIn checkIn) {
        CheckInId checkInId = new CheckInId(checkIn.getId(), checkIn.getUser());
        if (isCheckInDuplicate(checkIn) || checkInRepository.existsById(checkInId))
            return false;
        else {
            checkInRepository.save(checkIn);
            return true;
        }
    }

    @Override
    public Boolean removeCheckIn(Long id, Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        CheckInId checkInId = new CheckInId(id, user);
        if (checkInRepository.existsById(checkInId)) {
            checkInRepository.deleteById(checkInId);
            return true;
        }
        else
            return false;
    }

    @Override
    public Set<CheckIn> findAllCheckIn() {
        return new HashSet<>(checkInRepository.findAll());
    }

    @Override
    public CheckIn findById(Long id, Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        CheckInId checkInId = new CheckInId(id, user);
        return checkInRepository.findById(checkInId).orElse(null);
    }
}
