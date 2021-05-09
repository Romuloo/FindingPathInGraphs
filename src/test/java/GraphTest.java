import domain.Graph;
import org.junit.Test;
import org.junit.Before;

import java.util.Set;

import static org.junit.Assert.*;


/**
 * Clase encargada de testear la clase Graph.
 */

public class GraphTest {

    private Graph<Integer> grafo;

    @Before
    public void setUp(){

        grafo = new Graph();
        grafo.addVertex(1);
        grafo.addVertex(2);
        grafo.addVertex(3);

        grafo.addEdge(1, 2);


    }

    /**
     * Este test comprueba si se han annadido correctamente los vertices
     */
    @Test
    public void verticeAnnadidoCorrectamente(){
        assertTrue(grafo.containsVertex(1));
    }

    /**
     * Este test comprueba si se ha annadido v2 correctamente a la lista de adyacentes de v1
     */
    @Test
    public void arcoAnnadidoCorrectamente(){
        assertTrue(grafo.obtainAdjacents(1).contains(2));
    }

    /**
     * Este test comprueba que si, al annadir un arco entre dos vértices y uno de estos no existe,
     * se annade.
     */
    @Test
    public void verticeAnnadidoAlAnnadirArco(){
        grafo.addEdge(1, 4);

        assertTrue(grafo.containsVertex(1) && grafo.containsVertex(4));
    }

    /**
     * Este test comprueba que, si el vértice del cual estamos buscando
     * su conjunto de adyacentes no existe, tira una excepción.
     * Este test tiene el código mínimo para tirar dicha excepción.
     */
    @Test (expected =  NullPointerException.class)
    public void obtainAdjacentsTiraExcepcion(){
        grafo.obtainAdjacents(5);
    }



}
