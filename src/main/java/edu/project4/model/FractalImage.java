package edu.project4.model;

public record FractalImage(Pixel[][] data, int width, int height) {

    public static FractalImage create(int width, int height) {
        FractalImage fractalImage = new FractalImage(
            new Pixel[height][width],
            width,
            height
        );
        // set all pixels black and hitCount 0
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                fractalImage.data[i][j] = new Pixel(j, i, Color.getEmpty());
            }
        }
        return fractalImage;
    }

    public boolean contains(int x, int y) {
        return x >= 0 && x < width
            && y >= 0 && y < height;
    }
}
