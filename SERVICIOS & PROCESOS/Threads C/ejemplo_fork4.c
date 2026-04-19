#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>
#include <sys/wait.h>

int main(int argc, char *argv[])
{
    int pid, i;
    char buffer[80];

    sprintf(buffer, "Antes del fork: Soy el proceso %d\n", getpid());
    write(1, buffer, strlen(buffer));

    pid = fork();

    switch (pid) {
        case 0:
            sprintf(buffer, "HIJO: Soy el proceso %d\n", getpid());
            write(1, buffer, strlen(buffer));

            for (i = 0; i < 10000; i++) ;

            sprintf(buffer, "HIJO acaba: Soy el proceso %d\n", getpid());
            write(1, buffer, strlen(buffer));

            exit(0);

        case -1:
            sprintf(buffer, "Se ha producido un error\n");
            write(1, buffer, strlen(buffer));
            break;

        default:
            sprintf(buffer, "PADRE: Soy el proceso %d\n", getpid());
            write(1, buffer, strlen(buffer));

            waitpid(pid, NULL, 0);
    }

    sprintf(buffer, "Solo lo ejecuta el padre: Soy el proceso %d\n", getpid());
    write(1, buffer, strlen(buffer));

    return 0;
}
