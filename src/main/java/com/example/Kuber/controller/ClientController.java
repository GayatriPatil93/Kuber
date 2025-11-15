package com.example.Kuber.controller;

import com.example.Kuber.model.Client;
import com.example.Kuber.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    //Add Client
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Client client){
        return clientService.add(client);
    }

    //fetch all clients
    @GetMapping("/getall")
     public ResponseEntity<?> getall(
             @RequestParam (required = false) Integer page,
             @RequestParam (required = false) Integer size ){
        return ResponseEntity.ok(clientService.getall(page,size));
     }

    //edit Client
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody Client updatedclient){
        return clientService.edit(id,updatedclient);
    }
    //delete client
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return clientService.delete(id);
    }

    @GetMapping("/search")
    public Page<Client> search(@RequestParam String value, @RequestParam (required = false) Integer page, @RequestParam (required = false) Integer size ){
    return clientService.search(value,page,size);
    }

}
