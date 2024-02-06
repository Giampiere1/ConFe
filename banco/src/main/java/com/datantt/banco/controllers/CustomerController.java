package com.datantt.banco.controllers;

import java.util.List;


import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @GetMapping("/")
    @ResponseBody
    public Observable<ResponseEntity<List<CustomerDTO>>> getList() {
        return customerService.getList()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> log.debug("getList:: init"))
                .doOnComplete(() -> log.info("getList: completed"))
                .map(ResponseEntity::ok)
                .onErrorReturn(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }



    @GetMapping("/{id}")
    @ResponseBody
    public Single<ResponseEntity<CustomerDTO>> getDetail(@PathVariable String id) {

         return customerService.getDetail(id)
            .subscribeOn(Schedulers.io())
                 .map(ResponseEntity::ok)
                .onErrorReturn(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
       }

   @PostMapping("/")
    @ResponseBody
    public Single<ResponseEntity<String>> create(@RequestBody CustomerDTO customerDTO) {

       return customerService.create(customerDTO)
               .subscribeOn(Schedulers.io())
               .map(ResponseEntity::ok)
               .onErrorReturn(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
    @PutMapping("/")
    @ResponseBody
    public Single<ResponseEntity<CustomerDTO>> update(@RequestBody CustomerDTO customerDTO) {
        return customerService.update(customerDTO)
                .subscribeOn(Schedulers.io())
                .toSingle(() ->ResponseEntity.ok(customerDTO));
    }


    @DeleteMapping("/{id}")
    @ResponseBody
    public Single<ResponseEntity<CustomerDTO>> delete(@PathVariable String id) {
        return customerService.delete(id)
                .subscribeOn(Schedulers.io())
                .toSingle(() ->ResponseEntity.status(HttpStatus.OK).build());
    }

}
