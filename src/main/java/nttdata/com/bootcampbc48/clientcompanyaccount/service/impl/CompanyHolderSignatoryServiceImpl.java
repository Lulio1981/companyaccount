package nttdata.com.bootcampbc48.clientcompanyaccount.service.impl;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import nttdata.com.bootcampbc48.clientcompanyaccount.dto.*;
import nttdata.com.bootcampbc48.clientcompanyaccount.entity.CompanyHolderSignatory;
import nttdata.com.bootcampbc48.clientcompanyaccount.entity.HolderSignatory;
import nttdata.com.bootcampbc48.clientcompanyaccount.repository.CompanyHolderSignatoryRepository;
import nttdata.com.bootcampbc48.clientcompanyaccount.service.CompanyHolderSignatoryService;
import nttdata.com.bootcampbc48.clientcompanyaccount.service.HolderSignatoryService;
import nttdata.com.bootcampbc48.clientcompanyaccount.util.handler.exceptions.BadRequestException;
import nttdata.com.bootcampbc48.clientcompanyaccount.util.mapper.CompanyHolderSignatoryModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CompanyHolderSignatoryServiceImpl implements CompanyHolderSignatoryService {

    static CompanyHolderSignatoryModelMapper modelMapper = CompanyHolderSignatoryModelMapper.singleInstance();

    public final CompanyHolderSignatoryRepository repository;

    @Override
    public Flowable<CompanyHolderSignatory> findAll() {
        return repository.findAll()
                .switchIfEmpty(Flowable.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to get an item.",
                        "The personal clients don't exist.",
                        getClass(),
                        "getByDocumentNumber.switchIfEmpty"
                )));
    }

    @Override
    public Single<CompanyHolderSignatory> findByDocumentNumberAndRegistrationStatus(String documentNumber, short registrationStatus) {
        return repository.findByDocumentNumberAndRegistrationStatus(documentNumber, registrationStatus)
                .switchIfEmpty(Maybe.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to get an item.",
                        "The personal client with abbreviation " + documentNumber + " does not exists.",
                        getClass(),
                        "getByDocumentNumber.switchIfEmpty"
                )))
                .cast(CompanyHolderSignatory.class).toSingle();

    }

    @Override
    public Flowable<CompanyHolderSignatory> findByAccountNumberAndRegistrationStatusAndType(String accountNumber, short registrationStatus, String type) {
        return repository.findByAccountNumberAndRegistrationStatusAndType(accountNumber, registrationStatus, type)
                .switchIfEmpty(Flowable.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to get an item.",
                        "The client with account number  " + accountNumber + " does not exists.",
                        getClass(),
                        "getByDocumentNumber.switchIfEmpty"
                )))
                .cast(CompanyHolderSignatory.class);
    }

    @Override
    public Flowable<CompanyHolderSignatory> create(Flowable<CreateCompanyHolderSignatoryDto> createCompanyHolderSignatoryDto) {

        return createCompanyHolderSignatoryDto
                .map(chs -> repository.save(modelMapper.reverseMapCreateWithDate(chs)))
                .doOnError(e -> Flowable.error(new BadRequestException(
                        "ERROR",
                        "An error occurred while trying to create an item.",
                        e.getMessage(),
                        getClass(),
                        "save.onErrorResume"
                )))
                .cast(CompanyHolderSignatory.class);
    }


    @Override
    public Single<CompanyHolderSignatory> update(UpdateCompanyHolderSignatoryDto updateCompanyHolderSignatoryDto) {

        return repository.findByDocumentNumberAndRegistrationStatus(updateCompanyHolderSignatoryDto.getDocumentNumber(), (short) 1)
                .switchIfEmpty(Single.error(new Exception("An item with the id number " + updateCompanyHolderSignatoryDto.getDocumentNumber() + " was not found. >> switchIfEmpty")))
                .flatMap(p -> repository.save(modelMapper.reverseMapUpdate(p, updateCompanyHolderSignatoryDto)))
                .doOnError(e -> Single.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to update an item.",
                        e.getMessage(),
                        getClass(),
                        "update.onErrorResume"
                ))).cast(CompanyHolderSignatory.class);
    }

    @Override
    public Single<CompanyHolderSignatory> delete(DeleteCompanyHolderSignatoryDto deleteCompanyHolderSignatoryDto) {

        return repository.findById(deleteCompanyHolderSignatoryDto.getId())
                .switchIfEmpty(Single.error(new Exception("An item with the id number " + deleteCompanyHolderSignatoryDto.getId() + " was not found. >> switchIfEmpty")))
                .flatMap(p -> repository.save(modelMapper.reverseMapDelete(p, deleteCompanyHolderSignatoryDto)))
                .doOnError(e -> Single.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to delete an item.",
                        e.getMessage(),
                        getClass(),
                        "update.onErrorResume"
                )));
    }
}