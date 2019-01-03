package com.mindboardapps.app.smallsketch.tosvg.model

interface IAbstractObject {
    val uuid: String

    val pageUuid: String

    val originalPts: FloatArray

    val pts: FloatArray

    val transformMatrixValues: FloatArray

    val color: Int

    fun getBounds(): RectF
    fun applyTransform(matrixValues: FloatArray)

    var underDragging: Boolean
}
