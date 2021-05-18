import java.util.Random;

public class JobSchedulling {
    public static void main(String[] args) {
        int n = 16;      // n = 작업의 갯수
        int m = 2;     // m = 기계의 갯수
        int[] t = new int[n];       // t = 각 작업의 수행 시간
        Random random = new Random();
        System.out.print("작업 시간 : ");
        for (int i = 0; i < n; i++) {
            t[i] = random.nextInt(10) + 1;
            System.out.printf("%d ", t[i]);
        }
        System.out.println();
        System.out.println(Approx_schedule(n, m, t));
    }

    public static int Approx_schedule(int n, int m, int[] t) {
        int[] L = new int[m];       // L = 각 기계의 마지막 작업 종료 시간
        for (int j = 0; j < m; j++) {
            L[j] = 0;
        }
        // 제일 먼저 끝나는 기계에 작업 배정
        for (int i = 0; i < n; i++) {
            int min = 0;
            for (int j = 1; j < m; j++) {
                if (L[j] < L[min]) {
                    min = j;
                }
            }
            L[min] = L[min] + t[i];
        }
        // 가장 마미가으로 종료되는 시간 찾기
        int max = L[0];
        for (int i = 1; i < m; i++) {
            if (L[i] > max) {
                max = L[i];
            }
        }
        return max;
    }
}
