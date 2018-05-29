package com.fontys.capitaselecta.service;

import com.fontys.capitaselecta.domain.Expenditure;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import com.fontys.capitaselecta.dao.ExpenditureDAO;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alexander
 */
@Service
public class ExpenditureService {

    private static final Logger LOG = Logger.getLogger(ExpenditureService.class.getName());
        
    @Autowired
    DataSource dataSource;
    
    @Autowired
    ExpenditureDAO expenditureDAO;
    
    public ExpenditureService() {
        LOG.log(Level.INFO, String.format("Datasource: %s", this.dataSource));
    }
    
    public List<Expenditure> getAllExpenditures() {
        List<Expenditure> expenditures = new ArrayList<>();
        if (this.expenditureDAO != null) {
            for (Expenditure e : this.expenditureDAO.findAll()) {
                expenditures.add(e);
            }
        } else {
            LOG.log(Level.INFO, "Autowired fail.");
        }
        
        
        return expenditures;
    }

    public boolean postExpenditure(Expenditure expenditure) {
        if (this.expenditureDAO == null) {
            return false;
        }
        
        this.expenditureDAO.save(expenditure);
        
        return true;
    }

    public Expenditure getSpecificExpenditure(long id) {
        if (this.expenditureDAO == null) {
            return null;
        }
        
        if (this.expenditureDAO.findById(id).isPresent()) {
            return this.expenditureDAO.findById(id).get();
        } else {
            return null;
        }
    }

    public boolean deleteExpenditure(long id) {
        if (this.expenditureDAO == null) {
            return false;
        }
        
        this.expenditureDAO.delete(this.expenditureDAO.findById(id).get());
        
        return true;
    }
}