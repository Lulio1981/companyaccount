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


    public HolderSignatory reverseMapUpdate(HolderSignatory holderSignatory, UpdateHolderSignatoryDto updateDto) {

        holderSignatory.setDocumentNumber(updateDto.getDocumentNumber());
        holderSignatory.setName(updateDto.getName());
        holderSignatory.setLastName(updateDto.getLastName());
        holderSignatory.setAge(updateDto.getAge());
        holderSignatory.setAddress(updateDto.getAddress());
        holderSignatory.setPhoneNumber(updateDto.getPhoneNumber());
        holderSignatory.setMobilePhone(updateDto.getMobilePhone());
        holderSignatory.setType(updateDto.getType());

        return holderSignatory;
    }

    public HolderSignatory reverseMapDelete(HolderSignatory personalClient, DeleteHolderSignatoryDto deleteDto) {

        personalClient.setRegistrationStatus((short) 0);

        return personalClient;
    }

}