package reversString;

public class Runner {
    public static void main(String[] args) {
        String str = "abcdifg";
        StringBuilder sb = new StringBuilder(str);
        System.out.println(sb);
        sb = sb.reverse();
        System.out.println(sb);
    }
}
