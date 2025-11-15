package com.example.Kuber.service;

import com.example.Kuber.model.Client;
import com.example.Kuber.repository.ClientRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ResponseEntity<String> add( Client client){
        if(client.getName()==null || client.getEmail()==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name & email are required.");
        }
        clientRepository.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).body("Client Added.");
    }

    public Object getall(Integer page , Integer size){
        if (page != null && size != null) {
            Pageable pageable = PageRequest.of(page, size);
            return clientRepository.findAll(pageable);
        }
        else {
            return clientRepository.findAll();
        }
    }

    public ResponseEntity<String> edit(Long id, Client updatedClient){
        return clientRepository.findById(id)
                .map(client -> {
                    client.setName(updatedClient.getName());
                    client.setCompanyName(updatedClient.getCompanyName());
                    client.setEmail(updatedClient.getEmail());
                    client.setMobileNumber(updatedClient.getMobileNumber());
                    client.setDate(updatedClient.getDate());
                    client.setCurrency(updatedClient.getCurrency());
                    client.setBillingMethod(updatedClient.getBillingMethod());
                    client.setImage(updatedClient.getImage());

                    clientRepository.save(client);
                    return ResponseEntity.ok("Client Details edited Successfully.");
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<String> delete(Long id){
        return clientRepository.findById(id)
                .map(client -> {
                    clientRepository.delete(client);
                    return ResponseEntity.ok("Client deleted Successfully.");
                }).orElse(ResponseEntity.notFound().build());
    }
//specification logic
    public Page<Client> search(String value, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(
                page != null ? page : 0,
                size != null ? size : 10
        );

        return clientRepository.findAll((root, query, cb) -> {
            if (value == null || value.trim().isEmpty()) {
                return cb.conjunction(); // returns all records
            }

            String lowerValue = "%" + value.toLowerCase() + "%";
            Predicate predicate = cb.disjunction();

            for (Field field : Client.class.getDeclaredFields()) {
                if (field.getType().equals(String.class)) {
                    predicate = cb.or(predicate,
                            cb.like(cb.lower(root.get(field.getName())), lowerValue));
                }
            }
                predicate = cb.or(predicate,
                        cb.like(root.get("date").as(String.class), "%" + value + "%"));


                return predicate;
        }, pageable);
    }


}
