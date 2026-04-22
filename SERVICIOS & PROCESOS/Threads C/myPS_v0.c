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

int main(int argc, char *argv[])
{
    pid_t pid;

    if (argc != 2) {
        fprintf(stderr, "Uso: %s <username>\n", argv[0]);
        exit(1);
    }

    if ((pid = fork()) < 0)
        error_y_exit("Error en fork", 1);

    if (pid == 0) {
        printf("Hijo: PID=%d, username=%s\n", getpid(), argv[1]);
        fflush(stdout);
        while(1);
    } else {
        printf("Padre: PID=%d\n", getpid());
        fflush(stdout);
        while(1);
    }

    return 0;
}
