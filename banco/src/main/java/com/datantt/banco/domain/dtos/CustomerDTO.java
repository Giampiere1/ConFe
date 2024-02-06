package com.datantt.banco.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "customer")
public class CustomerDTO {


    @JsonProperty("id")
    public String id;

    @JsonProperty("Names")
    public String Names;

    @JsonProperty("Lastname")
    public String Lastname;

    @JsonProperty("Address")
    public String Address;

    @JsonProperty("Telephone")
    public String Telephone;

    @JsonProperty("Email")
    public String Email;

    @JsonProperty("CustomerTypeCode")
    public String CustomerTypeCode;

}
