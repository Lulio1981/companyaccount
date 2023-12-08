package nttdata.com.bootcampbc48.clientcompanyaccount.controller;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import nttdata.com.bootcampbc48.clientcompanyaccount.dto.CreateAccountTypeCompanyDto;
import nttdata.com.bootcampbc48.clientcompanyaccount.dto.DeleteAccountTypeCompanyDto;
import nttdata.com.bootcampbc48.clientcompanyaccount.dto.UpdateAccountTypeCompanyDto;
import nttdata.com.bootcampbc48.clientcompanyaccount.entity.CompanyAccountType;
import nttdata.com.bootcampbc48.clientcompanyaccount.service.impl.CompanyAccountTypeServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("client/company/accounttype")
@Tag(name = "CompanyAccount Type information", description = "Manage account types maintenance")
@CrossOrigin(value = {"*"})
@RequiredArgsConstructor
public class CompanyAccountTypeController {

    public final CompanyAccountTypeServiceImpl service;

    @GetMapping("/{abbreviation}")
    public Single<ResponseEntity<CompanyAccountType>> findByAbbreviation(@PathVariable String abbreviation) {
        return service.findByAbbreviation(abbreviation).map(client -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(client));
    }

    @GetMapping("/find/{id}")
    public Single<ResponseEntity<CompanyAccountType>> findById(@PathVariable String id) {

        return service.findById(id).map(client -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(client));
    }

    @GetMapping
    public ResponseEntity<Flowable<CompanyAccountType>> findAll() {
        Flowable<CompanyAccountType> flowable = service.findAll();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(flowable);
    }

    @PostMapping
    public Single<ResponseEntity<CompanyAccountType>> create(@RequestBody CreateAccountTypeCompanyDto createAccountTypeCompanyDto) {
        return service.create(createAccountTypeCompanyDto).map(p -> ResponseEntity
                .created(URI.create("/client/company/accounttype".concat(p.get_id())))
                .contentType(MediaType.APPLICATION_JSON)
                .body(p)
        );
    }


    @PutMapping
    public Single<ResponseEntity<CompanyAccountType>> update(@RequestBody UpdateAccountTypeCompanyDto updateAccountTypeCompanyDto) {
        return service.update(updateAccountTypeCompanyDto)
                .map(p -> ResponseEntity.created(URI.create("/client/company/accounttype"
                                .concat(p.get_id())
                        ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p));
    }

    @DeleteMapping
    public Single<ResponseEntity<CompanyAccountType>> delete(@RequestBody DeleteAccountTypeCompanyDto deleteAccountTypeCompanyDto) {
        return service.delete(deleteAccountTypeCompanyDto)
                .map(p -> ResponseEntity.created(URI.create("/client/company/accounttype"
                                .concat(p.get_id())
                        ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p));
    }
}