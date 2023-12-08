package nttdata.com.bootcampbc48.clientcompanyaccount.controller;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import nttdata.com.bootcampbc48.clientcompanyaccount.dto.*;
import nttdata.com.bootcampbc48.clientcompanyaccount.entity.Holder;
import nttdata.com.bootcampbc48.clientcompanyaccount.service.impl.HolderServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("client/company/accountholder")
@Tag(name = "CompanyAccountHolder Type information", description = "Manage account Holders maintenance")
@CrossOrigin(value = {"*"})
@RequiredArgsConstructor
public class CompanyAccountHolderController {

    public final HolderServiceImpl service;

    @GetMapping("/{documentNumber}/{registrationStatus}")
    public Single<ResponseEntity<Holder>> findByDocumentNumberAndRegistrationStatus(@PathVariable String documentNumber, @PathVariable short registrationStatus) {
        return service.findByDocumentNumberAndRegistrationStatus(documentNumber, registrationStatus).map(client -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(client));
    }

    @GetMapping("/{accountNumber}/{registrationStatus}")
    public Single<ResponseEntity<Holder>> findByAccountNumberAndRegistrationStatus(@PathVariable String accountNumber, @PathVariable short registrationStatus) {
        return service.findByAccountNumberAndRegistrationStatus(accountNumber, registrationStatus).map(client -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(client));
    }

    @GetMapping
    public ResponseEntity<Flowable<Holder>> findAll() {
        Flowable<Holder> flowable = service.findAll();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(flowable);
    }

    @PostMapping
    public Single<ResponseEntity<Holder>> create(@RequestBody CreateAccountCompanyHolderDto createAccountCompanyHolderDto) {
        return service.create(createAccountCompanyHolderDto).map(p -> ResponseEntity
                .created(URI.create("/client/company/accountholder".concat(p.get_id())))
                .contentType(MediaType.APPLICATION_JSON)
                .body(p)
        );
    }


    @PutMapping
    public Single<ResponseEntity<Holder>> update(@RequestBody UpdateAccountCompanyHolderDto updateAccountCompanyHolderDto) {
        return service.update(updateAccountCompanyHolderDto)
                .map(p -> ResponseEntity.created(URI.create("/client/company/accountholder"
                                .concat(p.get_id())
                        ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p));
    }

    @DeleteMapping
    public Single<ResponseEntity<Holder>> delete(@RequestBody DeleteAccountCompanyHolderDto deleteAccountCompanyHolderDto) {
        return service.delete(deleteAccountCompanyHolderDto)
                .map(p -> ResponseEntity.created(URI.create("/client/company/accountholder"
                                .concat(p.get_id())
                        ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p));
    }
}