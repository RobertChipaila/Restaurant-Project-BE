package ro.sdaboys.restaurantapp.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sdaboys.restaurantapp.converter.ReservationIntervalConverter;
import ro.sdaboys.restaurantapp.dto.ReservationTimeDto;
import ro.sdaboys.restaurantapp.exception.ReservationTimeNotFoundException;
import ro.sdaboys.restaurantapp.model.ReservationTime;
import ro.sdaboys.restaurantapp.model.Tables;
import ro.sdaboys.restaurantapp.repository.ReservationTimeRepository;
import ro.sdaboys.restaurantapp.repository.TablesRepository;
import ro.sdaboys.restaurantapp.validator.ReservationTimeIntervalValidator;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ReservationTimeService {


    private ModelMapper modelMapper;
    private ReservationTimeRepository reservationTimeRepository;
    private ReservationTimeIntervalValidator validator;
    private ReservationIntervalConverter converter;
    private TablesRepository tablesRepository;

    @Autowired
    public ReservationTimeService(ReservationTimeRepository reservationTimeRepository,
                                  ReservationTimeIntervalValidator validator, ModelMapper modelMapper,
                                  ReservationIntervalConverter converter, TablesRepository tablesRepository) {
        this.reservationTimeRepository = reservationTimeRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.converter = converter;
        this.tablesRepository = tablesRepository;
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

    public ReservationTimeDto createReservation(ReservationTimeDto reservationTimeDto) {
        if (validator.isValid(reservationTimeDto)) {
            reservationTimeDto = converter.adjustTime(reservationTimeDto);
            ReservationTime reservationTime = mapFromDtoToReservationTime(reservationTimeDto);
            Tables table = tablesRepository.findById(reservationTimeDto.getTableId()).orElseThrow();
            reservationTime.setTables(table);
            ReservationTime savedResevationTime = reservationTimeRepository.save(reservationTime);
            // adauga rezervarea curenta la lista totala de rezervari a mesei
            table.getReservationTimeList().add(savedResevationTime);
            tablesRepository.save(table);

        }
        return reservationTimeDto;

    }

    private ReservationTime mapFromDtoToReservationTime(ReservationTimeDto reservationTimeDto) {
        return modelMapper.map(reservationTimeDto, ReservationTime.class);
    }

    public void deleteReservationTime(Long id) {
        reservationTimeRepository.deleteById(id);
    }

    public ReservationTimeDto updateReservationTime(Long id, ReservationTimeDto reservationTimeDto) {
        ReservationTime reservationTime = reservationTimeRepository.findById(id).orElseThrow(() -> new ReservationTimeNotFoundException("For " +
                "id + " + id));
        reservationTime.setStartTime(reservationTimeDto.getStartTime());
        reservationTime.setEndTime(reservationTimeDto.getEndTime());
        return mapFromReservationTimeToDto(reservationTimeRepository.save(reservationTime));

    }

}
