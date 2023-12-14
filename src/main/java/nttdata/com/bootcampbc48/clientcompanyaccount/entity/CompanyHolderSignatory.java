package nttdata.com.bootcampbc48.clientcompanyaccount.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class CompanyHolderSignatory {

    @Id
    private String _id;

    private String accountNumber;

    private String documentNumber;

    private String type;

    private short registrationStatus;

    private Date insertionDate;

    private String insertionUser;

    private String insertionTerminal;

}
