package ro.sdaboys.restaurantapp.service;

import org.modelmapper.ModelMapper;
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

    @Autowired
    private ModelMapper modelMapper;

    private ReservationTimeRepository reservationTimeRepository;

    @Autowired
    public ReservationTimeService(ReservationTimeRepository reservationTimeRepository) {
        this.reservationTimeRepository = reservationTimeRepository;
    }

    public List<ReservationTimeDto> showAllReservations() {
        List<ReservationTime> reservations = reservationTimeRepository.findAll();
        // facem loop prin lista de rezervari
        return reservations.stream().map(this::mapFromReservationTimeToDto).collect(toList());
    }

    private ReservationTimeDto mapFromReservationTimeToDto(ReservationTime reservationTime) {
        return modelMapper.map(reservationTime, ReservationTimeDto.class);
    }

    public ReservationTimeDto showReservationById(Long id) {
        ReservationTime reservationTime =
                reservationTimeRepository.findById(id).orElseThrow(() -> new ReservationTimeNotFoundException("For " +
                        "id + " + id));
        return mapFromReservationTimeToDto(reservationTime);
    }

    public void createReservation(ReservationTimeDto reservationTimeDto) {
        ReservationTime reservationTime = mapFromDtoToReservationTime(reservationTimeDto);
        reservationTimeRepository.save(reservationTime);
    }

    private ReservationTime mapFromDtoToReservationTime(ReservationTimeDto reservationTimeDto) {
        return modelMapper.map(reservationTimeDto, ReservationTime.class);
    }
}
