package leetcode;

public class CountGoodNumbers1922 {
    Long mod = 1000000007L;

    public int countGoodNumbers(long n) {
        Long res = 5L;
        if(n==1) {
            return 5;
        }
        Long even = (n+1)/2 - 1;
        Long odd = n/2;

        res = ((res*getPow(5L, even))%mod * (getPow(4L, odd)%mod))%mod;
        return res.intValue();
    }

    private Long getPow(Long base, Long power) {
        if(power == 0) {
            return 1L;
        }
        if(power%2 == 0) {
            Long p = getPow(base, power/2);
            return (p*p)%mod;
        }
        return (base*getPow(base, power-1))%mod;
    }

    public static void main(String[] args) {
        System.out.println(new CountGoodNumbers1922().countGoodNumbers(4));
    }

}
