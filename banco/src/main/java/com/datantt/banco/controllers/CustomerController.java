package com.datantt.banco.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.datantt.banco.domain.dtos.CustomerDTO;
import com.datantt.banco.domain.services.CustomerService;

import static java.util.Objects.isNull;

@RequestMapping("/customer")
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<?> getList() {
        try {
            System.out.println("INICIO LISTADO");
            List<CustomerDTO> response = customerService.getList();
            if (isNull(response)) {
                return ResponseEntity.noContent().build();
            }
            System.out.println("FIN LISTADO");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getDetail(@PathVariable String id) {
        try {
            System.out.println("INICIO DETALLE");
            System.out.println(id);
            CustomerDTO response = customerService.getDetail(id);
            if (isNull(response)) {
                return ResponseEntity.noContent().build();
            }
            System.out.println("FIN DETALLE");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody CustomerDTO customerDTO) {
        try {
            System.out.println("INICIO CREAR");
            Boolean response = customerService.create(customerDTO);
            System.out.println("FIN CREAR");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/")
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody CustomerDTO customerDTO) {
        try {
            System.out.println("INICIO ACTUALIZAR");
            Boolean response = customerService.update(customerDTO);
            System.out.println("FIN ACTUALIZAR");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable String id) {
        try {
            System.out.println("INICIO ELIMINAR");
            System.out.println(id);
            Boolean response = customerService.delete(id);
            System.out.println("FIN ELIMINAR");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

}
