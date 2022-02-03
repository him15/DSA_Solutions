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
class Solution
{
    public int fun(String s)
    {
        // Write your code here
        long emptyCount = 1 , aCount = 0 , bCount = 0 , cCount = 0;
        int mod = (int)(1e9 + 7);
        
        for(int i = 0; i <  s.length() ; i++){
            char ch = s.charAt(i);
            
            if(ch == 'a'){
                aCount = (aCount + (emptyCount + aCount)) % mod;
            }
            else if(ch == 'b'){
                bCount = (bCount + (aCount + bCount))%mod;
                
            }
            else if(ch == 'c'){
                cCount = (cCount + (bCount + cCount)) % mod;
                
            }
        }
        
        return (int)cCount;        
        
    }
}







// -----------------------------------------------139. Word Break--------------------------------------------------
class Solution {
    
    public boolean helper(String str , int i , HashSet<String> dic , int dp[]){
        if(i == str.length() ){
            dp[i] = 1;
            return true;
        }
        
        if( dp[i] != -1 ){
            return dp[i] == 1;
        }
        
        
        for(int j = i + 1; j <= str.length() ; j++){
            String word = str.substring(i , j);
            if(dic.contains(word)){
                boolean recAns = helper(str , j , dic , dp);
                if(recAns){ 
                    dp[i] = 1;
                    return true;
                }
            }
        }
        
        dp[i] = 0;
        return false;
    }
    
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> dic = new HashSet<>();
        for(String word : wordDict){
            dic.add(word);
        }
        int dp[] = new int[s.length() + 1];
        Arrays.fill(dp , -1);
        return helper(s , 0 , dic , dp);
    }
}

// tabular
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> dic = new HashSet<>();
        for(String word : wordDict){
            dic.add(word);
        }
        
        boolean dp[] = new boolean[s.length() + 1];
        dp[0] = true;
        
        for(int i = 0; i < dp.length ; i++){
            for(int j = i + 1; j <= s.length() ; j++){
                String str = s.substring(i , j);
                if(dic.contains(str)){
                    dp[j] = dp[j] || dp[i]; 
                }
            }
        }
        return dp[dp.length - 1];
    }
 }









// -----------------------------------------------------LONGEST INCREASING SUBSEQUECE------------------------------
class Solution {
    
    public int LIS_Memo(int arr[] , int idx , int dp[]){
        
        if(dp[idx] != 0) return dp[idx];
        
        int ans = 1;
        for(int i = idx - 1; i >= 0 ; i--){
            if(arr[i] < arr[idx]){
                ans = Math.max(ans , LIS_Memo(arr , i , dp) + 1);
            }
        }
        
        return dp[idx] = ans;
    }
    
    public int LIS_Tab(int arr[] , int dp[] ){
        int n = arr.length;
        int maxLen = 1;
        for(int i = 0; i < n ; i++){
            int len = 0;
            for(int j = i -1 ; j >= 0 ; j--){
                if(arr[j] < arr[i]){
                    len = Math.max(len , dp[j]);    
                }
            }
            dp[i] = len + 1;
            maxLen = Math.max(maxLen , dp[i]);
        }
        return maxLen;
    }
    
    
    public int lengthOfLIS(int[] arr) {
        int n = arr.length;
        int dp[] = new int[arr.length ];
        // int ans = 0;
        
        // for(int i = n - 1; i >= 0 ; i--){
        //     ans = Math.max(ans , LIS_Memo(arr , i , dp) );
        // }
        int ans = LIS_Tab(arr , dp);
        return ans;
        
    }
}
