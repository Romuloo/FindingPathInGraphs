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
package domain;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Javier Linares Castrillón
 */

public class Graph<V> {


//Lista de adyacencia
private Map<V, Set<V>> adjacencyList = new HashMap<V, Set<V>>();

/**
 * Añade el vértice ‘v‘ al grafo.
 *
 * @param v vértice a añadir.
 * @return ‘true‘ si no estaba anteriormente y ‘false‘ en caso contrario.
 */
public void addVertex(V v) {
    Set<V> adyacentes = new TreeSet<V>();
   adjacencyList.put(v, adyacentes);
}

/**
 * Añade un arco entre los vértices ‘v1‘ y ‘v2‘ al grafo. En caso de que no exista alguno de los
 * vértices, lo añade también.
 *
 * @param v1 el origen del arco.
 * @param v2 el destino del arco.
 * @return ‘true‘ si no existía el arco y ‘false‘ en caso
 * contrario.
 */

public boolean addEdge(V v1, V v2) {
    if(!this.containsVertex(v1)) this.addVertex(v1);
    if(!this.containsVertex(v2)) this.addVertex(v2);
    return adjacencyList.get(v1).add(v2); // ahora el valor de V1 == un conjunto con los actuales hijos de v1
}


/**
 * Obtiene el conjunto de vértices adyacentes a ‘v‘.
 *
 * @param v vértice del que se obtienen los adyacentes.
 * @return conjunto de vértices adyacentes.
 */
public Set<V> obtainAdjacents(V v){
   if(!this.containsVertex(v)) throw new NullPointerException("No existe el vértice " + v + ".");
    return adjacencyList.get(v);
}

/**
 * Comprueba si el grafo contiene el vértice dado.
 *
 * @param v vértice para el que se realiza la comprobación.
 * @return ‘true‘ si ‘v‘ es un vértice del grafo.
 */
public boolean containsVertex(V v) {
    return adjacencyList.containsKey(v);
}

/**
 * Método ‘toString()‘ reescrito para la clase ‘Grafo.java‘.
 *
 * @return una cadena de caracteres con la lista de adyacencia
 */
@Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    for(V v : adjacencyList.keySet())
        sb.append( v + "=" + adjacencyList.get(v) + ", ");
    return "Grafo: [ " + sb.toString() + "]";
}

/**
 * Obtiene, en caso de que exista, un camino entre ‘v1‘ y ‘v2 ‘. En
 * caso contrario, devuelve ‘null‘.
 *
 * @param v1 el vértice origen.
 * @param v2 el vértice destino.
 * @return lista con la secuencia de vértices desde ‘v1‘ hasta ‘v2‘
 * pasando por arcos del grafo.
 */
public List<V> onePath(V v1, V v2) {

    boolean encontrado = false;
    Map<V, V> traza = new HashMap<>();
    Stack<V> abierta = new Stack<>();
    List<V> secuencia = new LinkedList<>();

    abierta.push(v1);
    traza.put(v1, null);

    do{
        V vx = abierta.pop();
        if(vx == v2)
            encontrado = true;
        else
            for(V v : adjacencyList.get(vx)) {
                abierta.push(v);
                traza.put(v, vx);
             }
    } while(!abierta.isEmpty() && !encontrado);

    if(encontrado){

        V vFinal = v2;

        while(vFinal != v1){
            secuencia.add(vFinal);
            vFinal = traza.get(vFinal);
        }
        secuencia.add(v1);

        // Tengo que trasponer el array. A cada posición v del array le hago corresponer el valor del elemento de la posición Length - posición del elemento.
        // De  esta forma, un elemento que estaría en la posición 1 en un array de 4 elementos pasa a estar en [4 - 1 = 3].
        // Esto lo collecto todo en una lista.
        return secuencia.stream().map(v -> secuencia.get((secuencia.size() - 1) - secuencia.indexOf(v))).collect(Collectors.toList());
    }
    return null;
    }
    
}

