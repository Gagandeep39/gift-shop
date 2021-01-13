/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-13 16:38:40
 * @modify date 2021-01-13 16:38:40
 * @desc Enable DB to be accessed by others
 */
package com.cg.databaseserver.config;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class H2DatabaseConfiguration {

  @Bean(initMethod = "start", destroyMethod = "stop")
  public Server inMemoryH2DatabaseaServer() throws SQLException {
    return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9550");
  }

}
