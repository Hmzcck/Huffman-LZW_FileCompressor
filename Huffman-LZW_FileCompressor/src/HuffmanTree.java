import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import java.util.Comparator;

public class HuffmanTree implements Comparator<hfTreeNode> {
    static hfTreeNode[] hfArr;
    public hfTreeNode hfNod = new hfTreeNode(" ", 0);
    static HashMap<String, String> HuffmanMap = new HashMap<>();

    public static HashMap<String, String> generateTreeGetValues(HashMap<String, Integer> h) {
        hfArr = new hfTreeNode[h.size()];
        int counter = 0;
        for (Map.Entry<String, Integer> entry : h.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            hfArr[counter] = new hfTreeNode(key, value);
            counter++;
        }

        Comparator<hfTreeNode> cmp = new Comparator<hfTreeNode>() {

            @Override
            public int compare(hfTreeNode node1, hfTreeNode node2) {
                return node1.getValue() - node2.getValue();
            }

        };
        Arrays.sort(hfArr, cmp);

        hfTreeNode hfTree = generateTree(hfArr);

        giveBytes(hfTree, "", HuffmanMap);
        return HuffmanMap;
    }

    public static hfTreeNode generateTreeForDecompiling(String[] PassArr) {
        hfTreeNode passNode = new hfTreeNode(" ", 0);
        for (int i = 0; i < PassArr.length; i += 2) {
            String way = PassArr[i + 1];
            String asciiCode = PassArr[i];
            int counter = 0;
            hfTreeNode willBeInserted = new hfTreeNode(asciiCode, 0);
            hfTreeNode currentNode = passNode;
            while (counter < way.length() - 1) {
                if (way.charAt(counter) == '1') {
                    if (currentNode.righthfNode == null) {
                        hfTreeNode tempNode = new hfTreeNode(" ", 0);
                        currentNode.righthfNode = tempNode;
                        currentNode = tempNode;
                    } else {
                        currentNode = currentNode.righthfNode;
                    }
                } else {
                    if (currentNode.lefhfNode == null) {
                        hfTreeNode tempNode = new hfTreeNode(" ", 0);
                        currentNode.lefhfNode = tempNode;
                        currentNode = tempNode;
                    } else {
                        currentNode = currentNode.lefhfNode;
                    }

                }
                counter++;
            }
            if (way.charAt(counter) == '1') {
                currentNode.righthfNode = willBeInserted;
            } else {
                currentNode.lefhfNode = willBeInserted;
            }
        }
        return passNode;

    }

    private static hfTreeNode generateTree(hfTreeNode[] arr) {
   
        SpecialQueue myQ = new SpecialQueue();
        for (int i = 0; i < arr.length; i++) {
            myQ.enqueue(arr[i]);

        }

        while (myQ.size() > 1) {
            hfTreeNode min1 = myQ.getMin1();
            hfTreeNode min2 = myQ.getMin2();
            hfTreeNode newNode = new hfTreeNode(" ", min1.getValue() + min2.getValue());
            newNode.lefhfNode = min1;
            newNode.righthfNode = min2;
            myQ.enqueue(newNode);
        }

        hfTreeNode lastOne;

        lastOne = myQ.getLast();

        return lastOne;
    }

    public static void printInorder(hfTreeNode node) {
        if (node == null)
            return;

        printInorder(node.lefhfNode);

        System.out.print(node.getkey() + "!");

        printInorder(node.righthfNode);
    }

    private static void giveBytes(hfTreeNode hftr, String code, HashMap<String, String> hfff) {

        if (hftr.lefhfNode == null && hftr.righthfNode == null) {
            hftr.byteCode = code;
            hfff.put(hftr.getkey(), code);
            return;
        }
        giveBytes(hftr.lefhfNode, code + "0", hfff);
        giveBytes(hftr.righthfNode, code + "1", hfff);

    }

    @Override
    public int compare(hfTreeNode node1, hfTreeNode node2) {
        return node2.getValue() - node1.getValue();
    }
}
