package com.mindboardapps.app.smallsketch.tosvg.utils

object ModelUtils {
    private val matrix = Matrix()

    fun updatePts(newPts: FloatArray, matrixValues:FloatArray): FloatArray {
        matrix.setValues(matrixValues)
        matrix.mapPoints(newPts)
        return newPts
    }
}
