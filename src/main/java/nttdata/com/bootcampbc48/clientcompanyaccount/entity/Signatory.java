package nttdata.com.bootcampbc48.clientcompanyaccount.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Document
public class Signatory implements Serializable {

    private static final long serialVersionUID = -1518847114715450167L;

    @Id
    private String _id;

    private String documentNumber;

    private String name;

    private String lastName;

    private Integer age;

    private Integer address;

    private String phoneNumber;

    private String mobilePhone;

    private String accountNumber;

    private short registrationStatus;

    private Date insertionDate;

    private String insertionUser;

    private String insertionTerminal;
}
