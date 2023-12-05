package nttdata.com.bootcampbc48.clientcompanyaccount.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CreateAccountCompanyHolderDto {

    private String documentNumber;
    private String name;
    private String lastName;
    private Integer age;
    private Integer address;
    private String phoneNumber;
    private String mobilePhone;
    private String accountNumber;
    private Date insertionDate;
    private String insertionUser;
    private String insertionTerminal;

}