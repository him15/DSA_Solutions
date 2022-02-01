// --------------------------132. Palindrome Partitioning II-----------------------------------



class Solution {
    
    public int minCut_helper(boolean pdp[][] , String str , int idx , int dp[]){
        if(idx == str.length()){
            return 0;
        }
        
        if(dp[idx] != -1){
            return dp[idx];
        }
        
        int cut = Integer.MAX_VALUE;
        for(int j = idx; j < str.length() ; j++){
            
            if(pdp[idx][j]){
                int a = minCut_helper(pdp , str , j + 1 , dp) + 1;
                cut = Math.min(cut , a);
            }
        }
        
        return dp[idx] = cut;
    }
    
    
    public int minCut(String str) {
        int n = str.length();
        boolean dp[][] = new boolean[n][n];
        for(int gap = 0; gap < n ; gap++){
            for(int i = 0,j = gap; i < n && j < n ; i++,j++){
                if(gap==0){
                    dp[i][j] = true;
                }
                else if(gap == 1 && str.charAt(i) == str.charAt(j) ){
                    dp[i][j] = true;
                }
                else{
                    dp[i][j] = (dp[i + 1][j-1] && str.charAt(i)==str.charAt(j));
                }
            }
        }
        
        int pdp[] = new int[n];
        Arrays.fill(pdp , -1);
        
        return minCut_helper( dp , str , 0 , pdp ) - 1;
        
    }
}



// -------------------------------------GFG -> Count subsequences of type a^i, b^j, c^k  -------------------------------------



