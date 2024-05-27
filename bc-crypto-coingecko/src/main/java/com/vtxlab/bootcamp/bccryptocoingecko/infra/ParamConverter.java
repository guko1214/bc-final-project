package com.vtxlab.bootcamp.bccryptocoingecko.infra;

import java.util.ArrayList;
import java.util.List;

public class ParamConverter {
  
  public static List<String> csvToList(String ids) {
    List<String> idsList = new ArrayList<>();
    char[] chrArr = ids.toCharArray();
    StringBuilder id = new StringBuilder();
    for (int i = 0; i < chrArr.length; i++) {
      if (chrArr[i] != ',') {
        id = id.append(chrArr[i]);
      } else {
        idsList.add(id.toString());
        id = new StringBuilder();
      }
    }
    idsList.add(id.toString());
    return idsList;
  }

}
