package nttdata.com.bootcampbc48.clientcompanyaccount.controller;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import nttdata.com.bootcampbc48.clientcompanyaccount.dto.*;
import nttdata.com.bootcampbc48.clientcompanyaccount.entity.HolderSignatory;
import nttdata.com.bootcampbc48.clientcompanyaccount.service.impl.HolderSignatoryServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("client/company/holdersignatory")
@Tag(name = "HolderSignatory information", description = "Manage account Holders and Signatory maintenance")
@CrossOrigin(value = {"*"})
@RequiredArgsConstructor
public class HolderSignatoryController {

    public final HolderSignatoryServiceImpl service;

    @GetMapping("/{documentNumber}/{registrationStatus}")
    public Single<ResponseEntity<HolderSignatory>> findByDocumentNumberAndRegistrationStatus(@PathVariable String documentNumber, @PathVariable short registrationStatus) {
        return service.findByDocumentNumberAndRegistrationStatus(documentNumber, registrationStatus).map(client -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(client));
    }

    @GetMapping("/{accountNumber}/{registrationStatus}/{type}")
    public ResponseEntity<Flowable<HolderSignatory>> findByAccountNumberAndRegistrationStatusAndType(@PathVariable String accountNumber, @PathVariable short registrationStatus, @PathVariable String type) {
        Flowable<HolderSignatory> flowable = service.findByAccountNumberAndRegistrationStatusAndType(accountNumber, registrationStatus, type);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(flowable);
    }

    @GetMapping
    public ResponseEntity<Flowable<HolderSignatory>> findAll() {
        Flowable<HolderSignatory> flowable = service.findAll();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(flowable);
    }

    @PostMapping
    public Single<ResponseEntity<HolderSignatory>> create(@RequestBody CreateHolderSignatoryDto createAccountCompanyHolderDto) {
        return service.create(createAccountCompanyHolderDto).map(p -> ResponseEntity
                .created(URI.create("/client/company/holdersignatory".concat(p.get_id())))
                .contentType(MediaType.APPLICATION_JSON)
                .body(p)
        );
    }


    @PutMapping
    public Single<ResponseEntity<HolderSignatory>> update(@RequestBody UpdateHolderSignatoryDto updateAccountCompanyHolderDto) {
        return service.update(updateAccountCompanyHolderDto)
                .map(p -> ResponseEntity.created(URI.create("/client/company/holdersignatory"
                                .concat(p.get_id())
                        ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p));
    }

    @DeleteMapping
    public Single<ResponseEntity<HolderSignatory>> delete(@RequestBody DeleteHolderSignatoryDto deleteAccountCompanyHolderDto) {
        return service.delete(deleteAccountCompanyHolderDto)
                .map(p -> ResponseEntity.created(URI.create("/client/company/holdersignatory"
                                .concat(p.get_id())
                        ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p));
    }
}