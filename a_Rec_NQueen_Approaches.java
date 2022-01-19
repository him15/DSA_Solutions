import java.util.*;

public class Main{
   
    
    
    public static int comb_2D(boolean board[][] , int tnq , int bno , String ans){
        if(tnq == 0){
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        int n = board.length;
        int m = board[0].length;
        for(int i = bno ; i < n * m ; i++){
            int r = i / m;
            int c = i % m;
            
             count += comb(board , tnq - 1 , i + 1 , ans + " ("+ r +","+c+") ");
        }
        return count;
    }
    
    public static int perm_2D(boolean board[][] , int tnq , int bno , String ans){
        if(tnq == 0){
            // System.out.println(ans);
            return 1;
        }
        int count = 0;
        int n = board.length;
        int m = board[0].length;
        for(int i = 0 ; i < n * m ; i++){
            int r = i / m;
            int c = i % m;
            if(!board[r][c]){
                board[r][c] = true;
                count += perm(board , tnq - 1 , i, ans + " ("+ r +","+c+") ");
                board[r][c] = false;
            }
        }
        return count;
    }
    
    public static int comb_sub_1d(int tnq , int tnb , int qno , int bno , String ans){
        if(bno == tnb || qno == tnq){
            if(qno == tnq){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int count = 0;
        
        count += comb_sub(tnq , tnb , qno + 1 , bno + 1 , ans + " b"+bno+"-q"+qno);
        count += comb_sub(tnq , tnb , qno , bno + 1 , ans);
        
        return count;
    }
    
    public static int perm_sub_1d(boolean board[] , int tnq , int tnb , int qno , int bno , String ans){
        if(bno == tnb || qno == tnq){
            if(qno == tnq){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int count = 0;
        
        if(!board[bno]){
            board[bno] = true;
            count += perm_sub_1d(board,tnq , tnb , qno + 1 , 0 , ans + " b"+bno+"-q"+qno);
            board[bno] = false;
        }
        count += perm_sub_1d(board , tnq , tnb , qno , bno + 1 , ans);
        
        return count;
    }
    
    public static boolean isSafeToPlaceQueen(boolean[][] board , int r , int c){
        int dirs[][] = { {0,-1},{-1,-1},{-1,0},{-1,1},{1,0},{1,1},{0,1},{1,-1}};
        int n = board.length;
        int m = board[0].length;
        for(int dir[] : dirs){
            for(int rad = 1; rad < n ; rad++){
                int x = r + rad * dir[0];
                int y = c + rad * dir[1];
                
                if(x >= 0 && y >= 0 && x < n && y < m){
                    if(board[x][y]) return false;
                }
                else{
                    break;
                }
            }
        }
        return true;
        
    }
    
    public static int nqueen_01(boolean[][] board , int tnq , int idx , String ans){
        if(tnq == 0){
            System.out.println(ans);
            return 1;
        }
        
        int count = 0;
        int n = board.length , m = board[0].length;
        for(int i = idx; i < n * m ; i++){
            int r = i / m;
            int c = i % m;
            
            if(isSafeToPlaceQueen(board , r , c) && !board[r][c]){
                board[r][c] = true;
                count += nqueen_01(board , tnq - 1 , 0 , ans +" ("+r+","+c+") ");
                board[r][c] = false;
            }
        }
        return count;
    }
    
    public static int nqueen_01_subsequence(boolean[][] board , int tnq , int idx , String ans){
        int n = board.length , m = board[0].length;
        if(idx >= n * m || tnq == 0){
            if(tnq == 0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        
        int count = 0;
            
            int r = idx / m;
            int c = idx % m;
            if(isSafeToPlaceQueen(board , r , c)){
                board[r][c] = true;
                count += nqueen_01_subsequence(board , tnq - 1 , idx + 1  , ans +" ("+r+","+c+") ");
                board[r][c] = false;
            }
            count += nqueen_01_subsequence(board , tnq , idx + 1  , ans);
            
        return count;
    }
    
    // ===============================================================================================
    
    
    // using Shadow concept -> by taking some extra space
    static boolean row[];
    static boolean col[];
    static boolean dia[];
    static boolean antidia[];
    public static int nqueen_02(int n , int m , int tnq , int idx , String ans){
        if(tnq == 0){
            System.out.println(ans);
            return 1;
        }
        
        int count = 0;
        for(int i = idx; i < n * m ; i++){
            int r = i / m;
            int c = i % m;
            
            if(!row[r] && !col[c] && !dia[r+c] && !antidia[r - c + m - 1]){
                row[r] = col[c] = dia[r+c] = antidia[r - c + m - 1] = true;
                count += nqueen_02(n , m , tnq - 1 , i + 1 , ans +" ("+r+","+c+") ");   // if put 0 instead of i + 1 then it will be permutation
                row[r] = col[c] = dia[r+c] = antidia[r - c + m - 1] = false;
            }
        }
        return count;
    }
    
    
    
    // ================================ Assign one queen to each floor =========================================
    public boolean row[];
    public boolean col[];
    public boolean dia[];
    public boolean antidia[];
    
    public int nqueen_03(int n , int r){
        if(r == n){
            return 1;
        }
        
        int count = 0;
        for(int c = 0; c < n ; c++){
            
            if(!row[r] && !col[c] && !dia[r + c] && !antidia[r - c + n - 1]){
                row[r] = col[c] = dia[r + c] = antidia[r - c + n - 1] = true;
                count += helper(n , r + 1);
                row[r] = col[c] = dia[r + c] = antidia[r - c + n - 1] = false;
            }
        }
        return count;
        
    }
    
    int row = 0;
    int col = 0;
    int dia = 0;
    int antidia = 0;
    
    public int nqueen_04(int n , int r ){
        if(r == n){
            return 1;
        }
        
        
        int count = 0;
        for(int c = 0; c < n ; c++){
            
            // is safe
            if( ( (row & (1<<c)) == 0 ) && ( (col & (1<<c)) == 0) && ( (dia & (1<< (r + c))) == 0) &&  ( (antidia & (1<< (r - c + n - 1))) == 0)  ){
                row ^= (1<<c);
                col ^= (1<<c);
                dia ^= (1<< (r + c));
                antidia ^= (1<< (r - c + n - 1));
                count += helper(n , r + 1);
                row ^= (1<<c);
                col ^= (1<<c);
                dia ^= (1<< (r + c));
                antidia ^= (1<< (r - c + n - 1));
            }
            
        }
        
        return count;
    }
    
    public static void main(String args[]){
        
        // int tnb = 5;
        // int tnq= 3;
        // boolean board[][] = new boolean[4][4];
        // boolean board[] = new boolean[tnb];
        // int count = comb(board , 4 , 0 , "");
        // int count = perm(board , 4 , 0 , "");
        // int count = comb_sub_1d(tnq,tnb ,0 , 0 , "");
        // int count = perm_sub_1d(board , tnq,tnb ,0 , 0 , "");
        // int count = nqueen_01(board , 4 , 0 , "");
        // int count = nqueen_01_subsequence(board , 4 , 0 , "");
        
        // int n = 4;
        // int m = 4;
        // row =new boolean[n];
        // col =new boolean[m];
        // dia = new boolean[n + m - 1];
        // antidia = new boolean[n + m - 1];
        // int count = nqueen_02(n , m ,  4 , 0 , "");
        
        
        
        // System.out.println(count);
    }
}
