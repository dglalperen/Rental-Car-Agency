package com.CarRentalAgency.controller;

import com.CarRentalAgency.entity.Dealer;
import com.CarRentalAgency.exception.AlreadyExistsException;
import com.CarRentalAgency.services.DealerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/dealers")
public class DealerController {

    private final DealerServiceImpl dealerService;


    @GetMapping
    public List<Dealer> dealerList() {
        List<Dealer> dealerList = dealerService.findAllDealers();
        if (dealerList.isEmpty())
            throw new NoSuchElementException("THERE IS NO DEALERS IN THE DATA BASE.");
        return dealerList;
    }

    @GetMapping("{id}")
    public Dealer findDealerByID(@PathVariable Long id) {
        return dealerService.findDealerById(id);
    }


    @GetMapping("{email}")
    public Dealer findDealerByEmail(@PathVariable String email) {
        return dealerService.findDealerByEmail(email);
    }

    @GetMapping("{name}")
    public List<Dealer> findDealerByName(@PathVariable String name) {
        return dealerService.findDealerByFirstNameIgnoreCase(name);
    }

    @PostMapping
    public Dealer saveDealer(@RequestBody Dealer dealer) throws AlreadyExistsException {
        return dealerService.saveDealer(dealer);
    }


    @DeleteMapping("{id}")
    public String DeleteDealerByID(@PathVariable Long id) throws NoSuchElementException {
        dealerService.deleteDealerById(id);
        return "Deleted Successfully ;) ";
    }

    @PutMapping("{id}")
    public Dealer updateDealer(@PathVariable Long id, @RequestBody @Valid Dealer dealer)
            throws NoSuchElementException, AlreadyExistsException {
        return dealerService.updateDealer(id, dealer);
    }

}
