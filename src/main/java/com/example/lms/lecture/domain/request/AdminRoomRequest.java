package com.example.lms.lecture.domain.request;

import com.example.lms.room.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminRoomRequest {

    private Long roomId;

    public Room toEntity(){
        return Room
                .builder()
                .id(roomId)
                .build();
    }
}
