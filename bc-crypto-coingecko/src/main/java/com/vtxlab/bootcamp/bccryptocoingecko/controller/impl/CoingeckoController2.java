package com.vtxlab.bootcamp.bccryptocoingecko.controller.impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.vtxlab.bootcamp.bccryptocoingecko.model.CoinsMKData;
import com.vtxlab.bootcamp.bccryptocoingecko.service.CoingeckoService;


@Controller
@RequestMapping(value = "/crypto/coingecko/api/v1")
public class CoingeckoController2 {
  
  @Autowired
  CoingeckoService coingeckoService;

  @GetMapping(value = "/coinsdata")
  public String displayExternalData(Model model) {
    HashMap<String, String> parm = new HashMap<>();
    parm.put("vs_currency","usd");
    //parm.put("ids","bitcoin");
    List<CoinsMKData> coinsMKDatas = coingeckoService.getCoinsQuote(parm);
    model.addAttribute("coinsMKDatas", coinsMKDatas);
    return "coin-data";
  }
}
