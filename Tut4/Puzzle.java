class Puzzle {
    private final Grid2D<String> grid;
    private final int numOfCols;

    Puzzle(int n) {
        this.grid = this.populate(n);
        this.numOfCols = grid.getNumOfCols();
    }

    Puzzle(Grid2D<String> grid, int n) {
        this.grid = grid;
        this.numOfCols = n;
    }

    Grid2D<String> populate(int n) {
        int size = n * n - 1;
        Grid2D<String> grid = new Grid2D<String>(n);
        for (int i = 1; i <= size; i++) {
            grid = grid.add(String.format("%d", i));
        }
        grid = grid.add(".");
        return grid;
    }

    Puzzle move(int n) { 
        Grid2D<String> grid = this.grid;
        int iNum = 0;
        int jNum = 0;
        int iEmptyCell = 0;
        int jEmptyCell = 0;
        boolean foundNum = false;
        boolean foundEmpty = false;
        // Capture location of cell to be moved and empty cell
        for (int i = 0; i < grid.getNumOfRows(); i++) {
            for (int j = 0; j < grid.getNumOfCols(); j++) {
                if (grid.get(i, j).equals(String.valueOf(n))) {
                    iNum = i;
                    jNum = j;
                    foundNum = true;
                }
                if (grid.get(i, j) == ".") {
                    iEmptyCell = i;
                    jEmptyCell = j;
                    foundEmpty = true;
                }
                if (foundNum && foundEmpty) {
                    break;
                }
            }
        }

        if (isNeighbour(iNum, jNum, iEmptyCell, jEmptyCell)) {
            grid = grid.set(iEmptyCell, jEmptyCell, String.valueOf(n));
            grid = grid.set(iNum, jNum, ".");
        }
        return new Puzzle(grid, this.numOfCols);
    }

    boolean isNeighbour(int i1, int j1, int i2, int j2) {
        return (Math.abs(i1 - i2) == 1 && j1 == j2 || // same col
                Math.abs(j1 - j2) == 1 && i1 == i2);  // same row
    }

    public boolean isSolved() {
        return this.toString().equals(new Puzzle(this.numOfCols).toString());
    }

    @Override
    public String toString() {
        String string = "";
        for (int i = 0; i < this.grid.getNumOfRows(); i++) {
            string += "\n";
            for (int j = 0; j < this.grid.getNumOfCols(); j++) {
                string = string + this.grid.get(i, j);
                if (j < this.grid.getNumOfCols() - 1) {
                    string += " ";
                }
            }        
        }
        return string.toString();
    }
}
