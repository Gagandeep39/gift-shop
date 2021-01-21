/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-21 16:56:31
 * @modify date 2021-01-21 16:56:31
 * @desc [description]
 */
package com.cg.gatewayserver.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

@Component
@Primary
@EnableAutoConfiguration
public class SwaggerMircroserviceConfig implements SwaggerResourcesProvider {

  @Override
  public List<SwaggerResource> get() {
    List<SwaggerResource> resources = new ArrayList<>();
    resources.add(swaggerResource("auth-service", "/auth-service/v2/api-docs", "2.0"));
    resources.add(swaggerResource("product-service", "/product-service/v2/api-docs", "2.0"));
    resources.add(swaggerResource("order-service", "/order-service/v2/api-docs", "2.0"));
    resources.add(swaggerResource("cart-service", "/cart-service/v2/api-docs", "2.0"));
    return resources;
  }

  private SwaggerResource swaggerResource(String name, String location, String version) {
    SwaggerResource swaggerResource = new SwaggerResource();
    swaggerResource.setName(name);
    swaggerResource.setLocation(location);
    swaggerResource.setSwaggerVersion(version);
    return swaggerResource;
  }

}
