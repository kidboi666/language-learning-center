#include <ncurses.h>

int main() {
	initscr();
	cbreak();
	noecho();
	keypad(stdscr, TRUE);
	
	int height = 10, width = 40;
	WINDOW * win = newwin(height, width, 5, 10);
	box(win, 0, 0);

	mvwprintw(win, 1, 1, "Welcome to C Terminal UI!");
	mvwprintw(win, 3, 1, "Press any key to exit...");

	wrefresh(win);
	getch();

	endwin();
	return 0;
}
