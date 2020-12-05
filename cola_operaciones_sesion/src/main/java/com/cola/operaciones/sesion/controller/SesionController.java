package com.cola.operaciones.sesion.controller;

import com.cola.operaciones.sesion.model.SesionResponseDto;
import com.cola.operaciones.sesion.model.data.Sesion;
import com.cola.operaciones.sesion.service.SesionService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sesion")
public class SesionController {

    private SesionService sesionService;
    private ModelMapper modelMapper;

    @Autowired
    public SesionController(SesionService sesionService) {
        this.sesionService = sesionService;

        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @GetMapping (
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<List<SesionResponseDto>> sesiones() {
        List<Sesion> sesions = sesionService.findAll();
        List<SesionResponseDto> responseDtos = sesions.stream().map(
                s -> modelMapper.map(s, SesionResponseDto.class)
        ).collect(Collectors.toList());

        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SesionResponseDto> createSesion(){
        Sesion sesion = sesionService.createSesion();
        SesionResponseDto responseDto = modelMapper.map(sesion, SesionResponseDto.class);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
