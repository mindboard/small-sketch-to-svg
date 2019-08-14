package com.mindboardapps.app.smallsketch.tosvg

import java.io.*
import com.mindboardapps.app.smallsketch.tosvg.style.IStyleObject
import com.mindboardapps.app.smallsketch.tosvg.image.*

class SsfToPng(styleObject: IStyleObject) : AbstractSsfToImg(styleObject), ISsfToImg {
    private val transcoder = MyPNGTranscoder()

    override fun createImage(lines: List<String>, outputStream: OutputStream){
        super.createImage(transcoder, lines, outputStream)
    }
}
