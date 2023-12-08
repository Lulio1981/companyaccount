package nttdata.com.bootcampbc48.clientcompanyaccount.repository;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import nttdata.com.bootcampbc48.clientcompanyaccount.entity.Holder;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HolderRepository extends RxJava3CrudRepository<Holder, String> {

    public Maybe<Holder> findByAccountNumberAndRegistrationStatus(String accountNumber, short registrationStatus);

    public Maybe<Holder> findByDocumentNumberAndRegistrationStatus(String documentNumber, short registrationStatus);

}