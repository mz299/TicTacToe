import java.util.Scanner;

public class TicTacToe {
	enum state {
		X,
		O,
		EMPTY,
	}
	enum gameState {
		CONTINUE,
		WIN,
		DRAW,
	}
	
	public static int size = 3;
	private state board[][] = new state[size][size];
	
	TicTacToe() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j] = state.EMPTY;
			}
		}
	}

	public void displayBoard() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				switch (board[i][j]) {
					case EMPTY:
					System.out.print(" ");
					break;
					case X:
					System.out.print("X");
					break;
					case O:
					System.out.print("O");
					break;
					default:
				}
			}
			System.out.println();
		}
	}
	
	public boolean place(boolean firstPlayer, int row, int column) {
		if (row < 0 || row >= size) {
			return false;
		}
		if (column < 0 || column >= size) {
			return false;
		}
		if (board[row][column] != state.EMPTY) {
			return false;
		}
		
		if (firstPlayer) {
			board[row][column] = state.X;
		} else {
			board[row][column] = state.O;
		}
		
		return true;
	}

	public gameState checkGameState(int row, int column) {
		state s = board[row][column];
		for (int i = 0; i < size; i++) {
			if (board[row][i] != s) {
				break;
			}
			if (i == size - 1) {
				return gameState.WIN;
			}
		}
		for (int i = 0; i < size; i++) {
			if (board[i][column] != s) {
				break;
			}
			if (i == size - 1) {
				return gameState.WIN;
			}
		}
		for (int i = 0; i < size; i++) {
			if (board[i][size - 1 - i] != s) {
				break;
			}
			if (i == size - 1) {
				return gameState.WIN;
			}
		}

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (board[i][j] == state.EMPTY) {
					return gameState.CONTINUE;
				}
			}
		}
		return gameState.DRAW;
	}
	
	public static void main(String args[]) {
		TicTacToe game = new TicTacToe();
		boolean firstPlayerMove = true;
		System.out.printf("Tic-Tac-Toe(%d X %d) Begin!\n", size, size);
		while (true) {
			if (firstPlayerMove) {
				System.out.println("First Player Move");
			} else {
				System.out.println("Second Player Move");
			}
			System.out.println("Row:");
			int row = extracted().nextInt();
			System.out.println("Column:");
			int column = extracted().nextInt();
			while (!game.place(firstPlayerMove, row, column)) {
				System.out.println("Cannot place here!");
				System.out.println("Row:");
				row = extracted().nextInt();
				System.out.println("Column:");
				column = extracted().nextInt();
			}
			game.displayBoard();
			switch (game.checkGameState(row, column)) {
			case CONTINUE:
				break;
			case DRAW:
				System.out.println("Draw!");
				return;
			case WIN:
				System.out.println("WIN!");
				return;
			default:
				break;
			}
			firstPlayerMove = !firstPlayerMove;
		}
	}

	private static Scanner extracted() {
		return new Scanner(System.in);
	}
}
