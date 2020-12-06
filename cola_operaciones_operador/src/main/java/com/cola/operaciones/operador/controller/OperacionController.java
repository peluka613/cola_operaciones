package com.cola.operaciones.operador.controller;

import com.cola.operaciones.operador.model.OperacionRequestDto;
import com.cola.operaciones.operador.model.OperacionResponseDto;
import com.cola.operaciones.operador.model.data.Operacion;
import com.cola.operaciones.operador.service.OperacionService;
import com.cola.operaciones.operador.utils.SesionUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/operacion")
public class OperacionController {

    private OperacionService operacionService;
    private SesionUtils sesionUtils;
    private ModelMapper modelMapper;

    @Autowired
    public OperacionController(OperacionService operacionService, SesionUtils sesionUtils) {
        this.operacionService = operacionService;
        this.sesionUtils = sesionUtils;

        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @GetMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<List<OperacionResponseDto>> operaciones(@RequestParam(required = false) String sesionId) {
        List<Operacion> operacions;
        if (sesionId != null) {
            sesionUtils.validateSesionId(sesionId);
            operacions = operacionService.findAllBySesionId(sesionId);
        } else {
            operacions = operacionService.findAll();
        }

        List<OperacionResponseDto> responseDtos = operacions.stream().map(
                s -> modelMapper.map(s, OperacionResponseDto.class)
        ).collect(Collectors.toList());

        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    @PutMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<OperacionResponseDto> realizarOperacion(@Valid @RequestBody OperacionRequestDto requestDto){
        sesionUtils.validateSesionId(requestDto.getSesionId());

        Operacion operacion = modelMapper.map(requestDto, Operacion.class);
        operacion = operacionService.realizarOperacion(operacion, requestDto.isAppendResult());
        
        OperacionResponseDto responseDto = modelMapper.map(operacion, OperacionResponseDto.class);
        
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
