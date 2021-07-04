package ro.sdaboys.restaurantapp.dto;

public class SeatsDto {

    private Long id;
    private Boolean isSelected;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
