package ro.sdaboys.restaurantapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.sdaboys.restaurantapp.model.Tables;

@Repository
public interface TablesRepository extends JpaRepository <Tables, Long>{
}
