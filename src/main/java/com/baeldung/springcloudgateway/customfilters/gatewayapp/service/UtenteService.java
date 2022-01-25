package com.baeldung.springcloudgateway.customfilters.gatewayapp.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.baeldung.springcloudgateway.customfilters.gatewayapp.domain.Ruolo;
import com.baeldung.springcloudgateway.customfilters.gatewayapp.domain.Utente;
import com.baeldung.springcloudgateway.customfilters.gatewayapp.model.UtenteDTO;
import com.baeldung.springcloudgateway.customfilters.gatewayapp.repos.RuoloRepository;
import com.baeldung.springcloudgateway.customfilters.gatewayapp.repos.UtenteRepository;


@Service
public class UtenteService {

    private final UtenteRepository utenteRepository;
    private final RuoloRepository ruoloRepository;

    public UtenteService(final UtenteRepository utenteRepository,
            final RuoloRepository ruoloRepository) {
        this.utenteRepository = utenteRepository;
        this.ruoloRepository = ruoloRepository;
    }

    public List<UtenteDTO> findAll() {
        return utenteRepository.findAll()
                .stream()
                .map(utente -> mapToDTO(utente, new UtenteDTO()))
                .collect(Collectors.toList());
    }

    public UtenteDTO get(final Long id) {
        return utenteRepository.findById(id)
                .map(utente -> mapToDTO(utente, new UtenteDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    
    public UtenteDTO getByUsername(final String username) {
       
    	Utente findByUsername = utenteRepository.findByUsername(username);
    	return mapToDTO(findByUsername, new UtenteDTO());
    	
    }

    public Long create(final UtenteDTO utenteDTO) {
        final Utente utente = new Utente();
        mapToEntity(utenteDTO, utente);
        return utenteRepository.save(utente).getId();
    }

    public void update(final Long id, final UtenteDTO utenteDTO) {
        final Utente utente = utenteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(utenteDTO, utente);
        utenteRepository.save(utente);
    }

    public void delete(final Long id) {
        utenteRepository.deleteById(id);
    }

    private UtenteDTO mapToDTO(final Utente utente,  UtenteDTO utenteDTO) {
        if(utente!=null) {
        	utenteDTO.setId(utente.getId());
            utenteDTO.setNome(utente.getNome());
            utenteDTO.setCognome(utente.getCognome());
            utenteDTO.setUsername(utente.getUsername());
            if(utente.getRole()!=null) {
            	Ruolo role = utente.getRole();
            	  utenteDTO.setRole(role.getId());
            	  Optional<Ruolo> findById = ruoloRepository.findById(utenteDTO.getRole());
            	  if(findById.isPresent())
            	  utenteDTO.setRoleName(findById.get().getNome());
            }
        }
        else {
        	utenteDTO=null;
        }
    	
//        utenteDTO.setRole(utente.getRole() == null ? null : utente.getRole().getId());
//        //utenteDTO.setRoleName(utente.getRole() == null ? null : utente.getRole().getNome());
        return utenteDTO;
    }

    private Utente mapToEntity(final UtenteDTO utenteDTO, final Utente utente) {
        utente.setNome(utenteDTO.getNome());
        utente.setCognome(utenteDTO.getCognome());
        utente.setUsername(utenteDTO.getUsername());
        if (utenteDTO.getRole() != null && (utente.getRole() == null || !utente.getRole().getId().equals(utenteDTO.getRole()))) {
            final Ruolo role = ruoloRepository.findById(utenteDTO.getRole())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "role not found"));
            utente.setRole(role);
        }
        return utente;
    }

}
