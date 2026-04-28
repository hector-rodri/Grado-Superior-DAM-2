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
    char c;

    if (argc < 2) {
        fprintf(stderr, "Uso: %s <username1> [username2] ...\n", argv[0]);
        exit(1);
    }

    for (i = 1; i < argc; i++) {

        if ((pid = fork()) < 0)
            error_y_exit("Error en fork", 1);

        if (pid == 0) {
            printf("Hijo: PID=%d, username=%s\n", getpid(), argv[i]);
            fflush(stdout);
            muta_a_PS(argv[i]);
        }
    }

    while (waitpid(-1, NULL, 0) > 0);

    printf("Padre: PID=%d, todos los hijos han terminado\n", getpid());
    fflush(stdout);

    printf("Pulsa una tecla para continuar...\n");
    fflush(stdout);
    if (read(0, &c, sizeof(char)) < 0)
        error_y_exit("Error en read", 1);

    return 0;
}
