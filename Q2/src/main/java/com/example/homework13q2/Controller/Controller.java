package com.example.homework13q2.Controller;

import com.example.homework13q2.ApiResponse.ApiResponse;
import com.example.homework13q2.Customers.Customers;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("api/v1/customers")
@RestController
public class Controller {

    ArrayList<Customers> customers = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Customers> getCustomers (){
        return customers;
    }


    @PostMapping("/add")
    public ApiResponse addCustomers(@RequestBody Customers customer){
        customers.add(customer);
        return new ApiResponse("Customer Added",200);
    }


    @PutMapping("/update/{index}")
    public ApiResponse updateCustomers(@PathVariable int index ,@RequestBody Customers customer){
        if(customers.size() == 0){
            return new ApiResponse("you can NOT update because array is empty;",100);
        } else if(index < 0 ){
            return new ApiResponse("you can NOT put number less than 0 ",100);
        } else if (customers.size() < index) {
            return new ApiResponse("you can NOT put number more than size",100);
        }else{
            customers.set(index,customer);
            return new ApiResponse("Updated",200);
        }
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteCustomers(@PathVariable int index) {
        if (customers.size() == 0) {
            return new ApiResponse("you can NOT update because array is empty;", 100);
        } else if (index < 0) {
            return new ApiResponse("you can NOT put number less than 0 ", 100);
        } else if (customers.size() < index) {
            return new ApiResponse("you can NOT put number more than size", 100);
        } else {
            customers.remove(index);
            return new ApiResponse("Deleted",200);
        }
    }

    @PutMapping("/deposit/{index}/{balance}")
    public ApiResponse deposit(@PathVariable int index,@PathVariable int balance,Customers customer){

        if(customers.size() == 0){
            return new ApiResponse("you can NOT update because array is empty;",100);
        } else if(index < 0 ){
            return new ApiResponse("you can NOT put number less than 0 ",100);
        } else if (customers.size() < index) {
            return new ApiResponse("you can NOT put number more than size",100);
        }else {
            customer = customers.get(index);

            System.out.println(balance);
            customer.setBalance(customer.getBalance() + balance);

            return new ApiResponse("Deposit success", 200);
        }
    }

    @PutMapping("/withdraw/{index}/{balance}")
    public ApiResponse withdraw(@PathVariable int index,@PathVariable int balance,Customers customer){

        if(customers.size() == 0){
            return new ApiResponse("you can NOT update because array is empty;",100);
        } else if(index < 0 ){
            return new ApiResponse("you can NOT put number less than 0 ",100);
        } else if (customers.size() < index) {
            return new ApiResponse("you can NOT put number more than size",100);
        }else {
            customer = customers.get(index);
            System.out.println(balance);
            if (customer.getBalance() - balance >= 0) {
                customer.setBalance(customer.getBalance() - balance);
                return new ApiResponse("Withdraw success", 200);
            } else {
                return new ApiResponse("Withdraw Failed", 200);

            }
        }
    }

}
