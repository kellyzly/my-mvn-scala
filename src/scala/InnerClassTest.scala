package scala

object InnerClassTest {

  class Graph {

    class Node {
      var connectedNodes: List[Node] = Nil

      def connectTo(node: Node): Unit = {
        if (connectedNodes.find(node.equals).isEmpty) {
          connectedNodes = node :: connectedNodes
        }
      }
    }

    var nodes: List[Node] = Nil

    def newNode: Node = {
      val res = new Node
      nodes = res :: nodes
      res
    }
  }

  class Graph1 {

    class Node1 {
      var connectedNodes: List[Graph1#Node1] = Nil

      def connectTo(node: Graph1#Node1): Unit = {
        if (connectedNodes.find(node.equals).isEmpty) {
          connectedNodes = node :: connectedNodes
        }
      }
    }

    var nodes: List[Graph1#Node1] = Nil

    def newNode: Node1 = {
      val res = new Node1
      nodes = res :: nodes
      res
    }
  }


  def main1(args: Array[String]): Unit = {
    val graph = new Graph
    val node1 = graph.newNode
    val node2 = graph.newNode
    val node3 = graph.newNode

    node1.connectTo(node2) // compile pass
    val graph2 = new Graph
    val node4 = graph2.newNode
    //node1.connectTo(node4) // type mismatch, actual graph2.Node
    // expect graph.Node


  }

  def main(args: Array[String]): Unit = {
    val graph1 = new Graph1
    val graph2 = new Graph1
    val node11 = graph1.newNode
    val node22 = graph2.newNode


    //After Graph1#Node1  the compile error miss and
    // it means node11.conectTo's parameter is Graph1#Node1
    node11.connectTo(node22)


  }


}
