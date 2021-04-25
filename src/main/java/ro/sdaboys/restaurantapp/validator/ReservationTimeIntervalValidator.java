package ro.sdaboys.restaurantapp.validator;


import org.springframework.stereotype.Component;
import ro.sdaboys.restaurantapp.dto.ReservationTimeDto;

import java.time.Duration;
import java.time.LocalDateTime;
//il facem component ca sa l putem injecta

@Component
public class ReservationTimeIntervalValidator {
    public boolean isValid(ReservationTimeDto reservationTimeDto){
        LocalDateTime startTime =  reservationTimeDto.getStartTime();
        LocalDateTime endTime =  reservationTimeDto.getEndTime();
        // diferenta intre 2 LocalDateTime -> Duration
        Duration duration = Duration.between(startTime, endTime);
        duration.toHours();
        System.out.println(duration);
        // testam cu false ca sa nu salveze
        return false;
    }
}
