package ro.sdaboys.restaurantapp.validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.sdaboys.restaurantapp.dto.ReservationTimeDto;
import ro.sdaboys.restaurantapp.model.ReservationTime;
import ro.sdaboys.restaurantapp.model.Tables;
import ro.sdaboys.restaurantapp.repository.ReservationTimeRepository;
import ro.sdaboys.restaurantapp.repository.TablesRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
//il facem component ca sa l putem injecta

@Component
public class ReservationTimeIntervalValidator {

    private ReservationTimeRepository reservationTimeRepository;
    private TablesRepository tablesRepository;

    @Autowired
    public ReservationTimeIntervalValidator(ReservationTimeRepository reservationTimeRepository, TablesRepository tablesRepository) {
        this.reservationTimeRepository = reservationTimeRepository;
        this.tablesRepository = tablesRepository;
    }

    public boolean isValid(ReservationTimeDto reservationTimeDto) {
        LocalDateTime startTime = reservationTimeDto.getStartTime();
        LocalDateTime endTime = reservationTimeDto.getEndTime();
        // diferenta intre 2 LocalDateTime -> Duration
        Duration duration = Duration.between(startTime, endTime);
        // valideaza doar ca diferenta este mai mare de 60
        return duration.toMinutes() >= 60 && duration.toMinutes() <= 180; // ca sa nu depasim o anumita durata a
        // rezervarii
        // testam cu false ca sa nu salveze
    }

    // verificam ca intervalul rezervarii nu se suprapune cu o alta rezervare din aceiasi zi
    public boolean isValidInterval(ReservationTimeDto reservationTimeDto) {
        LocalDateTime startTime = reservationTimeDto.getStartTime();
        LocalDateTime endTime = reservationTimeDto.getEndTime();

        int startTimeDayOfMonth = startTime.getDayOfMonth();
        int startTimeMonth = startTime.getMonthValue();
        int startTimeYear = startTime.getYear();
        LocalDateTime reservationStart = LocalDateTime.of(startTimeYear, startTimeMonth, startTimeDayOfMonth, 10, 59);
        System.out.println(reservationStart);

        int endTimeDayOfMonth = endTime.getDayOfMonth();
        int endTimeMonth = endTime.getMonthValue();
        int endTimeYear = endTime.getYear();
        LocalDateTime reservationEnd = LocalDateTime.of(startTimeYear, startTimeMonth, startTimeDayOfMonth, 21, 1);
        System.out.println(reservationEnd);

        // atentie! in dataJPA query-ul find by id ne returneaza un Optional si este recomandat sa avem metoda
        //  orElseThrow in cazul in care nu gasim id-ul in baza de date.
        Tables currentTable = tablesRepository.findById(reservationTimeDto.getTableId()).orElseThrow();
        // returnam toate rezervarile din ziua respectiva
        List<ReservationTime> reservationTimeList =
                reservationTimeRepository.findAllByTablesEqualsAndStartTimeAfterAndEndTimeBefore(currentTable,
                        reservationStart, reservationEnd);
        reservationTimeList.forEach(System.out::println);

        return reservationTimeList.stream().allMatch(x -> isOverlappingInterval(reservationTimeDto.getStartTime(),
                reservationTimeDto.getEndTime(), x.getStartTime(), x.getEndTime()));
    }

    private boolean isOverlappingInterval(LocalDateTime startTime, LocalDateTime endTime,
                                          LocalDateTime existingStartTime, LocalDateTime existingEndTime) {
        return (startTime.isBefore(existingStartTime) && endTime.isBefore(existingStartTime)) ||
                (startTime.isAfter(existingEndTime) && endTime.isAfter(existingEndTime));
    }

}
