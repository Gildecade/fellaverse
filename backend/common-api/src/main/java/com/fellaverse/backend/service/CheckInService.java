package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.CheckIn;

import java.util.List;
import java.util.Set;

public interface CheckInService {
    /**
    * return all check in record from a user by userID
    */
    List<CheckIn> findUserCheckIn(Long userId);

    /**
    * return true if there repeat check-in in DB already.
    * return false for a good operation to insert new check-in
     */
    Boolean  isCheckInDuplicate(CheckIn checkIn);

    /**
     * return true for success add new check-in.
     */
    Boolean addCheckIn(CheckIn checkIn);
    Boolean editCheckIn(CheckIn checkIn);

    /**
     * return true for success deleting check-in.
     */
    Boolean removeCheckIn(Long userId, Long checkInId);

    /**
     * return all check-in.
     */
    Set<CheckIn> findAllCheckIn();

    CheckIn findById(Long id, Long userId);

}
