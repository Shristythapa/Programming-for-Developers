/*you are provided certain string and pattern, return true if pattern entirely matches the string otherwise return false.
Note: if pattern contains char @ it matches entire sequence of characters and # matches any single character within
string.
Input: String a=“tt”, pattern =”@”
Output: true
Input: String a=“ta”, pattern =”t”
Output: false
Input: String a=“ta”, pattern =”t#”
Output: true*/
class PatternMatching {
    boolean solve(String str, String pattern, int i, int j){
        /*input :
        str is the string
        * pattern is the pattern the string should match with
        i is the length of the string
        j is the length of the pattern
        */


        //base cases

        //if both string ends at same time
        if(i<0&&j<0){
            return true;
        }

        //if patter string ends but str is left
        //this means that the string doesn't match the pattern
        if(i>=0&&j<0){
            return false;
        }

        //if patten is left but str is ended
        if(i<0&&j>=0){
            for(int k=0; k<=j;k++){
                if(pattern.charAt(k)!='@'){
                    //as @ matches a sequence of character string
                    return false;
                }
            }
            //if @ is the ending left character then string still matches
            return true;
        }

             //matching cases
             if(str.charAt(i)==pattern.charAt(j)||pattern.charAt(j)=='#'){
                 return solve(str, pattern, i-1, j-1);
             }

             else if(pattern.charAt(j)=='@'){
                 return (solve(str,pattern,i-1,j)||solve(str, pattern, i, j-1));
             }

             else{
                 return false;
             }

    }

    boolean match(String str, String pattern){
        //this function takes in string and pattern as input and runs the solve function to implement pattern matching
        int i=str.length()-1;
        int j=pattern.length()-1;


        return solve(str, pattern, i, j );
    }

    public static void main(String[] args) {
        PatternMatching dp = new PatternMatching();
        String str ="abcde";
        String pattern="a@cde";
        System.out.println(dp.match(str,pattern));
    }
}
