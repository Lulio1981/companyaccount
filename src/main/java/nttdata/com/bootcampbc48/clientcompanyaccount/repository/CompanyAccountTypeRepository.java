package nttdata.com.bootcampbc48.clientcompanyaccount.repository;

import io.reactivex.rxjava3.core.Maybe;
import nttdata.com.bootcampbc48.clientcompanyaccount.entity.CompanyAccountType;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyAccountTypeRepository extends RxJava3CrudRepository<CompanyAccountType, String> {

    public Maybe<CompanyAccountType> findById(String id);

    public Maybe<CompanyAccountType> findByAbbreviation(String abbreviation);
}