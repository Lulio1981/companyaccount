package nttdata.com.bootcampbc48.clientcompanyaccount.util.mapper;

import nttdata.com.bootcampbc48.clientcompanyaccount.dto.CreateAccountCompanyDto;
import nttdata.com.bootcampbc48.clientcompanyaccount.dto.DeleteAccountCompanyDto;
import nttdata.com.bootcampbc48.clientcompanyaccount.dto.UpdateAccountCompanyDto;
import nttdata.com.bootcampbc48.clientcompanyaccount.entity.CompanyAccount;
import org.modelmapper.ModelMapper;

import java.util.Date;

public class CompanyAccountModelMapper {

    private final ModelMapper mapper = new ModelMapper();

    private static CompanyAccountModelMapper instance;

    private CompanyAccountModelMapper() {
    }

    public static CompanyAccountModelMapper singleInstance() {
        if (instance == null) {
            instance = new CompanyAccountModelMapper();
        }
        return instance;
    }

    //MAPPERS BEGIN
    public CompanyAccount reverseMapCreateWithDate(CreateAccountCompanyDto createDto) {
        CompanyAccount personalClient = mapper.map(createDto, CompanyAccount.class);
        personalClient.setInsertionDate(new Date());
        personalClient.setRegistrationStatus((short) 1);
        return personalClient;
    }


    public CompanyAccount reverseMapUpdate(CompanyAccount account, UpdateAccountCompanyDto updateDto) {
        account.setAccountNumber(updateDto.getAccountNumber());
        account.setIdAccountType(updateDto.getIdAccountType());
        return account;
    }

    public CompanyAccount reverseMapDelete(CompanyAccount personalClient, DeleteAccountCompanyDto deleteDto) {

        personalClient.setRegistrationStatus((short) 0);

        return personalClient;
    }

}