package com.fontys.capitaselecta.rest;

import com.fontys.capitaselecta.domain.Expenditure;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alex
 */
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS })
@RestController
@RequestMapping(value = "/expenditures")
public class ExpenditureController {

    private static final Logger LOG = Logger.getLogger(ExpenditureController.class.getName());
    
    private List<Expenditure> expenditures;
    private AtomicInteger counter = new AtomicInteger(1);

    public ExpenditureController() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        String date = dateFormatter.format(new Date());
        
        this.expenditures = new ArrayList<>();
        
        expenditures.add(new Expenditure(counter.getAndIncrement(), date, "Coffee + Biscuit", "FOOD", 13.37, "CAD"));
        expenditures.add(new Expenditure(counter.getAndIncrement(), date, "Sweater", "CLOTHES", 69.69, "CAD"));
        expenditures.add(new Expenditure(counter.getAndIncrement(), date, "Rent for June", "RENT", 555.55, "CAD")); 
    }     
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Expenditure fetchSpecificExpenditure(@PathVariable("id") long id) {
        LOG.log(Level.INFO, "Fetch Specific Expenditure.");
        
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
        //TODO DATABASE THIS SHIT.
        LOG.log(Level.INFO, "Fetch All Expenditures.");
        
        return this.expenditures;
    }
    
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public Expenditure postExpenditure(@RequestParam("description") String description,
                                        @RequestParam("date") String date,
                                        @RequestParam("spent") double spent,
                                        @RequestParam("type") String type,
                                        @RequestParam("currency") String currency) {
        //TODO POST IN DATABASE.
        LOG.log(Level.INFO, "Post New Expenditure.");
        
        Expenditure expenditure = new Expenditure(counter.getAndIncrement(), date, description, type, spent, currency);
        this.expenditures.add(expenditure);
        
        return expenditure;
    }
}