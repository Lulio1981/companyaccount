package nttdata.com.bootcampbc48.clientcompanyaccount.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CreateCompanyHolderSignatoryDto {

    private String accountNumber;

    private String documentNumber;

    private String type;

    private Date insertionDate;

    private String insertionUser;

    private String insertionTerminal;

}