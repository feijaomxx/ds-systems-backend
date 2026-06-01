package com.dssystems.dssystemsbackend.repositories;

import com.dssystems.dssystemsbackend.models.ResumoMensalProjection;
import com.dssystems.dssystemsbackend.models.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {

    List<Transacao> findByUsuarioId(Integer usuarioId);

    @Query(value = "SELECT * FROM vw_resumo_mensal WHERE usuario = :nomeUsuario", nativeQuery = true)
    List<ResumoMensalProjection> buscarResumoDoDashboard(@Param("nomeUsuario") String nomeUsuario);
}