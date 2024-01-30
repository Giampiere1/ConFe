package com.datantt.banco.domain.services.impl;

import java.util.List;
import java.util.function.Predicate;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datantt.banco.domain.dtos.CustomerDTO;
import com.datantt.banco.domain.services.CustomerService;
import com.datantt.banco.domain.util.Constantes;
import com.datantt.banco.domain.util.Util;
import com.datantt.banco.infraestructure.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<CustomerDTO> getList() {
        List<Document> documents = customerRepository.getList();
        List<CustomerDTO> result = Util.mapTo(documents, CustomerDTO.class);
        return result;
    }

    @Override
    public CustomerDTO getDetail(String id) {
        Document document = customerRepository.getDetail(id);
        CustomerDTO result;
        if (document != null) {
            result = Util.mapTo(document, CustomerDTO.class);
        } else {
            result = null;
        }
        return result;
    }

    @Override
    public Boolean create(CustomerDTO customer) {
        Boolean result;
        Boolean validate = validateCustomerType.test(customer.getCustomerTypeCode());
        if (!validate) {
            System.out.println("Error en validacion de tipo de cliente.");
            return false;
        }
        result = customerRepository.create(customer);
        return result;
    }

    @Override
    public Boolean update(CustomerDTO customer) {
        Boolean result;
        result = customerRepository.update(customer);
        return result;
    }

    @Override
    public Boolean delete(String id) {
        Boolean result = customerRepository.delete(id);
        return result;
    }

    Predicate<String> validateCustomerType = x -> x.equalsIgnoreCase(Constantes.TIPOCLIENTE_PERSONAL)
            || x.equalsIgnoreCase(Constantes.TIPOCLIENTE_EMPRESARIAL);

}
