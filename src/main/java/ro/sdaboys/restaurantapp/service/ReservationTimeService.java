package ro.sdaboys.restaurantapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sdaboys.restaurantapp.dto.ReservationTimeDto;
import ro.sdaboys.restaurantapp.exception.ReservationTimeNotFoundException;
import ro.sdaboys.restaurantapp.model.ReservationTime;
import ro.sdaboys.restaurantapp.repository.ReservationTimeRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ReservationTimeService {

    private ReservationTimeRepository reservationTimeRepository;
        
    @Autowired
    public ReservationTimeService (ReservationTimeRepository reservationTimeRepository){
        this.reservationTimeRepository = reservationTimeRepository;
    }

    public List<ReservationTimeDto> showAllReservations(){
        List<ReservationTime> reservations = reservationTimeRepository.findAll();
        // facem loop prin lista de rezervari
        return reservations.stream().map(this::mapFromReservationTimeToDto).collect(toList());
    }

    private ReservationTimeDto mapFromReservationTimeToDto(ReservationTime reservationTime) {
        ReservationTimeDto reservationTimeDto = new ReservationTimeDto();
        reservationTimeDto.setId(reservationTime.getId());
        reservationTimeDto.setStartTime(reservationTime.getStartTime());
        reservationTimeDto.setEndTime(reservationTime.getEndTime());
        return reservationTimeDto;
    }

    public ReservationTimeDto showReservationById(Long id){
        ReservationTime reservationTime =
                reservationTimeRepository.findById(id).orElseThrow(() -> new ReservationTimeNotFoundException("For " +
                        "id + " +id));
        return mapFromReservationTimeToDto(reservationTime);
    }

    public void createReservation(ReservationTimeDto reservationTimeDto){
        ReservationTime reservationTime = mapFromDtoToReservationTime(reservationTimeDto);
        reservationTimeRepository.save(reservationTime);
    }

    private ReservationTime mapFromDtoToReservationTime(ReservationTimeDto reservationTimeDto) {
        ReservationTime reservationTime = new ReservationTime();
        reservationTime.setStartTime(reservationTimeDto.getStartTime());
        reservationTime.setEndTime(reservationTimeDto.getEndTime());
        return reservationTime;
    }
}
