package nttdata.com.bootcampbc48.clientcompanyaccount.dto;

import nttdata.com.bootcampbc48.clientcompanyaccount.entity.HolderSignatory;

import java.util.Date;
import java.util.List;

public class CompanyAccountInformationDto {

    private String idClient;
    private String accountNumber;
    private String idAccountType;
    private List<HolderSignatory> listOfHolders;
    private short registrationStatus;
    private Date insertionDate;
    private String insertionUser;
    private String insertionTerminal;
}
