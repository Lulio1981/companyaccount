package nttdata.com.bootcampbc48.clientcompanyaccount.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CreateAccountCompanyDto {

    private String idClient;
    private String accountNumber;
    private String idAccountType;
    private short registrationStatus;
    private Date insertionDate;
    private String insertionUser;
    private String insertionTerminal;

}