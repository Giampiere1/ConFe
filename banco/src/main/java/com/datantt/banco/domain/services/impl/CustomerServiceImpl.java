package com.datantt.banco.domain.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import com.datantt.banco.domain.util.Constantes;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datantt.banco.domain.dtos.CustomerDTO;
import com.datantt.banco.domain.services.CustomerService;
import com.datantt.banco.infraestructure.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Observable<List<CustomerDTO>> getList() {
        return Observable.defer(() -> Observable.just(customerRepository.findAll()));
    }


    @Override
    public Single<CustomerDTO> getDetail(String id) {
        return Single.create(singleSubscriber -> {
            Optional<CustomerDTO> customerDto = customerRepository.findById(id);
            if (!customerDto.isPresent())
                singleSubscriber.onError(new Exception());
            else {
                CustomerDTO bookResponse = customerDto.get();
                singleSubscriber.onSuccess(bookResponse);
            }
        });
    }




    @Override
    public Single<String> create(CustomerDTO customer) {
        return Single.create(singleSubscriber -> {
            Optional<CustomerDTO> customerDTO = customerRepository.findById(customer.getNames());
            boolean validate = validateCustomerType.test(customer.getCustomerTypeCode());
            if (!validate || customerDTO.isPresent()) {
                singleSubscriber.onError(new Error());
            }
            else {
                String customerDtoId = customerRepository.save(customer).getId();
                singleSubscriber.onSuccess(customerDtoId);
            }
        });
    }
    @Override
    public Completable update(CustomerDTO customer) {
        return Completable.create(completableSubscriber -> {
            Optional<CustomerDTO> customerDTO = customerRepository.findById(customer.getId());
            boolean validate = validateCustomerType.test(customer.getCustomerTypeCode());
            if (!customerDTO.isPresent()||!validate )
                completableSubscriber.onError(new Exception());
            else {

                    CustomerDTO customerUpdate = customerDTO.get();
                    customerUpdate.setAddress(customer.getAddress());
                    customerUpdate.setNames(customer.getNames());
                    customerUpdate.setEmail(customer.getEmail());
                    customerUpdate.setLastname(customer.getLastname());
                    customerUpdate.setTelephone(customer.getTelephone());
                    customerUpdate.setCustomerTypeCode(customer.getCustomerTypeCode());
                    customerRepository.save(customerUpdate);
                    completableSubscriber.onComplete();


            }
        });
    }


    @Override

    public Completable delete(String id) {
        return Completable.create(completableSubscriber -> {
            Optional<CustomerDTO> customerDTO = customerRepository.findById(id);
            if (!customerDTO.isPresent())
                completableSubscriber.onError(new Exception());
            else {
                customerRepository.delete(customerDTO.get());
                completableSubscriber.onComplete();
            }
        });
    }
    Predicate<String> validateCustomerType = x -> x.equalsIgnoreCase(Constantes.TIPOCLIENTE_PERSONAL)
            || x.equalsIgnoreCase(Constantes.TIPOCLIENTE_EMPRESARIAL)
            || x.equalsIgnoreCase(Constantes.TIPOCLIENTE_PERSONALVIP)
            || x.equalsIgnoreCase(Constantes.TIPOCLIENTE_EMPRESARIALPYME);
}

    /*






}*/
