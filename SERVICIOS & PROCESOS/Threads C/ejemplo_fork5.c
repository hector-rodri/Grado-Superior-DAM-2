#include <unistd.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <sys/wait.h>

char variable_global = 'A';

int main(int argc, char *argv[])
{
    int pid;
    char variable_local = 'a';
    char buffer[80];

    sprintf(buffer, "Antes del fork: Soy el proceso %d\n", getpid());
    write(1, buffer, strlen(buffer));

    pid = fork();

    switch (pid) {
        case 0:
            sprintf(buffer, "HIJO: Soy el proceso %d\n", getpid());
            write(1, buffer, strlen(buffer));

            variable_local = 'z';
            variable_global = 'Z';

            sprintf(buffer, "HIJO:La variable_global vale %c y la local %c\n",
                    variable_global, variable_local);
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

            sprintf(buffer, "PADRE:La variable_global vale %c y la local %c\n",
                    variable_global, variable_local);
            write(1, buffer, strlen(buffer));
    }

    sprintf(buffer, "Solo lo ejecuta el padre: Soy el proceso %d\n", getpid());
    write(1, buffer, strlen(buffer));

    return 0;
}
