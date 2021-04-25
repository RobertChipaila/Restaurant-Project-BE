package ro.sdaboys.restaurantapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.sdaboys.restaurantapp.dto.ReservationTimeDto;
import ro.sdaboys.restaurantapp.service.ReservationTimeService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
//@RequestMapping("/api/reservationTime/")
public class ReservationTimeController {

    private final ReservationTimeService reservationTimeService;

    @Autowired
    public ReservationTimeController(ReservationTimeService reservationTimeService){
        this.reservationTimeService = reservationTimeService;
    }

    @PostMapping
    public ResponseEntity createReservation(@RequestBody ReservationTimeDto reservationTimeDto){
        reservationTimeService.createReservation(reservationTimeDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReservationTimeDto>> showAllReservations(){
        return new ResponseEntity<>(reservationTimeService.showAllReservations(), HttpStatus.OK);
    }

    @GetMapping("/simple")
    public String text(){
        return "test";
    }

// ne generam obiectul jSon facand get de pe recordurile din Dto
    @GetMapping("/hackDtoRecords")
    public ResponseEntity<List<ReservationTimeDto>> showDtoRecords(){
        ReservationTimeDto reservationTimeDto = new ReservationTimeDto();
        reservationTimeDto.setStartTime(LocalDateTime.now());
        reservationTimeDto.setEndTime(LocalDateTime.now());
        reservationTimeService.createReservation(reservationTimeDto);
        return new ResponseEntity<>(reservationTimeService.showAllReservations(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationTimeDto> getSingleReservationTime(@PathVariable @RequestBody Long id){
        return new ResponseEntity<>(reservationTimeService.showReservationById(id), HttpStatus.OK);
    }


}
