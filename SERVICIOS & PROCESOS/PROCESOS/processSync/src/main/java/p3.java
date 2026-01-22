public class p3 {
    public static void main(String[] args) throws Exception {
        long pid = ProcessHandle.current().pid();
        String senyal = args[0];
        String pidPare = args[1];

        System.out.println("Procés p3 iniciat. ");
        System.out.println("PID: " + pid);
        System.out.println("Enviant senyal " + senyal + " al procés pare (" + pidPare + ")");

        Process p = new ProcessBuilder("kill", "-" + senyal, pidPare).start();
        int exitCode = p.waitFor(); 

        if (exitCode == 0) {
            System.out.println(senyal + " enviat al procés p");
        } else {
            System.err.println("Error en enviar " + senyal);
        }
    }
}