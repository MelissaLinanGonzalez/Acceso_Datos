package cursoSpringBoot.controllers;

import cursoSpringBoot.domain.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class CustomerController {

    private List<Customer> customers = new ArrayList<>(Arrays.asList(
            new Customer(123, "María Rodríguez", "mRodri", "pepitoflores74"),
            new Customer(234, "Gerardo Bretón", "geri", "amparitopicapiedras45"),
            new Customer(345, "Miguel Unamuno", "miguelito", "periquillolospalotes56"),
            new Customer(456, "Nadia Fernández", "nadiaF", "nohaynadiecomonadia88")
    ));

    @GetMapping("/clientes")
    public List<Customer> getCustomers(){
        return customers;
    }
}
