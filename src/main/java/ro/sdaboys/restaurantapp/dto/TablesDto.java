package ro.sdaboys.restaurantapp.dto;

import java.util.List;

public class TablesDto {
    private Long id;
    private int size;
    private List<ReservationTimeDto> reservationTimeDtoList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<ReservationTimeDto> getReservationTimeDtoList() {
        return reservationTimeDtoList;
    }

    public void setReservationTimeDtoList(List<ReservationTimeDto> reservationTimeDtoList) {
        this.reservationTimeDtoList = reservationTimeDtoList;
    }
}
