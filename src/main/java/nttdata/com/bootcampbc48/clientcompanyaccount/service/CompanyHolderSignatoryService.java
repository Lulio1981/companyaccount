package nttdata.com.bootcampbc48.clientcompanyaccount.service;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import nttdata.com.bootcampbc48.clientcompanyaccount.dto.*;
import nttdata.com.bootcampbc48.clientcompanyaccount.entity.CompanyHolderSignatory;

public interface CompanyHolderSignatoryService {

    public Flowable<CompanyHolderSignatory> create(Flowable<CreateCompanyHolderSignatoryDto> createCompanyHolderSignatoryDto);

    public Flowable<CompanyHolderSignatory> findAll();

    public Single<CompanyHolderSignatory> findByDocumentNumberAndRegistrationStatus(String documentNumber, short registrationStatus);

    public Flowable<CompanyHolderSignatory> findByAccountNumberAndRegistrationStatusAndType(String accountNumber, short registrationStatus, String type);

    public Single<CompanyHolderSignatory> update(UpdateCompanyHolderSignatoryDto updateCompanyHolderSignatoryDto);

    public Single<CompanyHolderSignatory> delete(DeleteCompanyHolderSignatoryDto deleteCompanyHolderSignatoryDto);


}