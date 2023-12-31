package nttdata.com.bootcampbc48.clientcompanyaccount.service.impl;

import io.reactivex.rxjava3.core.*;
import lombok.RequiredArgsConstructor;
import nttdata.com.bootcampbc48.clientcompanyaccount.dto.CreateAccountCompanyDto;
import nttdata.com.bootcampbc48.clientcompanyaccount.dto.DeleteAccountCompanyDto;
import nttdata.com.bootcampbc48.clientcompanyaccount.dto.UpdateAccountCompanyDto;
import nttdata.com.bootcampbc48.clientcompanyaccount.entity.CompanyAccount;
import nttdata.com.bootcampbc48.clientcompanyaccount.repository.CompanyAccountRepository;
import nttdata.com.bootcampbc48.clientcompanyaccount.service.CompanyAccountService;
import nttdata.com.bootcampbc48.clientcompanyaccount.util.handler.exceptions.BadRequestException;
import nttdata.com.bootcampbc48.clientcompanyaccount.util.mapper.CompanyAccountModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class CompanyAccountServiceImpl implements CompanyAccountService {

    static CompanyAccountModelMapper modelMapper = CompanyAccountModelMapper.singleInstance().singleInstance();
    public final CompanyAccountRepository repository;

    @Override
    public Flowable<CompanyAccount> findAll() {
        return repository.findAll()
                .switchIfEmpty(Flowable.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to get an item.",
                        "The customer haven accounts.",
                        getClass(),
                        "findAll.switchIfEmpty"
                )));
    }

    @Override
    public Single<CompanyAccount> findById(String id) {
        return repository.findById(id)
                .switchIfEmpty(Maybe.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to get an item.",
                        "The personal client with id number " + id + " does not exists.",
                        getClass(),
                        "findById.switchIfEmpty"
                )))
                .cast(CompanyAccount.class).toSingle();
    }

    @Override
    public Flowable<CompanyAccount> findByIdClientAndRegistrationStatus(String idClient, short registrationStatus) {
        return repository.findByIdClientAndRegistrationStatus(idClient, registrationStatus)
                .switchIfEmpty(Flowable.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to get an item.",
                        "The personal client with document number " + idClient + " does not exists.",
                        getClass(),
                        "findByIdClient.switchIfEmpty"
                )));

    }

    @Override
    public Single<CompanyAccount> create(CreateAccountCompanyDto createAccountCompanyDto) {
        return Maybe.just(createAccountCompanyDto)
                .filter(acc -> acc.getIdAccountType().equals("6568f69613752c52feeaac69"))
                .flatMapSingle(acc -> repository.findByAccountNumberAndRegistrationStatus(
                                createAccountCompanyDto.getAccountNumber(), createAccountCompanyDto.getRegistrationStatus())
                        .flatMapSingle(account -> {
                            return Single.error(new BadRequestException(
                                    "Account",
                                    "An error occurred while trying to create an item.",
                                    "The account" + createAccountCompanyDto.getAccountNumber() + " exist.",
                                    getClass(),
                                    "save"
                            ));
                        })
                        .switchIfEmpty(repository.save(modelMapper.reverseMapCreateWithDate(createAccountCompanyDto)))
                )
                .switchIfEmpty(Single.error(new BadRequestException(
                        "switchIfEmpty",
                        "An error occurred while trying to create an item.",
                        "The account" + createAccountCompanyDto.getAccountNumber() + " must be type Current Account.",
                        getClass(),
                        "save"
                )))
                .doOnError(e -> Single.error(new BadRequestException(
                        "ERROR",
                        "An error occurred while trying to create an item.",
                        e.getMessage(),
                        getClass(),
                        "save.onErrorResume"
                ))).cast(CompanyAccount.class);
    }

    @Override
    public Single<CompanyAccount> update(UpdateAccountCompanyDto updateAccountCompanyDto) {

        return repository.findById(updateAccountCompanyDto.getId())
                .switchIfEmpty(Single.error(new Exception("An item with the document number " + updateAccountCompanyDto.getId() + " was not found. >> switchIfEmpty")))
                .flatMap(p -> repository.save(modelMapper.reverseMapUpdate(p, updateAccountCompanyDto)))
                .doOnError(e -> Single.error(new BadRequestException(
                        "idClient",
                        "An error occurred while trying to update an item.",
                        e.getMessage(),
                        getClass(),
                        "update.onErrorResume"
                )));
    }

    @Override
    public Single<CompanyAccount> delete(DeleteAccountCompanyDto deleteAccountCompanyDto) {

        return repository.findById(deleteAccountCompanyDto.getId())
                .switchIfEmpty(Single.error(new Exception("An item with the document number " + deleteAccountCompanyDto.getId() + " was not found. >> switchIfEmpty")))
                .flatMap(p -> repository.save(modelMapper.reverseMapDelete(p, deleteAccountCompanyDto)))
                .doOnError(e -> Mono.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to delete an item.",
                        e.getMessage(),
                        getClass(),
                        "update.onErrorResume"
                )));
    }
}