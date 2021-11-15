import java.util.*;
public class Quin {
    ArrayList<ArrayList<Integer> > x = new ArrayList<ArrayList<Integer> >();
    HashMap<String , ArrayList<Integer>> map=new HashMap<String,ArrayList<Integer>>();


    public int findbitchange(String a , String b) {
        int ind = -1;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                if(ind != -1){
                    return -1;
                }
                ind = i;
            }
        }
        // for(int i = 0 ; i  < count.length ; i++){
        //     System.out.print(count[i]);
        // }
        // System.out.println();
        return ind;
    }


    public int decimal(String s){
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            sum += (int)Math.pow(2, s.length() - i - 1) * (s.charAt(i) - '0');
        }
        return sum;
    }


    public int findsum(int[] n){
        int sum = 0;
        for (int i = 0; i < n.length; i++) {
            if(n[i] != 0){
                sum++;
            }
        }
        return sum;
    }


    public int countchar(String str, char ch) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }


    public void printarr(String[][] arr){
        for(int i = 0 ; i < arr.length ; i++){
            for(int j = 0 ; j < arr[0].length ; j++){
                if(arr[i][j] != null)
                    System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println(x);
        System.out.println(Arrays.asList(map));
    }


    public String reverse(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append(s);  
        return sb.reverse().toString();
    }


    public String findbinary(int num , int nobits){
        String s = "";  
        while(num>0){
            int rem=num%2;
            s+=rem;
            num/=2;
        }
        String rev = reverse(s);
        while( rev.length()<nobits){
            rev = "0"+rev;
        }
        return rev;
    }

    

    public void step1(int[] n){
        int size = n.length;
        int nobits = (int)Math.ceil((Math.log(n[n.length-1]) / Math.log(2)));
        System.out.println(nobits);
        String array[] = new String[size];
        for (int i = 0; i < n.length; i++) {
            array[i] = findbinary(n[i] , nobits);
            x.add(new ArrayList<Integer>(Arrays.asList(n[i])));
            // System.out.println(array[i]);
        }


        String arr[][] = new String[size][size];
        for(int i = 0 ; i < array.length ; i++){
            int count = countchar(array[i],'1');
            // System.out.println(count);
            int j= 0;
            while (j < size){
                if(arr[count][j] == null){
                    arr[count][j] = array[i];
                    break;
                }
                j++;
            }
        }
        printarr(arr);
        String [][] s2o = step2(arr , nobits);
        printarr(s2o);
        String [][] s3o = step3(s2o , nobits);
        printarr(s3o);
        String [][] s4o = step3(s3o , nobits);
        printarr(s4o);

    }


    public String[][] step2(String[][] arr , int nobits){
        String[][] s2 = new String[arr.length][arr.length];
        int check = 0;
        int i = 0 , j = 0 , st = -1 , en = -1;
        while(true){
            if(i == arr.length - 1){
                break;
            }
            if(j == arr[0].length){
                i++;
                j = 0;
            }
            if(arr[i][j] == null){
                if(j >= arr[0].length){
                    i++;
                    j = 0;
                }
                else{
                    j++;
                }
                continue;
            }else if(st != -1 && en != -1 && check == 0){
                for(int a = st+1 ; a < st+2 ; a++){
                    for(int b = 0 ; b < arr[0].length ; b++){
                        if(arr[a][b] == null){
                            continue;
                        }
                        int c = findbitchange(arr[a][b],arr[st][en]);
                        if(c!=-1){
                            x.add(new ArrayList<Integer>(Arrays.asList(decimal(arr[a][b]) ,decimal(arr[st][en]) )));
                            String s2a = "";
                            for(int d = 0 ; d < nobits ; d++){
                                if(d == c){
                                    s2a += "X";
                                }else{
                                    s2a += arr[a][b].charAt(d);
                                }
                            }
                            map.put(s2a , new ArrayList<Integer>(Arrays.asList(decimal(arr[a][b]) ,decimal(arr[st][en]) )));
                            int e = 0;
                            while(e < s2[0].length){
                                if(s2[st][e] == null){
                                    s2[st][e] = s2a;
                                    break;
                                }
                                e++;
                            }
                        }else{
                            continue;
                        }
                    }
                    check = 1;
                }
            }else{
                st = i;
                en = j;
                
            }if(check == 1){
                st  = -1 ; en  = -1;
                check = 0;
                if(j >= arr[0].length){
                    i++;
                }
                else {
                    j++;
                }
            }
        }
        return s2;
    }


    public String [][] step3(String[][] arr , int nobits)  {
        String[][] s2 = new String[arr.length][arr.length];
        int check = 0;
        int i = 0 , j = 0 , st = -1 , en = -1;
        while(true){
            if(i == arr.length - 1){
                break;
            }
            if(j == arr[0].length){
                i++;
                j = 0;
            }
            if(arr[i][j] == null){
                if(j >= arr[0].length){
                    i++;
                    j = 0;
                }
                else{
                    j++;
                }
                continue;
            }else if(st != -1 && en != -1 && check == 0){
                for(int a = st+1 ; a < st+2 ; a++){
                    for(int b = 0 ; b < arr[0].length ; b++){
                        if(arr[a][b] == null){
                            continue;
                        }
                        int c = findbitchange(arr[a][b],arr[st][en]);
                        if((c) != -1){
                            ArrayList<Integer> temp = new ArrayList<Integer>(map.get(arr[a][b]));
                            temp.addAll(map.get(arr[st][en]));
                            // if((temp.size()) > nobits + 1){
                            //     continue;
                            // }
                            x.add(temp);

                            String s2a = "";
                            for(int d = 0 ; d < nobits ; d++){
                                if(d == c){
                                    s2a += "X";
                                }else{
                                    s2a += arr[a][b].charAt(d);
                                }
                            }
                            map.put(s2a , temp);
                            int e = 0;
                            while(e < s2[0].length){
                                if(s2[st][e] == null){
                                    s2[st][e] = s2a;
                                    break;
                                }
                                e++;
                            }
                        }else{
                            continue;
                        }
                    }
                    check = 1;
                }
            }else{
                st = i;
                en = j;
                
            }if(check == 1){
                st  = -1 ; en  = -1;
                check = 0;
                if(j >= arr[0].length){
                    i++;
                }
                else {
                    j++;
                }
            }
        }
        return s2;
    } 
    // public void McCluskeyAlgorithm(){

    // }
    public static void main(String[] args) {
        Quin q = new Quin();
        System.out.println("Hello");
        int[] a = {0,1,2,3,4,5,6,7};
        q.step1(a);
        
    }
}
