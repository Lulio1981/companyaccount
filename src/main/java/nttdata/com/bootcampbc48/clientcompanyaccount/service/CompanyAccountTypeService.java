package nttdata.com.bootcampbc48.clientcompanyaccount.service;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import nttdata.com.bootcampbc48.clientcompanyaccount.dto.CreateAccountTypeCompanyDto;
import nttdata.com.bootcampbc48.clientcompanyaccount.dto.DeleteAccountTypeCompanyDto;
import nttdata.com.bootcampbc48.clientcompanyaccount.dto.UpdateAccountTypeCompanyDto;
import nttdata.com.bootcampbc48.clientcompanyaccount.entity.CompanyAccountType;

public interface CompanyAccountTypeService {
    public Single<CompanyAccountType> create(CreateAccountTypeCompanyDto createAccountTypeCompanyDto);

    public Flowable<CompanyAccountType> findAll();

    public Single<CompanyAccountType> findById(String id);

    public Single<CompanyAccountType> findByAbbreviation(String abbreviation);

    public Single<CompanyAccountType> update(UpdateAccountTypeCompanyDto updateAccountTypeCompanyDto);

    public Single<CompanyAccountType> delete(DeleteAccountTypeCompanyDto deleteAccountTypeCompanyDto);


}