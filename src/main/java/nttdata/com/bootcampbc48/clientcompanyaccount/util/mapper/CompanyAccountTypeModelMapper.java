package nttdata.com.bootcampbc48.clientcompanyaccount.util.mapper;

import nttdata.com.bootcampbc48.clientcompanyaccount.dto.CreateAccountTypeCompanyDto;
import nttdata.com.bootcampbc48.clientcompanyaccount.dto.DeleteAccountTypeCompanyDto;
import nttdata.com.bootcampbc48.clientcompanyaccount.dto.UpdateAccountTypeCompanyDto;
import nttdata.com.bootcampbc48.clientcompanyaccount.entity.CompanyAccountType;
import org.modelmapper.ModelMapper;

import java.util.Date;

public class CompanyAccountTypeModelMapper {

    private final ModelMapper mapper = new ModelMapper();

    private static CompanyAccountTypeModelMapper instance;

    private CompanyAccountTypeModelMapper() {
    }

    public static CompanyAccountTypeModelMapper singleInstance() {
        if (instance == null) {
            instance = new CompanyAccountTypeModelMapper();
        }
        return instance;
    }

    //MAPPERS BEGIN
    public CompanyAccountType reverseMapCreateWithDate(CreateAccountTypeCompanyDto createDto) {
        CompanyAccountType personalClient = mapper.map(createDto, CompanyAccountType.class);

        personalClient.setInsertionDate(new Date());
        personalClient.setRegistrationStatus((short) 1);

        return personalClient;
    }


    public CompanyAccountType reverseMapUpdate(CompanyAccountType accountType, UpdateAccountTypeCompanyDto updateDto) {

        accountType.setAbbreviation(updateDto.getAbbreviation());
        accountType.setDescription(updateDto.getDescription());
        accountType.setInterestRate(updateDto.getInterestRate());
        accountType.setMaintenanceCost(updateDto.getMaintenanceCost());
        accountType.setMinimumBalance(updateDto.getMinimumBalance());
        accountType.setTransactionFee(updateDto.getTransactionFee());
        accountType.setTransactionPermissionDate(updateDto.getTransactionPermissionDate());
        accountType.setTransactionsNumber(updateDto.getTransactionsNumber());

        return accountType;
    }

    public CompanyAccountType reverseMapDelete(CompanyAccountType personalClient, DeleteAccountTypeCompanyDto deleteDto) {

        personalClient.setRegistrationStatus((short) 0);

        return personalClient;
    }

}