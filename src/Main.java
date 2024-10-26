public static void main(String[] args) throws Exception {
    Grid grid = new Grid(new String[]{
            "**OOOO",
            "**OO**",
            "**OOO*",
            "**OOO*",
            "**OO**",
            "**OO**",}
    );

    while (true) {
        // Clear console
        System.out.print("\033[H\033[2J");
        System.out.flush();

        // Display grid
        grid.displayGrid();

        // Pause for a while
        Thread.sleep(1000);

        // Update grid
        grid.getNextGeneration();
    }
}
