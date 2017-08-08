package fr.iocean.dta.employee.service;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fr.iocean.dta.employee.model.Employee;

@Service
@Scope(value = "prototype")
public class MailService {

    private String message;

    public void sendMail(Employee employee) {
        StringBuffer mail = new StringBuffer("Sending mail to ");
        mail.append(employee.getLastName()).append(" ");
        mail.append(employee.getFirstName()).append(" : ");
        mail.append(this.message);
        System.out.println(mail);
    }

    public void setMessage(String message) {
        this.message = message;
    }
}