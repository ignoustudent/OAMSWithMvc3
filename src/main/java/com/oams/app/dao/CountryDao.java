/**
 * 
 */
package com.oams.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oams.app.entities.Country;

/**
 * @author RAKESH SINGH
 *
 * Apr 7, 2018
 */
public interface CountryDao extends JpaRepository<Country, Integer>  {

	
}
