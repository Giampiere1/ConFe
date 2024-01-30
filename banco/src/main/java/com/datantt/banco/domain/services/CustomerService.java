package com.datantt.banco.domain.services;

import java.util.List;

import com.datantt.banco.domain.dtos.CustomerDTO;

public interface CustomerService {

    List<CustomerDTO> getList();

    CustomerDTO getDetail(String id);

    Boolean create(CustomerDTO customer);

    Boolean update(CustomerDTO customer);

    Boolean delete(String id);

}
