package nttdata.com.bootcampbc48.clientcompanyaccount.service;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import nttdata.com.bootcampbc48.clientcompanyaccount.dto.*;
import nttdata.com.bootcampbc48.clientcompanyaccount.entity.HolderSignatory;

public interface HolderSignatoryService {
    public Single<HolderSignatory> create(CreateHolderSignatoryDto createAccountCompanyHolderDto);

    public Flowable<HolderSignatory> findAll();

    public Single<HolderSignatory> findByDocumentNumberAndRegistrationStatus(String documentNumber, short registrationStatus);

    public Flowable<HolderSignatory> findByAccountNumberAndRegistrationStatusAndType(String accountNumber, short registrationStatus, String type);

    public Single<HolderSignatory> update(UpdateHolderSignatoryDto updateAccountCompanyHolderDto);

    public Single<HolderSignatory> delete(DeleteHolderSignatoryDto deleteAccountCompanyHolderDto);


}