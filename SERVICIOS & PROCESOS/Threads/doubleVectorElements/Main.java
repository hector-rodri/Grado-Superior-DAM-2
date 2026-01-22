public class Main {

    public static void main(String[] args) throws InterruptedException {
        int N = 100000013;
        int[] v = new int[N];
        int P = 4;// the program should work for any P <= N

        for (int i = 0; i < N; i++) {
            v[i] = i;
        }
        // Parallelize me using P threads
        Thread[] threads = new Thread[P];
        int chunkSize = (N + P - 1) / P;

        for (int t = 0; t < P; t++) {
            final int start = t * chunkSize;
            final int end = Math.min(start + chunkSize, N);

            threads[t] = new Thread(() -> {
                for (int i = start; i < end; i++) {
                    v[i] = v[i] * 2;
                }
            });

            threads[t].start();
        }
        for (int t = 0; t < P; t++) {
            threads[t].join();
        }
        for (int i = 0; i < N; i++) {
            if (v[i] != i * 2) {
                System.out.println("Wrong answer at index " + i);
                System.exit(1);
            }
        }
        System.out.println("Correct");
    }
}
