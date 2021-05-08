package ro.sdaboys.restaurantapp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tables")
public class Tables {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "table_size")
    private int size;

    @OneToMany(mappedBy = "tables", cascade = CascadeType.ALL)
    // specifica care e parintele din relatie (adica table)
    // cascade -> propaga schimbarile catre celalalte entitati (copil - reservationTime)
    private List<ReservationTime> reservationTimeList;

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

    public List<ReservationTime> getReservationTimeList() {
        return reservationTimeList;
    }

    public void setReservationTimeList(List<ReservationTime> reservationTimeList) {
        this.reservationTimeList = reservationTimeList;
    }
}
