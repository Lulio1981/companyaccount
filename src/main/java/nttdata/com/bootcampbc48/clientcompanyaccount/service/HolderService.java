package nttdata.com.bootcampbc48.clientcompanyaccount.service;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import nttdata.com.bootcampbc48.clientcompanyaccount.dto.*;
import nttdata.com.bootcampbc48.clientcompanyaccount.entity.Holder;

public interface HolderService {
    public Single<Holder> create(CreateAccountCompanyHolderDto createAccountCompanyHolderDto);

    public Flowable<Holder> findAll();

    public Single<Holder> findByDocumentNumberAndRegistrationStatus(String documentNumber, short registrationStatus);

    public Single<Holder> findByAccountNumberAndRegistrationStatus(String accountNumber, short registrationStatus);

    public Single<Holder> update(UpdateAccountCompanyHolderDto updateAccountCompanyHolderDto);

    public Single<Holder> delete(DeleteAccountCompanyHolderDto deleteAccountCompanyHolderDto);


}