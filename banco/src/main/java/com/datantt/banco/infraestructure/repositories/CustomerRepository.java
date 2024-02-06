package com.datantt.banco.infraestructure.repositories;



import com.datantt.banco.domain.dtos.CustomerDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<CustomerDTO,String> {

    //Observable<List<CustomerDTO>> getList();

  /*  Document getDetail(String id);

    Boolean create(CustomerDTO customer);

    Boolean update(CustomerDTO customer);

    Boolean delete(String id);
*/
}
