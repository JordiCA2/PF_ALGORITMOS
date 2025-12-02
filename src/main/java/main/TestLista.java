import estructuras.ListaEnlazada;

public class TestLista {

    public static void main(String[] args) {

        ListaEnlazada<String> lista = new ListaEnlazada<>();

        System.out.println("=== PRUEBA DE LISTA ENLAZADA ===");

        // Agregar elementos
        lista.agregar("A");
        lista.agregar("B");
        lista.agregar("C");
        lista.agregar("D");

        System.out.println("Tamaño actual: " + lista.tamano());

        // Obtener elementos
        System.out.println("Elemento en índice 0: " + lista.obtener(0));
        System.out.println("Elemento en índice 2: " + lista.obtener(2));

        // Buscar elemento (usando condición)
        String buscado = lista.buscar(d -> d.equals("C"));
        System.out.println("¿Se encontró 'C'? -> " + buscado);

        // Filtrar elementos (devuelve nueva lista)
        ListaEnlazada<String> filtrada = lista.filtrar(d -> d.compareTo("C") >= 0);
        System.out.println("\nLista filtrada (>= 'C'):");
        Object[] arrFil = filtrada.toArray();
        for (Object o : arrFil) System.out.println(o);

        // Reemplazar
        System.out.println("\nReemplazando índice 1 con 'Z'...");
        lista.reemplazar(1, "Z");

        // Mostrar lista actual
        System.out.println("Contenido actual:");
        Object[] arr = lista.toArray();
        for (Object o : arr) System.out.println(o);

        // Eliminar por índice
        System.out.println("\nEliminando índice 2...");
        lista.eliminarPorIndice(2);

        // Mostrar lista final
        System.out.println("Lista final:");
        arr = lista.toArray();
        for (Object o : arr) System.out.println(o);

        System.out.println("\nTamaño final: " + lista.tamano());
    }
}
