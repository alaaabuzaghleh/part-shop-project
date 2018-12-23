package com.partsshop.rest.utility;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

public class ActivationCodeGenerator {
	
    public static String generateActivationCode() {
	  char[] charArr="ABCDEFGHIJKLMNOPQRSTUVXYZabcdefghijklmnopqrstuvxyz0123456789".toCharArray();
      String result = RandomStringUtils.random(40, 0, charArr.length-1, true, true, charArr );
      return result;
     }
     
   


}
