package com.mindboardapps.app.smallsketch.tosvg.utils;

public class MatrixUtils {
    public static float[] createUnitTransform(){
        return new float[]{
            1f,0f,0f,
            0f,1f,0f,
            0f,0f,1f };
    }

    public static float[] times( float[] matrixA, float[] matrixB ){
        return new float[]{
            matrixA[0]*matrixB[0] + matrixA[1]*matrixB[3] + matrixA[2]*matrixB[6],
            matrixA[0]*matrixB[1] + matrixA[1]*matrixB[4] + matrixA[2]*matrixB[7],
            matrixA[0]*matrixB[2] + matrixA[1]*matrixB[5] + matrixA[2]*matrixB[8],

            matrixA[0+3]*matrixB[0] + matrixA[1+3]*matrixB[3] + matrixA[2+3]*matrixB[6],
            matrixA[0+3]*matrixB[1] + matrixA[1+3]*matrixB[4] + matrixA[2+3]*matrixB[7],
            matrixA[0+3]*matrixB[2] + matrixA[1+3]*matrixB[5] + matrixA[2+3]*matrixB[8],

            matrixA[0+3*2]*matrixB[0] + matrixA[1+3*2]*matrixB[3] + matrixA[2+3*2]*matrixB[6],
            matrixA[0+3*2]*matrixB[1] + matrixA[1+3*2]*matrixB[4] + matrixA[2+3*2]*matrixB[7],
            matrixA[0+3*2]*matrixB[2] + matrixA[1+3*2]*matrixB[5] + matrixA[2+3*2]*matrixB[8]
        };
    }
}
