package nttdata.com.bootcampbc48.clientcompanyaccount.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CreateAccountTypeCompanyDto {

    private String description;
    private String abbreviation;
    private String isoCurrencyCode;
    private BigDecimal interestRate;
    private BigDecimal transactionFee;
    private Long transactionsNumber;
    private BigDecimal maintenanceCost;
    private BigDecimal minimumBalance;
    private Date transactionPermissionDate;
    private short registrationStatus;
    private Date insertionDate;
    private String insertionUser;
    private String insertionTerminal;

}