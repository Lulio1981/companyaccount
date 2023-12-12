package nttdata.com.bootcampbc48.clientcompanyaccount.service.impl;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import nttdata.com.bootcampbc48.clientcompanyaccount.dto.*;
import nttdata.com.bootcampbc48.clientcompanyaccount.entity.HolderSignatory;
import nttdata.com.bootcampbc48.clientcompanyaccount.repository.HolderSignatoryRepository;
import nttdata.com.bootcampbc48.clientcompanyaccount.service.HolderSignatoryService;
import nttdata.com.bootcampbc48.clientcompanyaccount.util.handler.exceptions.BadRequestException;
import nttdata.com.bootcampbc48.clientcompanyaccount.util.mapper.HolderSignatoryModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class HolderSignatoryServiceImpl implements HolderSignatoryService {

    static HolderSignatoryModelMapper modelMapper = HolderSignatoryModelMapper.singleInstance();
    public final HolderSignatoryRepository repository;

    @Override
    public Flowable<HolderSignatory> findAll() {
        return repository.findAll()
                .switchIfEmpty(Flowable.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to get an item.",
                        "The personal clients dont exist.",
                        getClass(),
                        "getByDocumentNumber.switchIfEmpty"
                )));
    }


    @Override
    public Single<HolderSignatory> findByDocumentNumberAndRegistrationStatus(String documentNumber, short registrationStatus) {
        return repository.findByDocumentNumberAndRegistrationStatus(documentNumber, registrationStatus)
                .switchIfEmpty(Maybe.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to get an item.",
                        "The personal client with abbreviation " + documentNumber + " does not exists.",
                        getClass(),
                        "getByDocumentNumber.switchIfEmpty"
                )))
                .cast(HolderSignatory.class).toSingle();

    }

    @Override
    public Flowable<HolderSignatory> findByAccountNumberAndRegistrationStatusAndType(String accountNumber, short registrationStatus, String type) {
        return repository.findByAccountNumberAndRegistrationStatusAndType(accountNumber, registrationStatus, type)
                .switchIfEmpty(Flowable.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to get an item.",
                        "The client with account number  " + accountNumber + " does not exists.",
                        getClass(),
                        "getByDocumentNumber.switchIfEmpty"
                )))
                .cast(HolderSignatory.class);
    }

    @Override
    public Single<HolderSignatory> create(CreateHolderSignatoryDto createAccountCompanyHolderDto) {

        return repository.findById(createAccountCompanyHolderDto.getAccountNumber())
                .map(p -> {
                    throw new BadRequestException(
                            "DocumentNumber",
                            "[save] The document number " + createAccountCompanyHolderDto.getAccountNumber() + " is already in use.",
                            "An error occurred while trying to create an item.",
                            getClass(),
                            "save"
                    );
                })
                .switchIfEmpty(repository.save(modelMapper.reverseMapCreateWithDate(createAccountCompanyHolderDto)))
                .doOnError(e -> Single.error(new BadRequestException(
                        "ERROR",
                        "An error occurred while trying to create an item.",
                        e.getMessage(),
                        getClass(),
                        "save.onErrorResume"
                )))
                .cast(HolderSignatory.class);
    }


    @Override
    public Single<HolderSignatory> update(UpdateHolderSignatoryDto updateAccountCompanyHolderDto) {

        return repository.findByDocumentNumberAndRegistrationStatus(updateAccountCompanyHolderDto.getDocumentNumber(), (short) 1)
                .switchIfEmpty(Single.error(new Exception("An item with the id number " + updateAccountCompanyHolderDto.getDocumentNumber() + " was not found. >> switchIfEmpty")))
                .flatMap(p -> repository.save(modelMapper.reverseMapUpdate(p, updateAccountCompanyHolderDto)))
                .doOnError(e -> Single.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to update an item.",
                        e.getMessage(),
                        getClass(),
                        "update.onErrorResume"
                ))).cast(HolderSignatory.class);
    }

    @Override
    public Single<HolderSignatory> delete(DeleteHolderSignatoryDto deleteAccountCompanyHolderDto) {

        return repository.findById(deleteAccountCompanyHolderDto.getId())
                .switchIfEmpty(Single.error(new Exception("An item with the id number " + deleteAccountCompanyHolderDto.getId() + " was not found. >> switchIfEmpty")))
                .flatMap(p -> repository.save(modelMapper.reverseMapDelete(p, deleteAccountCompanyHolderDto)))
                .doOnError(e -> Single.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to delete an item.",
                        e.getMessage(),
                        getClass(),
                        "update.onErrorResume"
                )));
    }
}