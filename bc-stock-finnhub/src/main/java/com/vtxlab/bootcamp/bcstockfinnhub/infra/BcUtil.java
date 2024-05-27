package com.vtxlab.bootcamp.bcstockfinnhub.infra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.util.UriComponentsBuilder;

public class BcUtil {
  
  public static String getUrl(Scheme scheme, String domain,String endpoint ,HashMap<String,String> uris) {
    UriComponentsBuilder uri = UriComponentsBuilder.newInstance();
      uri.scheme(scheme.toLowercase())
      .host(domain)
      .path(endpoint);
      for (Map.Entry<String,String> s: uris.entrySet()) {
        if (s.getValue() != null) {
          uri.queryParam(s.getKey(),s.getValue());
        }
      }
      return uri.toUriString(); // handle special character, \ : //
  }

  public static String getUrl(Scheme scheme, String domain,String endpoint) {
    return UriComponentsBuilder.newInstance()
      .scheme(scheme.toLowercase())
      .host(domain)
      .path(endpoint)
      .toUriString(); // handle special character, \ : //
  }

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
