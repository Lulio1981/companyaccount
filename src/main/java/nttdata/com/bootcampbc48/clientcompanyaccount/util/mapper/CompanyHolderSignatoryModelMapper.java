package nttdata.com.bootcampbc48.clientcompanyaccount.util.mapper;

import nttdata.com.bootcampbc48.clientcompanyaccount.dto.*;
import nttdata.com.bootcampbc48.clientcompanyaccount.entity.CompanyHolderSignatory;
import org.modelmapper.ModelMapper;

import java.util.Date;

public class CompanyHolderSignatoryModelMapper {

    private final ModelMapper mapper = new ModelMapper();

    private static CompanyHolderSignatoryModelMapper instance;

    private CompanyHolderSignatoryModelMapper() {
    }

    public static CompanyHolderSignatoryModelMapper singleInstance() {
        if (instance == null) {
            instance = new CompanyHolderSignatoryModelMapper();
        }
        return instance;
    }

    //MAPPERS BEGIN
    public CompanyHolderSignatory reverseMapCreateWithDate(CreateCompanyHolderSignatoryDto createDto) {
        CompanyHolderSignatory personalClient = mapper.map(createDto, CompanyHolderSignatory.class);

        personalClient.setInsertionDate(new Date());
        personalClient.setRegistrationStatus((short) 1);

        return personalClient;
    }


    public CompanyHolderSignatory reverseMapUpdate(CompanyHolderSignatory companyHolderSignatory, UpdateCompanyHolderSignatoryDto updateDto) {

        companyHolderSignatory.setDocumentNumber(updateDto.getAccountNumber());
        companyHolderSignatory.setType(updateDto.getType());
        companyHolderSignatory.setAccountNumber(updateDto.getAccountNumber());

        return companyHolderSignatory;
    }

    public CompanyHolderSignatory reverseMapDelete(CompanyHolderSignatory personalClient, DeleteCompanyHolderSignatoryDto deleteDto) {

        personalClient.setRegistrationStatus((short) 0);

        return personalClient;
    }

}