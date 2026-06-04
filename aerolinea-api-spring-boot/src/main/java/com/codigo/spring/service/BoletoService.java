package com.codigo.spring.service;

import com.codigo.spring.request.BoletoRequest;
import com.codigo.spring.response.BoletoResponse;
import com.codigo.spring.response.ResponseBase;

public interface BoletoService {
    ResponseBase<BoletoResponse> save(BoletoRequest boletoRequest);
}
