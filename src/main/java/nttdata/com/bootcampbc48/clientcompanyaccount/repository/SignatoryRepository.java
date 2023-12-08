package nttdata.com.bootcampbc48.clientcompanyaccount.repository;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import nttdata.com.bootcampbc48.clientcompanyaccount.entity.CompanyAccount;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SignatoryRepository extends RxJava3CrudRepository<CompanyAccount, String> {

    public Maybe<CompanyAccount> findById(String id);

    public Maybe<CompanyAccount> findByAccountNumberAndRegistrationStatus(String accountNumber, short registrationStatus);

    public Flowable<CompanyAccount> findByIdClientAndRegistrationStatus(String idClient, short registrationStatus);

}