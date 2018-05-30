package com.alexandercolen.dao;

import com.alexandercolen.domain.Expenditure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alex
 */
@Repository
public interface ExpenditureDAO extends CrudRepository<Expenditure, Long> { 

}
