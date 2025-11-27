package estructuras;

import modelos.Producto;


// ABB ordenado por codigo de Producto
// Complejidad O(log n), en peor caso O(n)
public class ArbolBinarioBusqueda {

    private static class Nodo {

        Producto dato;
        Nodo izquierdo;
        Nodo derecho;

        Nodo(Producto d) {
            this.dato = d;
        }
    }
    private Nodo raiz;

    // Insertar producto si el codigo no existe
    public boolean insertar(Producto p) {
        if (p == null || p.getCodigo() == null) {
            return false;
        }
        if (raiz == null) {
            raiz = new Nodo(p);
            return true;
        }
        Nodo actual = raiz;
        while (true) {
            int cmp = comparar(p.getCodigo(), actual.dato.getCodigo());
            if (cmp == 0) {
                return false;
            }
            if (cmp < 0) {
                if (actual.izquierdo == null) {
                    actual.izquierdo = new Nodo(p);
                    return true;
                }
                actual = actual.izquierdo;
            } else {
                if (actual.derecho == null) {
                    actual.derecho = new Nodo(p);
                    return true;
                }
                actual = actual.derecho;
            }
        }
    }

    // Buscar por codigo y retornar producto
    public Producto buscar(String codigo) {
        if (codigo == null) {
            return null;
        }
        Nodo actual = raiz;
        while (actual != null) {
            int cmp = comparar(codigo, actual.dato.getCodigo());
            if (cmp == 0) {
                return actual.dato;
            }
            if (cmp < 0) {
                actual = actual.izquierdo;
            } else {
                actual = actual.derecho;
            }
        }
        return null;
    }

    // Eliminar por codigo manteniendo el ABB
    public boolean eliminar(String codigo) {
        if (codigo == null) {
            return false;
        }
        if (buscar(codigo) == null) {
            return false;
        }
        raiz = eliminarRec(raiz, codigo);
        return true;
    }

    private Nodo eliminarRec(Nodo nodo, String codigo) {
        if (nodo == null) {
            return null;
        }
        int cmp = comparar(codigo, nodo.dato.getCodigo());
        if (cmp < 0) {
            nodo.izquierdo = eliminarRec(nodo.izquierdo, codigo);
        } else if (cmp > 0) {
            nodo.derecho = eliminarRec(nodo.derecho, codigo);
        } else {
            if (nodo.izquierdo == null && nodo.derecho == null) {
                return null;
            } else if (nodo.izquierdo == null) {
                return nodo.derecho;
            } else if (nodo.derecho == null) {
                return nodo.izquierdo;
            } else {
                Nodo sucesor = minimo(nodo.derecho);
                nodo.dato = sucesor.dato;
                nodo.derecho = eliminarRec(nodo.derecho, sucesor.dato.getCodigo());
            }
        }
        return nodo;
    }

    private Nodo minimo(Nodo nodo) {
        while (nodo != null && nodo.izquierdo != null) {
            nodo = nodo.izquierdo;
        }
        return nodo;
    }

    // Retornar productos ordenados por codigo (inorden)
    public Producto[] obtenerInOrder() {
        int n = contar(raiz);
        Producto[] arr = new Producto[n];
        int[] idx = new int[]{0};
        llenarInOrder(raiz, arr, idx);
        return arr;
    }

    private int contar(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + contar(nodo.izquierdo) + contar(nodo.derecho);
    }

    private void llenarInOrder(Nodo nodo, Producto[] arr, int[] idx) {
        if (nodo == null) {
            return;
        }
        llenarInOrder(nodo.izquierdo, arr, idx);
        arr[idx[0]++] = nodo.dato;
        llenarInOrder(nodo.derecho, arr, idx);
    }

    // Retornar productos con precio en (min, max)
    public Producto[] buscarPorRangoPrecio(double min, double max) {
        int c = contarRangoPrecio(raiz, min, max);
        Producto[] arr = new Producto[c];
        int[] idx = new int[]{0};
        llenarRangoPrecio(raiz, min, max, arr, idx);
        return arr;
    }

    private int contarRangoPrecio(Nodo nodo, double min, double max) {
        if (nodo == null) {
            return 0;
        }
        int s = contarRangoPrecio(nodo.izquierdo, min, max) + contarRangoPrecio(nodo.derecho, min, max);
        double p = nodo.dato.getPrecio();
        if (p >= min && p <= max) {
            s++;
        }
        return s;
    }

    private void llenarRangoPrecio(Nodo nodo, double min, double max, Producto[] arr, int[] idx) {
        if (nodo == null) {
            return;
        }
        llenarRangoPrecio(nodo.izquierdo, min, max, arr, idx);
        double p = nodo.dato.getPrecio();
        if (p >= min && p <= max) {
            arr[idx[0]++] = nodo.dato;
        }
        llenarRangoPrecio(nodo.derecho, min, max, arr, idx);
    }

    
    // Retorna productos con stock menor a la cantidadMinima
    public Producto[] buscarStockCritico(int cantidadMinima) {
        int c = contarStockCritico(raiz, cantidadMinima);
        Producto[] arr = new Producto[c];
        int[] idx = new int[]{0};
        llenarStockCritico(raiz, cantidadMinima, arr, idx);
        return arr;
    }

    private int contarStockCritico(Nodo nodo, int cantidadMinima) {
        if (nodo == null) {
            return 0;
        }
        int s = contarStockCritico(nodo.izquierdo, cantidadMinima) + contarStockCritico(nodo.derecho, cantidadMinima);
        if (nodo.dato.getStock() < cantidadMinima) {
            s++;
        }
        return s;
    }

    private void llenarStockCritico(Nodo nodo, int cantidadMinima, Producto[] arr, int[] idx) {
        if (nodo == null) {
            return;
        }
        llenarStockCritico(nodo.izquierdo, cantidadMinima, arr, idx);
        if (nodo.dato.getStock() < cantidadMinima) {
            arr[idx[0]++] = nodo.dato;
        }
        llenarStockCritico(nodo.derecho, cantidadMinima, arr, idx);
    }

    private int comparar(String a, String b) {
        return a.compareTo(b);
    }
}
