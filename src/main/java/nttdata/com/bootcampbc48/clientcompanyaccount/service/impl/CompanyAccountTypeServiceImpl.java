package nttdata.com.bootcampbc48.clientcompanyaccount.service.impl;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import nttdata.com.bootcampbc48.clientcompanyaccount.dto.CreateAccountTypeCompanyDto;
import nttdata.com.bootcampbc48.clientcompanyaccount.dto.DeleteAccountTypeCompanyDto;
import nttdata.com.bootcampbc48.clientcompanyaccount.dto.UpdateAccountTypeCompanyDto;
import nttdata.com.bootcampbc48.clientcompanyaccount.entity.CompanyAccountType;
import nttdata.com.bootcampbc48.clientcompanyaccount.repository.CompanyAccountTypeRepository;
import nttdata.com.bootcampbc48.clientcompanyaccount.service.CompanyAccountTypeService;
import nttdata.com.bootcampbc48.clientcompanyaccount.util.handler.exceptions.BadRequestException;
import nttdata.com.bootcampbc48.clientcompanyaccount.util.mapper.CompanyAccountTypeModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class CompanyAccountTypeServiceImpl implements CompanyAccountTypeService {

    static CompanyAccountTypeModelMapper modelMapper = CompanyAccountTypeModelMapper.singleInstance();
    public final CompanyAccountTypeRepository repository;

    @Override
    public Flowable<CompanyAccountType> findAll() {
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
    public Single<CompanyAccountType> findById(String id) {
        return repository.findById(id)
                .switchIfEmpty(Maybe.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to get an item.",
                        "The personal client with id number " + id + " does not exists.",
                        getClass(),
                        "getByDocumentNumber.switchIfEmpty"
                )))
                .cast(CompanyAccountType.class).toSingle();
    }

    @Override
    public Single<CompanyAccountType> findByAbbreviation(String abbreviation) {
        return repository.findByAbbreviation(abbreviation)

                .switchIfEmpty(Maybe.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to get an item.",
                        "The personal client with abbreviation " + abbreviation + " does not exists.",
                        getClass(),
                        "getByDocumentNumber.switchIfEmpty"
                )))
                .cast(CompanyAccountType.class).toSingle();

    }

    @Override
    public Single<CompanyAccountType> create(CreateAccountTypeCompanyDto createAccountTypeCompanyDto) {

        return repository.findById(createAccountTypeCompanyDto.getAbbreviation())
                .map(p -> {
                    throw new BadRequestException(
                            "DocumentNumber",
                            "[save] The document number " + createAccountTypeCompanyDto.getAbbreviation() + " is already in use.",
                            "An error occurred while trying to create an item.",
                            getClass(),
                            "save"
                    );
                })
                .switchIfEmpty(repository.save(modelMapper.reverseMapCreateWithDate(createAccountTypeCompanyDto)))
                .doOnError(e -> Mono.error(new BadRequestException(
                        "ERROR",
                        "An error occurred while trying to create an item.",
                        e.getMessage(),
                        getClass(),
                        "save.onErrorResume"
                )))
                .cast(CompanyAccountType.class);
    }


    @Override
    public Single<CompanyAccountType> update(UpdateAccountTypeCompanyDto updateAccountTypeCompanyDto) {

        return repository.findById(updateAccountTypeCompanyDto.getId())
                .switchIfEmpty(Single.error(new Exception("An item with the id number " + updateAccountTypeCompanyDto.getId() + " was not found. >> switchIfEmpty")))
                .flatMap(p -> repository.save(modelMapper.reverseMapUpdate(p, updateAccountTypeCompanyDto)))
                .doOnError(e -> Single.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to update an item.",
                        e.getMessage(),
                        getClass(),
                        "update.onErrorResume"
                ))).cast(CompanyAccountType.class);
    }

    @Override
    public Single<CompanyAccountType> delete(DeleteAccountTypeCompanyDto deleteAccountTypeCompanyDto) {

        return repository.findById(deleteAccountTypeCompanyDto.getId())
                .switchIfEmpty(Single.error(new Exception("An item with the id number " + deleteAccountTypeCompanyDto.getId() + " was not found. >> switchIfEmpty")))
                .flatMap(p -> repository.save(modelMapper.reverseMapDelete(p, deleteAccountTypeCompanyDto)))
                .doOnError(e -> Single.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to delete an item.",
                        e.getMessage(),
                        getClass(),
                        "update.onErrorResume"
                )));
    }
}