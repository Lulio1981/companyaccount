package nttdata.com.bootcampbc48.clientcompanyaccount.repository;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import nttdata.com.bootcampbc48.clientcompanyaccount.entity.CompanyHolderSignatory;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;

@EnableReactiveMongoRepositories
public interface CompanyHolderSignatoryRepository extends RxJava3CrudRepository<CompanyHolderSignatory, String> {

    public Flowable<CompanyHolderSignatory> findByAccountNumberAndRegistrationStatus(String accountNumber, short registrationStatus);

    public Maybe<CompanyHolderSignatory> findByDocumentNumberAndRegistrationStatus(String documentNumber, short registrationStatus);

    public Maybe<CompanyHolderSignatory> findByDocumentNumberAndAccountNumberAndRegistrationStatus(String documentNumber, String accountNumber, short registrationStatus);

}
