package com.mindboardapps.app.smallsketch.tosvg.utils

class Matrix(){
    private var matrixValues: FloatArray = MatrixUtils.createUnitTransform()

    fun setValues(matrixValues: FloatArray){
        this.matrixValues = matrixValues
    }

    fun mapPoints(pts: FloatArray){
        0.until(pts.size).step(2).forEach { index->
            val x = pts[index]
            val y = pts[index+1]
            val z = 1f

            val newX = matrixValues[0]*x + matrixValues[1]*y + matrixValues[2]*z
            val newY = matrixValues[3]*x + matrixValues[4]*y + matrixValues[5]*z

            pts[index]   = newX
            pts[index+1] = newY
        }
    }
}

