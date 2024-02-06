package com.datantt.banco.domain.services;

import java.util.List;
import java.util.Optional;

import com.datantt.banco.domain.dtos.CustomerDTO;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface CustomerService {

    Observable<List<CustomerDTO>>  getList();

    Single<CustomerDTO> getDetail(String id);

    Single<String> create(CustomerDTO customer);


    Completable update(CustomerDTO customer);
    Completable delete(String id);


}
