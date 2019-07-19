package test.c03_mianshi;

public class Test3 {
    public static void main(String[] args) {
        print(3);
    }

    public static void print(int n) {
        int temp = 1;
        while (temp <= n) {
            for (int i = temp; i < n; i++) {
                System.out.print("           ");
            }
            for (int i = 0; i < 2 * temp - 1; i++) {
                System.out.print("     *     ");
            }
            System.out.println();
            temp++;
        }
        temp --;
        while (temp >= 0) {
            temp--;
            for (int i = temp; i < n; i++) {
                System.out.print("           ");
            }
            for (int i = 0; i < 2 * temp - 1; i++) {
                System.out.print("     *     ");
            }
            System.out.println();

        }
    }
}
