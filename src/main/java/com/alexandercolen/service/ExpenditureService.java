package com.alexandercolen.service;

import com.alexandercolen.domain.Expenditure;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import com.alexandercolen.dao.ExpenditureDAO;
import java.io.IOException;
import java.util.Comparator;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alexander
 */
@Service
public class ExpenditureService {

    private static final Logger LOG = Logger.getLogger(ExpenditureService.class.getName());
    
    private static final String DEBTS_URL = "http://localhost:8091/debts/";
        
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
        
        expenditures.sort(Comparator.comparing(Expenditure::getId));
        
        return expenditures;
    }

    public boolean postExpenditure(Expenditure expenditure, String external) {
        try {
            if (this.expenditureDAO == null) {
                return false;
            }
            
            if (expenditure.getDebtID() != 0 && external.equalsIgnoreCase("yes")) {
                //Create HTTP request for posting payment in Debt microservice.
                HttpClient httpClient = HttpClientBuilder.create().build();

                String postURL = String.format(ExpenditureService.DEBTS_URL + "%s/payments/new/no", expenditure.getDebtID());
                LOG.log(Level.INFO, String.format("Trying to post at URL: %s", postURL));

                HttpPost postRequest = new HttpPost(postURL);

                List<NameValuePair> params = new ArrayList<>();
                params.add(new BasicNameValuePair("date", String.format("%s", expenditure.getDate())));
                params.add(new BasicNameValuePair("spent", String.format("%s", expenditure.getSpent())));
                params.add(new BasicNameValuePair("currency", String.format("%s", expenditure.getCurrency())));
                postRequest.setEntity(new UrlEncodedFormEntity(params));

                //Execute request.
                HttpResponse response = httpClient.execute(postRequest);

                if (response.getStatusLine().getStatusCode() != 200) {
                    LOG.log(Level.INFO, String.format("Failed : HTTP error code : %s", response.getStatusLine().getStatusCode()));
                    return false;
                }
            }
            
            this.expenditureDAO.save(expenditure);
        
        return true;
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        
        return false;
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
        
        Expenditure expenditure = this.expenditureDAO.findById(id).get();
        
        if (expenditure.getDebtID() != 0) {
            //TODO find a way to make deleting a payment work without knowing what payment it is exactly.
            
//            try {
//                //Create HTTP request for deleting payment in Debt microservice.
//                HttpClient httpClient = HttpClientBuilder.create().build();
//                
//                String postURL = String.format(this.URL + "%s/payments/new", expenditure.getDebtID());
//                
//                HttpPost postRequest = new HttpPost(postURL);
//                
//                List<NameValuePair> params = new ArrayList<>();
//                params.add(new BasicNameValuePair("date", String.format("%s", expenditure.getId())));
//                postRequest.setEntity(new UrlEncodedFormEntity(params));
//                
//                //Execute request.
//                HttpResponse response = httpClient.execute(postRequest);
//                
//                if (response.getStatusLine().getStatusCode() != 200) {
//                    LOG.log(Level.INFO, String.format("Failed : HTTP error code : %s", response.getStatusLine().getStatusCode()));
//                    return false;
//                }
//            } catch (IOException ex) {
//                LOG.log(Level.SEVERE, ex.getMessage(), ex);
//            }
        }
        
        this.expenditureDAO.delete(expenditure);
        
        return true;
    }
}