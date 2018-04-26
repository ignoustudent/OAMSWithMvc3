/**
 * 
 */
package com.oams.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.oams.app.entities.State;

/**
 * @author RAKESH SINGH
 *
 * Apr 7, 2018
 */
public interface StateDao extends JpaRepository<State, Integer>  {

	
}
