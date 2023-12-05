package nttdata.com.bootcampbc48.clientcompanyaccount.controller;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import nttdata.com.bootcampbc48.clientcompanyaccount.dto.CreateAccountCompanyDto;
import nttdata.com.bootcampbc48.clientcompanyaccount.dto.DeleteAccountCompanyDto;
import nttdata.com.bootcampbc48.clientcompanyaccount.dto.UpdateAccountCompanyDto;
import nttdata.com.bootcampbc48.clientcompanyaccount.entity.CompanyAccount;
import nttdata.com.bootcampbc48.clientcompanyaccount.service.impl.CompanyAccountServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("client/company/account")
@Tag(name = "Maintenance company accounts information", description = "Manage company clients account")
@CrossOrigin(value = {"*"})
@RequiredArgsConstructor
public class CompanyAccountController {

    public final CompanyAccountServiceImpl service;

    @GetMapping("/{idClient}/{registrationStatus}")
    public ResponseEntity<Flowable<CompanyAccount>> findByIdClient(@PathVariable String idClient, @PathVariable short registrationStatus) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findByIdClientAndRegistrationStatus(idClient, registrationStatus));
    }

    @GetMapping("/find/{id}")
    public Single<ResponseEntity<CompanyAccount>> findById(@PathVariable String id) {

        return service.findById(id).map(client -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(client));
    }

    @GetMapping
    public ResponseEntity<Flowable<CompanyAccount>> findAll() {
        Flowable<CompanyAccount> flowable = service.findAll();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(flowable);
    }

    @PostMapping
    public Single<ResponseEntity<CompanyAccount>> create(@RequestBody CreateAccountCompanyDto createAccountCompanyDto) {
        return service.create(createAccountCompanyDto).map(p -> ResponseEntity
                .created(URI.create("/client/company/".concat(p.get_id())))
                .contentType(MediaType.APPLICATION_JSON)
                .body(p)
        );
    }


    @PutMapping
    public Single<ResponseEntity<CompanyAccount>> update(@RequestBody UpdateAccountCompanyDto updateAccountCompanyDto) {
        return service.update(updateAccountCompanyDto)
                .map(p -> ResponseEntity.created(URI.create("/client/company/account"
                                .concat(p.get_id())
                        ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p));
    }

    @DeleteMapping
    public Single<ResponseEntity<CompanyAccount>> delete(@RequestBody DeleteAccountCompanyDto deleteAccountCompanyDto) {
        return service.delete(deleteAccountCompanyDto)
                .map(p -> ResponseEntity.created(URI.create("/client/company/account"
                                .concat(p.get_id())
                        ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p));
    }
}