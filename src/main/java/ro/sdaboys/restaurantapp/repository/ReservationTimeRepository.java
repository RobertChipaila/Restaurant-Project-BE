package ro.sdaboys.restaurantapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.sdaboys.restaurantapp.model.ReservationTime;
import ro.sdaboys.restaurantapp.model.Tables;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationTimeRepository extends JpaRepository<ReservationTime, Long> {

    // facem un query care va returna o lista de reservationTime; luam toate intervalele care exista in ziua
    // respectiva, care ulterior le vom compara in validator

    List<ReservationTime> findAllByTablesEqualsAndStartTimeAfterAndEndTimeBefore(Tables tables,
                                                                                 LocalDateTime startTime,
                                                                                 LocalDateTime endTime);

}
