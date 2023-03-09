package axon.soft.backend.coding.challenge.service;

import axon.soft.backend.coding.challenge.domain.entity.PersonEntity;
import axon.soft.backend.coding.challenge.domain.entity.RoomEntity;
import axon.soft.backend.coding.challenge.domain.repository.PeopleRepository;
import axon.soft.backend.coding.challenge.domain.repository.RoomRepository;
import axon.soft.backend.coding.challenge.helper.AppMapper;
import axon.soft.backend.coding.challenge.helper.CSVHelper;
import axon.soft.backend.coding.challenge.model.RoomDTO;
import com.opencsv.exceptions.CsvException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class CSVService {
    @Autowired
    private CSVHelper csvHelper;

    @Autowired
    private AppMapper appMapper;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private PeopleRepository peopleRepository;

    @Transactional
    public List<RoomDTO> readRoomSittingFromCSV(BufferedReader file) throws IOException, CsvException {
        List<RoomDTO> roomDTOs = csvHelper.csvToJson(file);

        peopleRepository.deleteAll();
        roomRepository.deleteAll();

        for (RoomDTO roomDTO : roomDTOs) {
            RoomEntity roomEntity = appMapper.mapToRoomEntity(roomDTO);

            List<PersonEntity> peopleEntities = roomDTO.getPeople().stream()
                    .map(personDTO -> {
                        PersonEntity personEntity = appMapper.mapToPersonEntity(personDTO);
                        personEntity.setRoomEntity(roomEntity);
                        return personEntity;
                    })
                    .collect(Collectors.toList());

            roomEntity.setPersonEntities(peopleEntities);
            roomRepository.save(roomEntity);
        }
        return roomDTOs;
    }
}
