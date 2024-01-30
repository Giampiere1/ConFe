package com.datantt.banco.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    @JsonProperty("_id")
    public String _id;

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
