package com.vtxlab.bootcamp.bccryptocoingecko.infra;

import java.util.HashMap;
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
}
