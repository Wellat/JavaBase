package hemi.subject.jobs;

import java.util.Scanner;

/**
 * Created by Hemi on 2017/4/26.
 */
public class temp {

}
/*
class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String str = sc.nextLine();
            String pattern = sc.nextLine();
            if(pattern.length()>str.length()){
                System.out.println(0);
                return;
            }
            int patternIndex = 0;
            int strIndex = 0;
            for(int i=0;i<pattern.length();i++){
                char p = pattern.charAt(i);
                if(p=='?'){
                    i++;
                }else if(p=='*'){
                    i++;
                }
                for(int j=0;j<str.length();j++){
                    if(pattern.charAt(i) == str.charAt(j)){

                    }
                }
            }


            for(int i=0;i<str.length();i++){
                if(str.charAt(i)==pattern.charAt(strIndex)){
                    ++strIndex;
                    if(pattern.charAt(strIndex)=='?'){

                    }else if(pattern.charAt(strIndex)=='*'){

                    }
                }
            }

        }
        sc.close();
    }
    public static void match(String str,String pattern){

    }
}
*/
