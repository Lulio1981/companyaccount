package nttdata.com.bootcampbc48.clientcompanyaccount.service.impl;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import nttdata.com.bootcampbc48.clientcompanyaccount.dto.*;
import nttdata.com.bootcampbc48.clientcompanyaccount.entity.Holder;
import nttdata.com.bootcampbc48.clientcompanyaccount.repository.HolderRepository;
import nttdata.com.bootcampbc48.clientcompanyaccount.service.HolderService;
import nttdata.com.bootcampbc48.clientcompanyaccount.util.handler.exceptions.BadRequestException;
import nttdata.com.bootcampbc48.clientcompanyaccount.util.mapper.CompanyAccountHolderModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class HolderServiceImpl implements HolderService {

    static CompanyAccountHolderModelMapper modelMapper = CompanyAccountHolderModelMapper.singleInstance();
    public final HolderRepository repository;

    @Override
    public Flowable<Holder> findAll() {
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
    public Single<Holder> findByDocumentNumberAndRegistrationStatus(String documentNumber, short registrationStatus) {
        return repository.findByDocumentNumberAndRegistrationStatus(documentNumber, registrationStatus)
                .switchIfEmpty(Maybe.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to get an item.",
                        "The personal client with abbreviation " + documentNumber + " does not exists.",
                        getClass(),
                        "getByDocumentNumber.switchIfEmpty"
                )))
                .cast(Holder.class).toSingle();

    }

    @Override
    public Single<Holder> findByAccountNumberAndRegistrationStatus(String accountNumber, short registrationStatus) {
        return repository.findByDocumentNumberAndRegistrationStatus(accountNumber, registrationStatus)
                .switchIfEmpty(Maybe.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to get an item.",
                        "The client with account number  " + accountNumber + " does not exists.",
                        getClass(),
                        "getByDocumentNumber.switchIfEmpty"
                )))
                .cast(Holder.class).toSingle();

    }

    @Override
    public Single<Holder> create(CreateAccountCompanyHolderDto createAccountCompanyHolderDto) {

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
                .cast(Holder.class);
    }


    @Override
    public Single<Holder> update(UpdateAccountCompanyHolderDto updateAccountCompanyHolderDto) {

        return repository.findByDocumentNumberAndRegistrationStatus(updateAccountCompanyHolderDto.getDocumentNumber(), (short) 1)
                .switchIfEmpty(Single.error(new Exception("An item with the id number " + updateAccountCompanyHolderDto.getDocumentNumber() + " was not found. >> switchIfEmpty")))
                .flatMap(p -> repository.save(modelMapper.reverseMapUpdate(p, updateAccountCompanyHolderDto)))
                .doOnError(e -> Single.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to update an item.",
                        e.getMessage(),
                        getClass(),
                        "update.onErrorResume"
                ))).cast(Holder.class);
    }

    @Override
    public Single<Holder> delete(DeleteAccountCompanyHolderDto deleteAccountCompanyHolderDto) {

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