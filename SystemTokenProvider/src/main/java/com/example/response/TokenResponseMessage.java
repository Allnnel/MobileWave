package com.example.response;


import com.example.model.DTO.SystemTokenDTO;
import com.example.model.SystemToken;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenResponseMessage extends ResponseMessage{

    @JsonProperty("systemToken")
    private  SystemTokenDTO systemTokenDTO;

    public TokenResponseMessage(String status, String message, String code, SystemToken systemToken) {
        super(status, message, code);
        this.systemTokenDTO = new SystemTokenDTO(systemToken);
    }

    public SystemTokenDTO getSystemTokenDTO() {
        return systemTokenDTO;
    }

    public void setSystemTokenDTO(SystemTokenDTO systemTokenDTO) {
        this.systemTokenDTO = systemTokenDTO;
    }
}
