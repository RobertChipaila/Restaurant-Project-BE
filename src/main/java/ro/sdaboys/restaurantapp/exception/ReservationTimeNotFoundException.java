package ro.sdaboys.restaurantapp.exception;

public class ReservationTimeNotFoundException extends RuntimeException {
    public ReservationTimeNotFoundException(String message) {
        super(message);
    }
}
