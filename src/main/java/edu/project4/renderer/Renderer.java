package edu.project4.renderer;

import edu.project4.model.FractalImage;
import edu.project4.model.Rectangle;

public interface Renderer {

    FractalImage render(
        Rectangle world,
        int samples,
        int iterPerSample,
        int symmetry,
        double gammaCorrection
    );
}
