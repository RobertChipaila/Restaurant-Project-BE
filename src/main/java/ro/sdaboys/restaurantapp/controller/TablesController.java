package ro.sdaboys.restaurantapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.sdaboys.restaurantapp.dto.TablesDto;
import ro.sdaboys.restaurantapp.service.TablesService;

import java.util.List;

@RestController
public class TablesController {

    private final TablesService tablesService;

    @Autowired
    public TablesController(TablesService tablesService) {
        this.tablesService = tablesService;
    }

    @GetMapping("/hackDtoRecords")
    public ResponseEntity<List<TablesDto>> showDtoRecords() {
        TablesDto tablesDto = new TablesDto();
        tablesDto.setSize(8);
        tablesDto.setReservationTimeDtoList(List.of()); // creaza empty list
        tablesService.createTable(tablesDto);
        return new ResponseEntity<>(tablesService.showAllTables(), HttpStatus.OK);
    }

}
