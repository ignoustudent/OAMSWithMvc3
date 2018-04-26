/**
 * 
 */
package com.oams.app.dao;

import com.oams.app.entities.UserCredential;

/**
 * @author RAKESH SINGH
 *
 * Apr 1, 2018
 */

public interface UserCredDao{

	

    UserCredential getUserByUserName(String email);

    void updateUserCred(UserCredential userCred);


}
