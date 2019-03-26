package fi.tuni.bloggadeback;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @RequestMapping(value = "api/public/accounts/{accountNumber}")
    public String getPublicAccountDataLinkedTo(@PathVariable final int accountNumber) {
        return "Public Account Lined To: " + accountNumber;
    }

    @RequestMapping(value = "api/private/accounts/{accountNumber}")
    public String getPrivateAccountDataLinkedTo(@PathVariable final int accountNumber) {
        return "Private Account Lined To: " + accountNumber;
    }

    @RequestMapping(value = "api/private/admin/accounts/{accountNumber}")
    public String getExtraPrivateAccountDataLinkedTo(@PathVariable final int accountNumber) {
        return "Private Extra Account Lined To: " + accountNumber;
    }
}
