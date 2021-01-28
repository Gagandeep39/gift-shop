/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-28 13:35:59
 * @modify date 2021-01-28 13:35:59
 * @desc [description]
 */
package com.cg.cartservice.services;

import com.cg.cartservice.dto.NotificationEmail;

public interface EmailService {

  public void SendEmail(NotificationEmail email);

}
