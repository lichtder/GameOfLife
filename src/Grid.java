public class Grid {
    public int width;
    int height;
    private Cell[][] grid;

    public Grid(final int width, final int height) {
        this.width = width;
        this.height = height;

        makeGrid();
    }

    public Grid(String[] string) throws Exception {
        interpretGrid(string);
    }

    private void makeGrid() {
        grid = new Cell[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = new Cell(x, y, false, this);
            }
        }
    }

    private void interpretGrid(String[] string) throws Exception {
        int firstLength = string[0].length();

        for (String s : string) {
            if (firstLength == s.length()) {
                continue;
            } else {
                throw new Exception("All lines should be the same length.");
            }
        }

        height = string.length;
        width = firstLength;

        grid = new Cell[height][width];

        for (int y = 0; y < string.length; y++) {
            for (int x = 0; x < string[y].length(); x++) {
                if (string[y].charAt(x) == 'O') {
                    grid[y][x] = new Cell(x, y, false, this);
                } else if (string[y].charAt(x) == '*') {
                    grid[y][x] = new Cell(x, y, true, this);
                } else {
                    throw new Exception((x + 1) + ". character at the " + (y + 1) + ". line is not allowed.");
                }
            }
        }
    }

    public void displayGrid() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if(!grid[y][x].isAlive) {
                    System.out.print("O ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public void getNextGeneration() {
        Cell[][] newGeneration = new Cell[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Cell currentCell = grid[y][x];
                int signature = currentCell.getNeighborhoodSignature();

                int mask = ~(1 << 4);
                signature &= mask;

                int aliveCount = Integer.bitCount(signature);

                if(currentCell.isAlive) {
                    if (aliveCount < 2) {
                        // Any live cell with fewer than two live neighbors dies as if caused by underpopulation.
                        newGeneration[y][x] = new Cell(x, y, false, this);
                    } else if (aliveCount <= 3) {
                        // Any live cell with two or three live neighbors lives on to the next generation.
                        newGeneration[y][x] = new Cell(x, y, true, this);
                    } else {
                        // Any live cell with more than three live neighbors dies, as if by overpopulation.
                        newGeneration[y][x] = new Cell(x, y, false, this);
                    }
                } else {
                    // Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
                    if (aliveCount == 3) {
                        newGeneration[y][x] = new Cell(x, y, true, this);
                    } else {
                        newGeneration[y][x] = new Cell(x, y, false, this);
                    }
                }
            }
        }

        this.grid = newGeneration;
    }

    public Cell getCell(int x, int y) {
        return grid[y][x];
    }
}