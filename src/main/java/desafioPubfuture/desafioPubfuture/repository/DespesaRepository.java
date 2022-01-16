package desafioPubfuture.desafioPubfuture.repository;

import desafioPubfuture.desafioPubfuture.model.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long>{
    List<Despesa> findAllByDataPagamentoBetween(Date dataInicio, Date dataFim);

    List<Despesa> findAllByTipoDespesa(String tipoReceita);

    @Query("SELECT SUM(valor) FROM Despesa")
    Double getValorTotal();
}
