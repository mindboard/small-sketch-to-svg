package com.mindboardapps.app.smallsketch.tosvg.model

import com.mindboardapps.app.smallsketch.tosvg.utils.Matrix
import com.mindboardapps.app.smallsketch.tosvg.utils.MatrixUtils
import com.mindboardapps.app.smallsketch.tosvg.utils.ModelUtils

abstract class AbstractObject(
        override val pageUuid: String,
        override val uuid: String,
        override val originalPts: FloatArray,
        override val color: Int,
        initialTransformMatrixValues: FloatArray = MatrixUtils.createUnitTransform()) : IAbstractObject {

    var _transformMatrixValues:FloatArray = MatrixUtils.createUnitTransform()
    var _pts: FloatArray

    init {
        // 初期化順に意味があるので注意!!

        //1)
        _pts = originalPts.clone()

        // 2)
        applyTransform(initialTransformMatrixValues)
    }

    override val transformMatrixValues: FloatArray
        get() = _transformMatrixValues

    override var underDragging: Boolean = false

    override val pts: FloatArray
        get() = _pts

    private var _bounds: RectF? = null

    override fun getBounds(): RectF {
        if(_bounds==null){
            if(originalPts.size<3){
                return RectF()
            }

            val pts = originalPts.copyOf()

            val matrix = Matrix()
            matrix.setValues(transformMatrixValues)
            matrix.mapPoints(pts)

            val xList = arrayListOf<Float>()
            val yList = arrayListOf<Float>()

            val lenMinus1 = pts.size-1
            for(index in 0..lenMinus1 step 2){
                val x=pts[index]
                xList.add(x)

                val y=pts[index+1]
                yList.add(y)
            }

            _bounds = RectF(
                    xList.min()?:0f,
                    yList.min()?:0f,
                    xList.max()?:0f,
                    yList.max()?:0f)

        }

        return RectF(_bounds)
    }

    override fun applyTransform(matrixValues: FloatArray) {
        _transformMatrixValues = MatrixUtils.times(matrixValues, _transformMatrixValues)
        _bounds = null
        _pts = ModelUtils.updatePts(originalPts.clone(), _transformMatrixValues)
    }
}
