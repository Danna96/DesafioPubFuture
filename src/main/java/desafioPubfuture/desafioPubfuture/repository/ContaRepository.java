
package desafioPubfuture.desafioPubfuture.repository;

import desafioPubfuture.desafioPubfuture.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    @Query("SELECT SUM(saldo) FROM Conta")
    Double getSaldoTotal();

}

