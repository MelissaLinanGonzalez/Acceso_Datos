package cursoSpringBoot.controllers;

import cursoSpringBoot.domain.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class CustomerController {

    private List<Customer> customers = new ArrayList<>(Arrays.asList(
            new Customer(123, "María Rodríguez", "mRodri", "pepitoflores74"),
            new Customer(234, "Gerardo Bretón", "geri", "amparitopicapiedras45"),
            new Customer(345, "Miguel Unamuno", "miguelito", "periquillolospalotes56"),
            new Customer(456, "Nadia Fernández", "nadiaF", "nohaynadiecomonadia88")
    ));

    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping // AMBAS DECORACIONES SON VALIDAS
    public List<Customer> getCustomers(){
        return customers;
    }

    //@RequestMapping(value = "/{userName}", method = RequestMethod.GET)
    @GetMapping("/{userName}")
    public Customer getCliente(@PathVariable String userName){
        for (Customer c : customers){
            if (c.getUserName().equalsIgnoreCase(userName)){
                return c;
            }
        }
        return null;
    }

    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping
    public Customer postCliente(@RequestBody Customer customer){
        customers.add(customer);
        return customer;
    }

    //@RequestMapping(method = RequestMethod.PUT)
    @PutMapping
    public Customer putCliente(@RequestBody Customer customer){
        for (Customer c : customers){
            if (customer.getID() == c.getID()){
                c.setName(customer.getName());
                c.setUserName(customer.getUserName());
                c.setPassword(customer.getPassword());
                return c;
            }
        }
        return null;
    }

    //@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @DeleteMapping("{id}")
    public Customer deleteCliente(@PathVariable int id){
        for (Customer c : customers){
            if (c.getID() == id){
                customers.remove(c);

                return c;
            }
        }
        return null;
    }

    //@RequestMapping(method = RequestMethod.PATCH)
    @PatchMapping
    public Customer patchCliente(@RequestBody Customer customer){
        for (Customer c : customers){
            if (c.getID() == customer.getID()){
                if (customer.getName() != null){
                    c.setName(customer.getName());
                }
                if (customer.getUserName() != null){
                    c.setUserName(customer.getUserName());
                }
                if (customer.getPassword() != null){
                    c.setPassword(customer.getPassword());
                }
                return c;
            }
        }
        return null;
    }
}
