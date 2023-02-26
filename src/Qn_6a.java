import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/* Implement Huffman encoding and decoding.*/
class MyComparator implements Comparator<Huffman.HuffmanNode> {
    public int compare(Huffman.HuffmanNode x, Huffman.HuffmanNode y)
    {
       //used in priority queue to sort the node according to the data variable which is the frequency
        return x.data - y.data;
    }
}

class Huffman {

    //huffman node is a node in the huffman tree created during encoding
    class HuffmanNode{
        int data;
        char c;
        HuffmanNode left;
        HuffmanNode right;
    }

    //prints the encoded huffman code
    public static void printCode(HuffmanNode root, String s){
        //prints the code of each character

        if(root.left==null&&root.right==null&&Character.isLetter(root.c)){
            //print once leaf node is reached
            return;
        }
        //if going left then add 0 to string
        printCode(root.left,s+"0");

        //if going to right then add 1 to string
        printCode(root.right,s+"1");
    }

    //encoding
    public HuffmanNode encode(char[] charArray, int[] charFreq){
        /*
         * encoding function creates a huffman tree based on the character frequency
         * */
        int n=charArray.length;

        PriorityQueue<HuffmanNode> q = new PriorityQueue<>(n, new MyComparator());

        /*creating nodes of each character and adding it to priority queue*/
        for(int i=0; i<n; i++){
            HuffmanNode hn = new HuffmanNode();
            hn.c=charArray[i];
            hn.data=charFreq[i];

            hn.left=null;
            hn.right=null;

            q.add(hn);
        }

        HuffmanNode root=null;

        while(q.size()>1){
            /*creating huffman tree*/
            HuffmanNode x = q.peek();
            q.poll();
            HuffmanNode y = q.peek();
            q.poll();
            HuffmanNode f = new HuffmanNode();
            f.data = x.data+y.data;
            //the parent node doesn't contain a character
            f.c='-';
            f.left=x;
            f.right=y;

            root=f;

            q.add(f);

        }
        printCode(root,"");
        return root;
    }


    //decoding the huffman tree
    public void decode(HuffmanNode root, String str){
        /*Input:
        size: is the number of character in the huffman tree
        root: is the parent node
        str: is the 0 and 1 value that is created by encode function
        * */

        /*Output:
        function prints the character and its frequency
        * */

        ArrayList characters= new ArrayList<>();
        ArrayList frequency=new ArrayList<>();

        int i=0;
        while(i<str.length()){
            HuffmanNode current = root;
            while (current.c=='-'){
                if(str.charAt(i)=='0'){
                    current=current.left;
                    i++;
                }
                else {
                    current=current.right;
                    i++;
                }
            }

            /*once leaf node is reached add the character value to character list
            * and add the frequency value to frequency list*/

            char c = current.c;
            int f =current.data;
            characters.add(current.c);
            frequency.add(current.data);



        }
        printDecode(characters,frequency);

    }

    public static void printDecode(ArrayList characters, ArrayList frequencies){
        for (int i=0; i<characters.size(); i++){
            System.out.println(characters.get(i)+":"+frequencies.get(i));
        }
    }




    //driver method
    public static void main(String[] args) {
        char[] ch={'A','B','C','D','E'};
        int[] fre= {4,7,3,2,7};

        Huffman h = new Huffman();
        HuffmanNode hn= h.encode(ch,fre);
        System.out.println(hn.data);
        String cha = "000100111011";
        h.decode(hn,cha);

    }
}
