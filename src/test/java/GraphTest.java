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


}
