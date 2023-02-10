package com.fellaverse.backend.service;

import com.fellaverse.backend.dto.CheckInDTO;

import java.util.List;

public interface CheckInService {
    /**
    * return all check in record from a user by userID
    */
    public List<CheckInDTO> findUserCheckInHistory(Long userId);

    /**
    * return true if there repeat check-in in DB already.
    * return false for a good operation to insert new check-in
     */
    public Boolean  isCheckInDuplicate(CheckInDTO checkInDto);

    /**
     * return true for success add new check-in.
     */
    public Boolean addUserCheckInItem(CheckInDTO checkInDto);

    /**
     * return true for success deleting check-in.
     */
    public Boolean removeUserCheckInItem(Long userId, Long CheckInId);
}
