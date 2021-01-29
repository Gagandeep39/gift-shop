/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-29 19:50:10
 * @modify date 2021-01-29 19:50:10
 * @desc [description]
 */
package com.cg.authservice.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LoadBalancerConfig {
  public static final String LOADBALANCED = "loadbalanced";
  public static final String STANDARD = "standard";

  @LoadBalanced
  @Bean
  @Qualifier(LOADBALANCED)
  public RestTemplate loadBalancedRestTemplate() {
    return new RestTemplate();
  }
}
