package com.mindboardapps.app.smallsketch.tosvg

import java.io.*
import org.apache.fop.svg.PDFTranscoder
import com.mindboardapps.app.smallsketch.tosvg.style.IStyleObject
import com.mindboardapps.app.smallsketch.tosvg.model.IStrokeObject

class SsfToPdf(styleObject: IStyleObject) : AbstractSsfToImg(styleObject), ISsfToImg {
    private val transcoder = PDFTranscoder()

    override
    fun createImage(strokeObjectList: List<IStrokeObject>, outputStream: OutputStream){
        super.createImage(transcoder, strokeObjectList, outputStream)
    }
}
