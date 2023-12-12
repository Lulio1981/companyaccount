package nttdata.com.bootcampbc48.clientcompanyaccount.repository;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import nttdata.com.bootcampbc48.clientcompanyaccount.entity.CompanyAccount;
import nttdata.com.bootcampbc48.clientcompanyaccount.entity.HolderSignatory;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HolderSignatoryRepository extends RxJava3CrudRepository<HolderSignatory, String> {

    public Maybe<HolderSignatory> findById(String id);

    public Maybe<HolderSignatory> findByDocumentNumberAndRegistrationStatus(String documentNumber, short registrationStatus);

    public Flowable<HolderSignatory> findByAccountNumberAndRegistrationStatusAndType(String accountNumber, short registrationStatus, String type);

}
