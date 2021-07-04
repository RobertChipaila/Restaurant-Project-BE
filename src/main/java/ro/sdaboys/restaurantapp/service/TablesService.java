package ro.sdaboys.restaurantapp.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sdaboys.restaurantapp.dto.TablesDto;
import ro.sdaboys.restaurantapp.model.Tables;
import ro.sdaboys.restaurantapp.repository.TablesRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TablesService {
    private ModelMapper modelMapper;
    private TablesRepository tablesRepository;


    @Autowired
    public TablesService(ModelMapper modelMapper, TablesRepository tablesRepository) {
        this.modelMapper = modelMapper;
        this.tablesRepository = tablesRepository;
    }

    private TablesDto mapFromTablesToDto (Tables tables){
        return modelMapper.map(tables, TablesDto.class);
    }

    private Tables mapFromDtoToTables (TablesDto tablesDto){
        return modelMapper.map(tablesDto, Tables.class);
    }

    public TablesDto createTable(TablesDto tablesDto){
        Tables tables = mapFromDtoToTables(tablesDto);
        tablesRepository.save(tables);
    return tablesDto;
    }

    public List<TablesDto> showAllTables() {
        List<Tables> tables = tablesRepository.findAll();
        return tables.stream().map(this::mapFromTablesToDto).collect(Collectors.toList());
    }

}
