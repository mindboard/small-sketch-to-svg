package com.mindboardapps.app.smallsketch.tosvg

import java.io.*
import com.mindboardapps.app.smallsketch.tosvg.model.IStrokeObject

interface ISsfToImg {
    fun createImage(strokeObjectList: List<IStrokeObject>, outputStream: OutputStream)
}
