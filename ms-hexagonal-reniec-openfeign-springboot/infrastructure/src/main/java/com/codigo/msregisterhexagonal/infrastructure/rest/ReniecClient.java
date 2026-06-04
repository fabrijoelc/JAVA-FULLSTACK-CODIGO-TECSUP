package com.codigo.msregisterhexagonal.infrastructure.rest;

import com.codigo.msregisterhexagonal.infrastructure.response.ResponseReniec;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "reniecClient", url = "${decolecta.api.reniec.url}")
public interface ReniecClient {

    @GetMapping("/dni")
    ResponseReniec getInfoReniec(@RequestParam("numero")String numero,
                                 @RequestHeader("Authorization") String authorization);
}
