package com.example.lms.room.repository;

import com.example.lms.room.entity.Room;
import com.example.lms.schedule.entity.WeekDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository
        extends JpaRepository<Room, Long> {

    @Query("select r from Room as r where r.id = :roomId and r.roomCheck = false ")
    Optional<Room> findByIdandRoomCheck(@Param("roomId")Long roomId);

}
