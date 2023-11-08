package com.example.lms.global.domain.response;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


@Getter
@Setter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class LmsResponse<T> {
    private HttpStatus code;
    private T data; //자식 클래스 형태를 지정을 안해줘서, 부모 클래스의 리스폰스형태를 그대로 뱉는데, data는 자식 클래스의 리스폰스 데이터에서 뽑아온 데이터인거죠
    private String msg;
    private String  errorMsg;
    private LocalDateTime currentTime;

    public LmsResponse(HttpStatus code, T data, String msg, String errorMsg, LocalDateTime currentTime) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.errorMsg= errorMsg;
        this.currentTime = currentTime;
    }


}
