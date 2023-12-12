package nttdata.com.bootcampbc48.clientcompanyaccount.util.mapper;

import nttdata.com.bootcampbc48.clientcompanyaccount.dto.*;
import nttdata.com.bootcampbc48.clientcompanyaccount.entity.HolderSignatory;
import org.modelmapper.ModelMapper;

import java.util.Date;

public class HolderSignatoryModelMapper {

    private final ModelMapper mapper = new ModelMapper();

    private static HolderSignatoryModelMapper instance;

    private HolderSignatoryModelMapper() {
    }

    public static HolderSignatoryModelMapper singleInstance() {
        if (instance == null) {
            instance = new HolderSignatoryModelMapper();
        }
        return instance;
    }

    //MAPPERS BEGIN
    public HolderSignatory reverseMapCreateWithDate(CreateHolderSignatoryDto createDto) {
        HolderSignatory personalClient = mapper.map(createDto, HolderSignatory.class);

        personalClient.setInsertionDate(new Date());
        personalClient.setRegistrationStatus((short) 1);

        return personalClient;
    }


    public HolderSignatory reverseMapUpdate(HolderSignatory accountType, UpdateHolderSignatoryDto updateDto) {

        accountType.setDocumentNumber(updateDto.getDocumentNumber());
        accountType.setName(updateDto.getName());
        accountType.setLastName(updateDto.getLastName());
        accountType.setAge(updateDto.getAge());
        accountType.setAddress(updateDto.getAddress());
        accountType.setPhoneNumber(updateDto.getPhoneNumber());
        accountType.setMobilePhone(updateDto.getMobilePhone());
        accountType.setType(updateDto.getType());

        return accountType;
    }

    public HolderSignatory reverseMapDelete(HolderSignatory personalClient, DeleteHolderSignatoryDto deleteDto) {

        personalClient.setRegistrationStatus((short) 0);

        return personalClient;
    }

}