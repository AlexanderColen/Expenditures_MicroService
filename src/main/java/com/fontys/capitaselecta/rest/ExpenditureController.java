package com.fontys.capitaselecta.rest;

import com.fontys.capitaselecta.domain.Expenditure;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alex
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/expenditures")
public class ExpenditureController {
    
    private List<Expenditure> expenditures;

    public ExpenditureController() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        String date = dateFormatter.format(new Date());
        
        this.expenditures = new ArrayList<>();
        
        expenditures.add(new Expenditure(1, date, "Test Expenditure 1", "REST", 13.37, '$'));
        expenditures.add(new Expenditure(2, date, "Test Expenditure 2", "REST", 69.69, '$'));
        expenditures.add(new Expenditure(3, date, "Test Expenditure 3", "REST", 12.34, '$')); 
    }     
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Expenditure fetchSpecificExpenditure(@PathVariable("id") long id) {
        Expenditure found = null;
        
        for (Expenditure e : this.expenditures) {
            if (e.getId() == id) {
                found = e;
                break;
            }
        }
        
        return found;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public List<Expenditure> getAllExpenditures() {
        return this.expenditures;
    }
}