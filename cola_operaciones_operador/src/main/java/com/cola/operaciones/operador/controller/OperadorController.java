package com.cola.operaciones.operador.controller;

import com.cola.operaciones.operador.model.OperandoRequestDto;
import com.cola.operaciones.operador.model.OperandoResponseDto;
import com.cola.operaciones.operador.model.data.Operando;
import com.cola.operaciones.operador.service.OperandoService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/operando")
public class OperadorController {

    private OperandoService operandoService;
    private ModelMapper modelMapper;

    @Autowired
    public OperadorController(OperandoService operandoService) {
        this.operandoService = operandoService;

        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @GetMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<List<OperandoRequestDto>> operandos() {
        List<Operando> operandos = operandoService.findAll();
        List<OperandoRequestDto> responseDtos = operandos.stream().map(
                s -> modelMapper.map(s, OperandoRequestDto.class)
        ).collect(Collectors.toList());

        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<OperandoResponseDto> addOperando(@Valid @RequestBody OperandoRequestDto requestDto) {
        Operando operando = modelMapper.map(requestDto, Operando.class);
        operando = operandoService.addOperando(operando);

        OperandoResponseDto responseDto = modelMapper.map(operando, OperandoResponseDto.class);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
