package com.dssystems.dssystemsbackend.models;

import java.math.BigDecimal;

public interface ResumoMensalProjection {
    String getUsuario();
    String getTipo_movimentacao();
    BigDecimal getTotal_acumulado();
    String getMes_referencia();
}