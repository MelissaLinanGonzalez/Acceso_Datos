package cursoSpringBoot.controllers;

import cursoSpringBoot.domain.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Customer>> getCustomers(){
        return ResponseEntity.ok(customers);
        //return customers;
    }

    //@RequestMapping(value = "/{userName}", method = RequestMethod.GET)
    @GetMapping("/{userName}")
    public ResponseEntity<?> getCliente(@PathVariable String userName){ //El signo <?> Indica que podemos devolver diferentes tipos
        for (Customer c : customers){
            if (c.getUserName().equalsIgnoreCase(userName)){
                return ResponseEntity.ok(c);
                //return c;
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado con userName: " + userName);
    }

    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping
    public ResponseEntity<?> postCliente(@RequestBody Customer customer){
        customers.add(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente creado exitósamente: " + customer.getUserName());
    }

    //@RequestMapping(method = RequestMethod.PUT)
    @PutMapping
    public ResponseEntity<?> putCliente(@RequestBody Customer customer){
        for (Customer c : customers){
            if (customer.getID() == c.getID()){
                c.setName(customer.getName());
                c.setUserName(customer.getUserName());
                c.setPassword(customer.getPassword());
                return ResponseEntity.ok("Cliente modificado correctamente: " + c.getID());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado: " + customer.getID());
    }

    //@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable int id){
        for (Customer c : customers){
            if (c.getID() == id){
                customers.remove(c);

                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Cliente con id " + id + " eliminado correctamente");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente con id " + id + " no encontrado");
    }

    //@RequestMapping(method = RequestMethod.PATCH)
    @PatchMapping
    public ResponseEntity<?> patchCliente(@RequestBody Customer customer){
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
                return ResponseEntity.ok("Cliente modificado correctamente: " + customer.getID());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente con el id " + customer.getID() + " no encontrado");
    }
}
