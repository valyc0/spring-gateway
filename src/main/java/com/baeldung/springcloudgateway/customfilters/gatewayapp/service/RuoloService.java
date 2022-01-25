package com.baeldung.springcloudgateway.customfilters.gatewayapp.service;


import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.baeldung.springcloudgateway.customfilters.gatewayapp.domain.Ruolo;
import com.baeldung.springcloudgateway.customfilters.gatewayapp.model.RuoloDTO;
import com.baeldung.springcloudgateway.customfilters.gatewayapp.repos.RuoloRepository;


@Service
public class RuoloService {

    private final RuoloRepository ruoloRepository;

    public RuoloService(final RuoloRepository ruoloRepository) {
        this.ruoloRepository = ruoloRepository;
    }

    public List<RuoloDTO> findAll() {
        return ruoloRepository.findAll()
                .stream()
                .map(ruolo -> mapToDTO(ruolo, new RuoloDTO()))
                .collect(Collectors.toList());
    }

    public RuoloDTO get(final Long id) {
        return ruoloRepository.findById(id)
                .map(ruolo -> mapToDTO(ruolo, new RuoloDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final RuoloDTO ruoloDTO) {
        final Ruolo ruolo = new Ruolo();
        mapToEntity(ruoloDTO, ruolo);
        return ruoloRepository.save(ruolo).getId();
    }

    public void update(final Long id, final RuoloDTO ruoloDTO) {
        final Ruolo ruolo = ruoloRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(ruoloDTO, ruolo);
        ruoloRepository.save(ruolo);
    }

    public void delete(final Long id) {
        ruoloRepository.deleteById(id);
    }

    private RuoloDTO mapToDTO(final Ruolo ruolo, final RuoloDTO ruoloDTO) {
        ruoloDTO.setId(ruolo.getId());
        ruoloDTO.setNome(ruolo.getNome());
        return ruoloDTO;
    }

    private Ruolo mapToEntity(final RuoloDTO ruoloDTO, final Ruolo ruolo) {
        ruolo.setNome(ruoloDTO.getNome());
        return ruolo;
    }

}
