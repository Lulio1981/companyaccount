package nttdata.com.bootcampbc48.clientcompanyaccount.dto;

import lombok.Data;

@Data
public class UpdateAccountCompanyHolderDto {

    private String documentNumber;
    private String name;
    private String lastName;
    private Integer age;
    private Integer address;
    private String phoneNumber;
    private String mobilePhone;
    private String accountNumber;

}