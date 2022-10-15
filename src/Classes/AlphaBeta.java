
package Classes;

public class AlphaBeta {
	public int minimax(Board currentBoard, int alpha, int beta, char currentPlayer) {
		if (currentBoard.finished) return utility(currentBoard, currentPlayer);
		if (currentBoard.player == currentPlayer) return max_node(currentBoard, alpha, beta, currentPlayer);
		else return min_node(currentBoard, alpha, beta, currentPlayer);
	}

	int utility(Board currentBoard, char player) {
		char vs = player == 'x' ? 'o' : 'x';
		if (currentBoard.finished && currentBoard.winner == player) return 1;
		else if (currentBoard.finished && currentBoard.winner == vs) return -1;
		else return 0;
	}

	int max_node(Board currentBoard, int alpha, int beta, char currentPlayer) {
		int m = Integer.MIN_VALUE;
		int index = -1;

		for (Integer moves : currentBoard.availableMoves) {
			Board newBoard = currentBoard.copyThis();
			newBoard.move(moves/3, moves%3);

			int value = minimax(newBoard, alpha, beta, currentPlayer);

			if (value > m) {
				m = value;
				index = m;
			}

			if (m >= beta) {
				if (index > -1) currentBoard.move(index/3, index%3);
				return m;
			}

			if (m > alpha) {
				alpha = value;
				index = moves;			
			}
		}

		if (index > -1) currentBoard.move(index/3, index%3);
		return m;
	}

	int min_node(Board currentBoard, int alpha, int beta, char currentPlayer) {
		int m = Integer.MAX_VALUE;
		int index = -1;

		for (Integer moves : currentBoard.availableMoves) {
			Board newBoard = currentBoard.copyThis();
			newBoard.move(moves/3, moves%3);

			int value = minimax(newBoard, alpha, beta, currentPlayer);
			if (value < m) {
				m = value;
				index = moves;
			}

			if (m <= alpha) {
				if (index > -1) currentBoard.move(index/3, index%3);
				return m;			
			}

			if (m < beta) {
				beta = value;
				index = moves;			
			}
		}

		if (index > -1) currentBoard.move(index/3, index%3);
		return m;
	}

}