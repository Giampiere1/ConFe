package com.datantt.banco.infraestructure.repositories.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.datantt.banco.domain.dtos.CustomerDTO;
import com.datantt.banco.infraestructure.repositories.CustomerRepository;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    @Value("${connectionstring}")
    private String connectionString;

    @Value("${dbname}")
    private String dbname;

    @Value("${customercollection}")
    private String customercollection;

    @Override
    public List<Document> getList() {
        List<Document> result = new ArrayList<>();
        MongoClient client = MongoClients.create(connectionString);
        MongoDatabase database = client.getDatabase(dbname);
        MongoCollection<Document> collection = database.getCollection(customercollection);
        FindIterable<Document> documents = collection.find();
        for (Document document : documents) {
            result.add(document);
        }
        return result;
    }

    @Override
    public Document getDetail(String id) {
        MongoClient client = MongoClients.create(connectionString);
        MongoDatabase database = client.getDatabase(dbname);
        MongoCollection<Document> collection = database.getCollection(customercollection);
        return collection.find(new Document("_id", new ObjectId(id))).first();
    }

    @Override
    public Boolean create(CustomerDTO customer) {
        MongoClient client = MongoClients.create(connectionString);
        MongoDatabase database = client.getDatabase(dbname);
        MongoCollection<Document> collection = database.getCollection(customercollection);
        Document document = new Document("Names", customer.getNames())
                .append("Lastname", customer.getLastname())
                .append("Address", customer.getAddress())
                .append("Telephone", customer.getTelephone())
                .append("Email", customer.getEmail())
                .append("CustomerTypeCode", customer.getCustomerTypeCode());
        collection.insertOne(document);
        return true;
    }

    @Override
    public Boolean update(CustomerDTO customer) {
        MongoClient client = MongoClients.create(connectionString);
        MongoDatabase database = client.getDatabase(dbname);
        MongoCollection<Document> collection = database.getCollection(customercollection);
        Document document = new Document("$set", new Document("Names", customer.getNames())
                .append("Lastname", customer.getLastname())
                .append("Address", customer.getAddress())
                .append("Telephone", customer.getTelephone())
                .append("Email", customer.getEmail()));
        collection.updateOne(new Document("_id", new ObjectId(customer.get_id())), document);
        return true;
    }

    @Override
    public Boolean delete(String id) {
        MongoClient client = MongoClients.create(connectionString);
        MongoDatabase database = client.getDatabase(dbname);
        MongoCollection<Document> collection = database.getCollection(customercollection);
        collection.deleteOne(new Document("_id", new ObjectId(id)));
        return true;
    }

}
