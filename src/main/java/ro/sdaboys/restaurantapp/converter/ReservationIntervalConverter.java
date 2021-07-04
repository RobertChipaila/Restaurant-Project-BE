package ro.sdaboys.restaurantapp.converter;

import org.springframework.stereotype.Component;
import ro.sdaboys.restaurantapp.dto.ReservationTimeDto;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ReservationIntervalConverter {
    public ReservationTimeDto adjustTime (ReservationTimeDto reservationTimeDto){
        LocalDateTime startTime =  reservationTimeDto.getStartTime();
        LocalDateTime endTime =  reservationTimeDto.getEndTime();
        Duration duration = Duration.between(startTime, endTime);
        long durationInMinutes = duration.toMinutes() % 30;
        if (durationInMinutes == 0){
            return reservationTimeDto;
        } else if (durationInMinutes >= 15){
            endTime = endTime.plusMinutes(30 - durationInMinutes);
            reservationTimeDto.setEndTime(endTime);
            return reservationTimeDto;
        } else {
            endTime = endTime.minusMinutes(durationInMinutes);
            reservationTimeDto.setEndTime(endTime);
            return reservationTimeDto;
        }
    }
}
