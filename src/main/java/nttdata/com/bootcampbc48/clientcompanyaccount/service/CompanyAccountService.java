package nttdata.com.bootcampbc48.clientcompanyaccount.service;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import nttdata.com.bootcampbc48.clientcompanyaccount.dto.CreateAccountCompanyDto;
import nttdata.com.bootcampbc48.clientcompanyaccount.dto.DeleteAccountCompanyDto;
import nttdata.com.bootcampbc48.clientcompanyaccount.dto.UpdateAccountCompanyDto;
import nttdata.com.bootcampbc48.clientcompanyaccount.entity.CompanyAccount;

public interface CompanyAccountService {
    public Single<CompanyAccount> create(CreateAccountCompanyDto createAccountCompanyDto);

    public Flowable<CompanyAccount> findAll();

    public Single<CompanyAccount> findById(String id);

    public Flowable<CompanyAccount> findByIdClientAndRegistrationStatus(String idClient, short registrationStatus);

    public Single<CompanyAccount> update(UpdateAccountCompanyDto updateAccountCompanyDto);

    public Single<CompanyAccount> delete(DeleteAccountCompanyDto deleteAccountCompanyDto);


}