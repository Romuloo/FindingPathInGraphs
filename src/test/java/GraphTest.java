import domain.Graph;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;


/**
 * Clase encargada de testear la clase Graph.
 */

public class GraphTest {

    private Graph<Integer> grafo;

    @Before
    public void setUp(){

        grafo = new Graph();

    }

    /**
     * Este test comprueba si se han annadido correctamente los vertices
     */
    @Test
    public void verticeAnnadidoCorrectamente(){
        grafo.addVertex(1);

        assertTrue(grafo.containsVertex(1));
    }


}
