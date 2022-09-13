package com.tenpo.Apisum.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;

import java.io.Serializable;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PercentageResponse implements Serializable {
    @JsonProperty("porcentage_sum")
    private int porcentageSum;
    public PercentageResponse(){}
    public PercentageResponse(int inc){
        this.porcentageSum = inc;
    }

    public int getPorcentageSum() {
        return porcentageSum;
    }

    public void setPorcentageSum(int porcentageSum) {
        this.porcentageSum = porcentageSum;
    }

    @Override
    public String toString(){
        return new Gson().toJson(this, PercentageResponse.class);
    }
}
