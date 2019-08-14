package com.mindboardapps.app.smallsketch.tosvg

import java.io.*
import org.apache.fop.svg.PDFTranscoder
import com.mindboardapps.app.smallsketch.tosvg.style.IStyleObject

class SsfToPdf(styleObject: IStyleObject) : AbstractSsfToImg(styleObject), ISsfToImg {
    private val transcoder = PDFTranscoder()

    override fun createImage(lines: List<String>, outputStream: OutputStream){
        super.createImage(transcoder, lines, outputStream)
    }
}
