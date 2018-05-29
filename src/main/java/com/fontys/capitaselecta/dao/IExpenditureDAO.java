package com.fontys.capitaselecta.dao;

import com.fontys.capitaselecta.domain.Expenditure;
import java.util.List;

/**
 *
 * @author Alex
 */
public interface IExpenditureDAO {
    public void insert(Expenditure expenditure);
    
    public void merge(Expenditure expenditure);
    
    public void remove(Expenditure expenditure);
    
    public Expenditure fetchExpenditure(long id);
    
    public List<Expenditure> fetchAllExpenditures();
    
    public List<Expenditure> fetchAllForYear();
    
    public List<Expenditure> fetchAllForMonth();
    
    public List<Expenditure> fetchAllForDay();
}