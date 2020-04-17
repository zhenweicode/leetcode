class Solution{
    public static List<String> getNGrayCode(int n){
        if(n <= 0){
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>();
        int i = 2;
        result.add("0");
        result.add("1");
        while(i <= n){
            List<String> temp = Collections.reserve(new ArrayList<>(result));
            for(int i = 0; i < result.size(); i++){
                result.set(i, "0" + result.get(i));
            }

            for(String t : temp){
                result.add("1" + t);
            }

            i++;
        }

        return result;
    }
}