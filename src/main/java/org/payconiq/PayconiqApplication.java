package org.payconiq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PayconiqApplication {

  public static final Logger log = LoggerFactory.getLogger(PayconiqApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(PayconiqApplication.class, args);
    log.info("Payconiq Stock Application has started...");
  }
}
