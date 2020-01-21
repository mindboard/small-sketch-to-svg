package com.mindboardapps.app.smallsketch.tosvg

import java.io.*
import org.apache.batik.transcoder.image.JPEGTranscoder
import com.mindboardapps.app.smallsketch.tosvg.style.IStyleObject
import com.mindboardapps.app.smallsketch.tosvg.model.IStrokeObject

class SsfToJpeg(styleObject: IStyleObject) : AbstractSsfToImg(styleObject), ISsfToImg {
    private val transcoder: JPEGTranscoder
    init {
        transcoder = JPEGTranscoder()
        transcoder.addTranscodingHint(JPEGTranscoder.KEY_QUALITY, 0.9f)
    }

    override
    fun createImage(strokeObjectList: List<IStrokeObject>, outputStream: OutputStream){
        super.createImage(transcoder, strokeObjectList, outputStream)
    }
}
