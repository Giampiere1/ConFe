package com.datantt.banco.infraestructure.repositories;

import java.util.List;

import org.bson.Document;

import com.datantt.banco.domain.dtos.CustomerDTO;

public interface CustomerRepository {

    List<Document> getList();

    Document getDetail(String id);

    Boolean create(CustomerDTO customer);

    Boolean update(CustomerDTO customer);

    Boolean delete(String id);

}
