package com.mindboardapps.app.smallsketch.tosvg.model

import com.mindboardapps.app.smallsketch.tosvg.utils.MatrixUtils

class StrokeObject(
    pageUuid: String,
    uuid: String,
    originalPts: FloatArray,
    color: Int,
    override var groupUuid: String,
    override val logicalStrokeWidth: Int,
    initialTransformMatrixValues: FloatArray=MatrixUtils.createUnitTransform()) : AbstractObject(pageUuid, uuid, originalPts, color, initialTransformMatrixValues),  IStrokeObject {}
