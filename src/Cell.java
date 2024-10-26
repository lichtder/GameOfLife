public class Cell {
    public boolean isAlive;
    private final int x;
    private final int y;
    private final Grid grid;

    public Cell(final int x, final int y, boolean isAlive, Grid grid) {
        this.x = x;
        this.y = y;
        this.isAlive = isAlive;
        this.grid = grid;
    }

    public int getNeighborhoodSignature() {
        int signature = 0;

        int[] offsetX = new int[] {-1, 0, 1, -1, 0, 1, -1, 0, 1};
        int[] offsetY = new int[] {-1, -1, -1, 0, 0, 0, 1, 1, 1};

        for(int i = 0; i < 9; i++) {
            int x = this.x + offsetX[i];
            int y = this.y + offsetY[i];

            if ((x >= 0 && y >= 0) && (x < grid.width && y < grid.height)) {
                if (grid.getCell(x, y).isAlive) {
                    signature += (int) Math.pow(2, i);
                }
            }
        }

        return signature;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
