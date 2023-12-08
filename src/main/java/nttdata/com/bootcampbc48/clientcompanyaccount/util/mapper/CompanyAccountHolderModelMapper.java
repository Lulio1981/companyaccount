package nttdata.com.bootcampbc48.clientcompanyaccount.util.mapper;

import nttdata.com.bootcampbc48.clientcompanyaccount.dto.*;
import nttdata.com.bootcampbc48.clientcompanyaccount.entity.Holder;
import org.modelmapper.ModelMapper;

import java.util.Date;

public class CompanyAccountHolderModelMapper {

    private final ModelMapper mapper = new ModelMapper();

    private static CompanyAccountHolderModelMapper instance;

    private CompanyAccountHolderModelMapper() {
    }

    public static CompanyAccountHolderModelMapper singleInstance() {
        if (instance == null) {
            instance = new CompanyAccountHolderModelMapper();
        }
        return instance;
    }

    //MAPPERS BEGIN
    public Holder reverseMapCreateWithDate(CreateAccountCompanyHolderDto createDto) {
        Holder personalClient = mapper.map(createDto, Holder.class);

        personalClient.setInsertionDate(new Date());
        personalClient.setRegistrationStatus((short) 1);

        return personalClient;
    }


    public Holder reverseMapUpdate(Holder accountType, UpdateAccountCompanyHolderDto updateDto) {

        accountType.setDocumentNumber(updateDto.getDocumentNumber());
        accountType.setName(updateDto.getName());
        accountType.setLastName(updateDto.getLastName());
        accountType.setAge(updateDto.getAge());
        accountType.setAddress(updateDto.getAddress());
        accountType.setPhoneNumber(updateDto.getPhoneNumber());
        accountType.setMobilePhone(updateDto.getMobilePhone());
        accountType.setAccountNumber(updateDto.getAccountNumber());

        return accountType;
    }

    public Holder reverseMapDelete(Holder personalClient, DeleteAccountCompanyHolderDto deleteDto) {

        personalClient.setRegistrationStatus((short) 0);

        return personalClient;
    }

}