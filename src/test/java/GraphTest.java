/**
 Copyright [2021] [Javier Linares Castrillón]
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 http://www.apache.org/licenses/LICENSE-2.0
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

import domain.Graph;
import org.junit.Test;
import org.junit.Before;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;


/**
 * Clase encargada de testear la clase Graph.
 */

public class GraphTest {

    private Graph<Integer> grafo;
    private Graph<Integer> g;
    private List<Integer> expectedPath;

    @Before
    public void setUp(){

        grafo = new Graph();
        grafo.addVertex(1);
        grafo.addVertex(2);
        grafo.addVertex(3);

        grafo.addEdge(1, 2);

        // Se construye el grafo.
        g = new Graph<>();
        g.addEdge(1, 2); g.addEdge(3, 4); g.addEdge(1, 5); g.addEdge(5, 6); g.addEdge(6, 4);

        // Se construye el camino esperado.
        expectedPath = new LinkedList<>();
        expectedPath.add(1);
        expectedPath.add(5);
        expectedPath.add(6);
        expectedPath.add(4);



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


    /**
     * Este test comprueba que el método ‘onePath(V v1, V v2)‘ * encuentra un camino entre ‘v1‘ y ‘v2‘ cuando existe.
     */
    @Test
    public void onePathFindsAPath(){
    //Se comprueba si el camino devuelto es igual al esperado.
        assertEquals(expectedPath, g.onePath(1, 4));
    }

    @Test
    public void onePathNull(){
        assertEquals(grafo.onePath(1, 5), null);
    }



}
