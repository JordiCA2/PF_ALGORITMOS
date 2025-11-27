package main;

import servicios.GestorPedidos;
import modelos.*;

// prueba simple de la cola con prioridad de pedidos
public class TestPedidos {

    public static void main(String[] args) {
        GestorPedidos gestor = new GestorPedidos();
        
        // items de ejemplo
        ItemPedido[] items1 = new ItemPedido[]{
                new ItemPedido("P100", "Vaso", 2, 3.50, 2 * 3.50),
                new ItemPedido("P150", "Caja", 1, 2.20, 1 * 2.20)
            
        };
        
        ItemPedido[] items2 = new ItemPedido[]{
                new ItemPedido("P300", "Tenedor", 5, 1.00, 5 * 1.00)
            
        };
        
        ItemPedido[] items3 = new ItemPedido[]{
                            new ItemPedido("P250", "Servilleta", 3, 1.50, 3 * 1.50),
                new ItemPedido("P200", "Bolsa", 4, 0.80, 4 * 0.80)

        };
        
        gestor.registrarPedido("A1", "Ana", "Av. 1", TipoCliente.PREMIUM, TipoEntrega.EXPRESS, items1);
        gestor.registrarPedido("B1", "Beto", "Av. 2", TipoCliente.REGULAR, TipoEntrega.NORMAL, items2);
        gestor.registrarPedido("C1", "Caro", "Av. 3", TipoCliente.REGULAR, TipoEntrega.EXPRESS, items3);        
    
        System.out.println("Pendientes (por prioridad):");
        gestor.listarPedidoPendientes();
        
        gestor.atenderSiguientePedido();
        gestor.atenderSiguientePedido();
        gestor.atenderSiguientePedido();
    }
    
}
