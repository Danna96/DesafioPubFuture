package desafioPubfuture.desafioPubfuture.repository;

import desafioPubfuture.desafioPubfuture.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long>{
    List<Receita> findAllByDataRecebimentoBetween(Date dataInicio, Date dataFim);

    List<Receita> findAllByTipoReceita(String tipoReceita);

    @Query("SELECT SUM(valor) FROM Receita")
    Double getValorTotal();
}
