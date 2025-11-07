#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_TASKS 100
#define MAX_LENGTH 256
#define FILE_NAME "tasks.txt"

typedef struct {
    int id;
    char description[MAX_LENGTH];
    int completed;
} Task;

void load_tasks(Task tasks[], int *count) {
    FILE *file = fopen(FILE_NAME, "r");
    if (!file) {
        *count = 0;
        return;
    }

    *count = 0;
    while (fscanf(file, "%d|%d|%[^\n]",
                  &tasks[*count].id,
                  &tasks[*count].completed,
                  tasks[*count].description) == 3) {
        (*count)++;
        if (*count >= MAX_TASKS) break;
    }

    fclose(file);
}

void save_tasks(Task tasks[], int count) {
    FILE *file = fopen(FILE_NAME, "w");
    if (!file) {
        printf("Error: Cannot save tasks\n");
        return;
    }

    for (int i = 0; i < count; i++) {
        fprintf(file, "%d|%d|%s\n",
                tasks[i].id,
                tasks[i].completed,
                tasks[i].description);
    }

    fclose(file);
}

int get_next_id(Task tasks[], int count) {
    int max_id = 0;
    for (int i = 0; i < count; i++) {
        if (tasks[i].id > max_id) {
            max_id = tasks[i].id;
        }
    }
    return max_id + 1;
}

void add_task(char *description) {
    Task tasks[MAX_TASKS];
    int count;

    load_tasks(tasks, &count);

    if (count >= MAX_TASKS) {
        printf("Error: Task list is full\n");
        return;
    }

    tasks[count].id = get_next_id(tasks, count);
    strncpy(tasks[count].description, description, MAX_LENGTH - 1);
    tasks[count].description[MAX_LENGTH - 1] = '\0';
    tasks[count].completed = 0;
    count++;

    save_tasks(tasks, count);
    printf("Task added: %s (ID: %d)\n", description, tasks[count - 1].id);
}

void list_tasks() {
    Task tasks[MAX_TASKS];
    int count;

    load_tasks(tasks, &count);

    if (count == 0) {
        printf("No tasks found. Add one with: todo add \"Your task\"\n");
        return;
    }

    printf("\nTODO LIST\n");
    printf("─────────────────────────────────────────────────\n");

    for (int i = 0; i < count; i++) {
        char status = tasks[i].completed ? 'X' : ' ';

        printf("[%c] %d. %s\n",
               status,
               tasks[i].id,
               tasks[i].description);
    }

    printf("─────────────────────────────────────────────────\n");
    printf("Total: %d tasks\n\n", count);
}

void complete_task(int id) {
    Task tasks[MAX_TASKS];
    int count;

    load_tasks(tasks, &count);

    int found = 0;
    for (int i = 0; i < count; i++) {
        if (tasks[i].id == id) {
            tasks[i].completed = 1;
            found = 1;
            save_tasks(tasks, count);
            printf("Task %d marked as complete\n", id);
            break;
        }
    }

    if (!found) {
        printf("Error: Task ID %d not found\n", id);
    }
}

void delete_task(int id) {
    Task tasks[MAX_TASKS];
    int count;

    load_tasks(tasks, &count);

    int found = -1;
    for (int i = 0; i < count; i++) {
        if (tasks[i].id == id) {
            found = i;
            break;
        }
    }

    if (found == -1) {
        printf("Error: Task ID %d not found\n", id);
        return;
    }

    for (int i = found; i < count - 1; i++) {
        tasks[i] = tasks[i + 1];
    }
    count--;

    save_tasks(tasks, count);
    printf("Task %d deleted\n", id);
}

void clear_all() {
    remove(FILE_NAME);
    printf("All tasks cleared\n");
}

void show_help() {
    printf("\nTODO - Simple Task Manager\n\n");
    printf("Usage:\n");
    printf("  todo add \"Task description\"    Add a new task\n");
    printf("  todo list                      Show all tasks\n");
    printf("  todo done <id>                 Mark task as complete\n");
    printf("  todo delete <id>               Delete a task\n");
    printf("  todo clear                     Delete all tasks\n");
    printf("  todo help                      Show this help\n\n");
}

int main(int argc, char *argv[]) {
    if (argc < 2) {
        show_help();
        return 1;
    }

    char *command = argv[1];

    if (strcmp(command, "add") == 0) {
        if (argc < 3) {
            printf("Error: Please provide a task description\n");
            printf("Usage: todo add \"Your task\"\n");
            return 1;
        }
        add_task(argv[2]);

    } else if (strcmp(command, "list") == 0) {
        list_tasks();

    } else if (strcmp(command, "done") == 0) {
        if (argc < 3) {
            printf("Error: Please provide a task ID\n");
            printf("Usage: todo done <id>\n");
            return 1;
        }
        complete_task(atoi(argv[2]));

    } else if (strcmp(command, "delete") == 0) {
        if (argc < 3) {
            printf("Error: Please provide a task ID\n");
            printf("Usage: todo delete <id>\n");
            return 1;
        }
        delete_task(atoi(argv[2]));

    } else if (strcmp(command, "clear") == 0) {
        clear_all();

    } else if (strcmp(command, "help") == 0) {
        show_help();

    } else {
        printf("Unknown command: %s\n", command);
        show_help();
        return 1;
    }

    return 0;
}