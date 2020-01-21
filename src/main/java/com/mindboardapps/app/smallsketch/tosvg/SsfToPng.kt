package com.mindboardapps.app.smallsketch.tosvg

import java.io.*
import com.mindboardapps.app.smallsketch.tosvg.style.IStyleObject
import com.mindboardapps.app.smallsketch.tosvg.model.IStrokeObject
import com.mindboardapps.app.smallsketch.tosvg.image.*

class SsfToPng(styleObject: IStyleObject) : AbstractSsfToImg(styleObject), ISsfToImg {
    private val transcoder = MyPNGTranscoder()

    override
    fun createImage(strokeObjectList: List<IStrokeObject>, outputStream: OutputStream){
        super.createImage(transcoder, strokeObjectList, outputStream)
    }
}
