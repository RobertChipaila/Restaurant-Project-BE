package ro.sdaboys.restaurantapp.dto;

import java.time.LocalDateTime;

public class ReservationTimeDto {

    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int numberOfReservedSeats;
    // asocieara cu obiectul table pentru care facem rezervarea
    private Long tableId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getNumberOfReservedSeats() {
        return numberOfReservedSeats;
    }

    public void setNumberOfReservedSeats(int numberOfReservedSeats) {
        this.numberOfReservedSeats = numberOfReservedSeats;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }
}
