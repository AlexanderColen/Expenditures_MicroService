package com.alexandercolen.rest;

import com.alexandercolen.domain.Expenditure;
import com.alexandercolen.service.ExpenditureService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    private ExpenditureService expenditureService;

//    @PostConstruct
//    public void init() {
//        LOG.log(Level.INFO, "Creating fake data...");
//        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
//        String date = dateFormatter.format(new Date());
//        
//        this.expenditureService.postExpenditure(new Expenditure(date, "Coffee + Biscuit", "FOOD", 13.37, "CAD"));
//        this.expenditureService.postExpenditure(new Expenditure(date, "Sweater", "CLOTHES", 69.69, "CAD"));
//        this.expenditureService.postExpenditure(new Expenditure(date, "Rent for June", "RENT", 555.55, "CAD"));
//    }     
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Expenditure fetchSpecificExpenditure(@PathVariable("id") long id) {
        LOG.log(Level.INFO, "Fetch Specific Expenditure.");
        
        return this.getExpenditureService().getSpecificExpenditure(id);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public List<Expenditure> getAllExpenditures() {
        LOG.log(Level.INFO, "Fetch All Expenditures.");
        
        return this.getExpenditureService().getAllExpenditures();
    }
    
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public Expenditure postExpenditure(@RequestParam("description") String description,
                                        @RequestParam("date") String date,
                                        @RequestParam("spent") double spent,
                                        @RequestParam("type") String type,
                                        @RequestParam("currency") String currency) {
        LOG.log(Level.INFO, "Post New Expenditure.");
        
        Expenditure expenditure = new Expenditure(date, description, type, spent, currency);
        
        if (this.getExpenditureService().postExpenditure(expenditure)) {
            return expenditure;
        } else {
            return null;
        }
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public boolean removeExpenditure(@RequestParam("id") long id) {
        LOG.log(Level.INFO, String.format("Deleting Expenditure with ID %s", id));
        
        return this.getExpenditureService().deleteExpenditure(id);
    }
    
    private ExpenditureService getExpenditureService() {
        if (this.expenditureService == null) {
            this.expenditureService = new ExpenditureService();
        }
        
        return this.expenditureService;
    }
}