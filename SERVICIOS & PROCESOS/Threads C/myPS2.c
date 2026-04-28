#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

void error_y_exit(char *msg, int exit_status)
{
    perror(msg);
    exit(exit_status);
}

void muta_a_PS(char *username)
{
    execlp("ps", "ps", "-u", username, (char*)NULL);
    error_y_exit("Ha fallado la mutación al ps", 1);
}

int main(int argc, char *argv[])
{
    pid_t pid;
    int i;

    if (argc < 2) {
        fprintf(stderr, "Uso: %s <username1> [username2] ...\n", argv[0]);
        exit(1);
    }

    for (i = 1; i < argc; i++) {

        if ((pid = fork()) < 0)
            error_y_exit("Error en fork", 1);

        if (pid == 0) {
            /* Proceso hijo */
            printf("Hijo: PID=%d, username=%s\n", getpid(), argv[i]);
            fflush(stdout);
            muta_a_PS(argv[i]);
        }

        if (waitpid(pid, NULL, 0) < 0)
            error_y_exit("Error en waitpid", 1);
    }

    printf("Padre: PID=%d, todos los hijos han terminado\n", getpid());
    return 0;
}
