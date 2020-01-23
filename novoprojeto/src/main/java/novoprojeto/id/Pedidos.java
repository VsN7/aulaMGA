package novoprojeto.id;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class Pedidos {

    @Inject
    private EntityManager manager;

    public BigDecimal totalPedidosMesAtual(){
        return new BigDecimal("100");
    }

}
