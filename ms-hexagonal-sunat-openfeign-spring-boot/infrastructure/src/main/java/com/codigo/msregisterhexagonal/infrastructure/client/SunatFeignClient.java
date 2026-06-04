package com.codigo.msregisterhexagonal.infrastructure.client;

import com.codigo.msregisterhexagonal.infrastructure.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "sunatFeignClient", url = "${decolecta.api.base-url}", configuration = FeignConfig.class)
public interface SunatFeignClient {

    @GetMapping("ruc")
    SunatResponse consultarPorRuc(@RequestParam("numero") String numero);
}
