
/* You are given an array of different words and target words. Each character of a word represents a different
digit ranging from 0 to 9, and no two character are linked to same digit. If the sum of the numbers represented
by each word on the array equals the sum the number represented by the targeted word, return true; otherwise,
return false. Note: Provided list of words and targeted word is in the form of equation
Input: words = ["SIX","SEVEN","SEVEN"], result = "TWENTY"
Output: true
Explanation:
s=6
I=5
X=0,
E=8,
V=7,
N=2,
T=1,
W=3,
Y=4
SIX +SEVEN + SEVEN = TWENTY
650 + 68782 + 68782 = 138214*/
class CalWordVal {

    private char[] letters = {'S', 'I', 'X', 'E', 'V', 'N', 'T', 'W', 'Y'};
    private int[] values = {6, 5, 0, 8, 7, 2, 1, 3, 4};


    //helper function
    public int getNumber(char c){
        /*takes in character and returns the number value associated with the character*/
        for(int i=0; i<letters.length; i++){
            if(letters[i]==c){
                return values[i];
            }
        }
        return -1;
    }
    //helper function
    public int wordToNumber(String word){
        /*returns the number value of given word
        * by concatenating the num value of each character to a string and
        * returning the string by parsing it into integer*/
        String number="";
        for(int i=0; i<word.length();i++){
            number+=String.valueOf(getNumber(word.charAt(i)));
        }
        return Integer.parseInt(number);
    }

    //main function
    public boolean getResult(String[] words, String Target){
        /*calculates the sum of the number value of each word in the string array
        * calculates the number value of target word
        * return true if the values matches
        * else return false*/
        int sum=0;
        for(int i=0; i<words.length;i++){
            sum+=wordToNumber(words[i]);
        }
        int targetNum=wordToNumber(Target);
        if(sum==targetNum){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        CalWordVal calWordVal =new CalWordVal();
        String[] words = {"SIX","SEVEN","SEVEN"};
        String result = "TWENTY";
        System.out.println(calWordVal.getResult(words,result));
    }
}
