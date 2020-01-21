package com.mindboardapps.app.smallsketch.tosvg

import java.io.*
import java.util.UUID
import java.util.zip.GZIPInputStream

import org.apache.batik.transcoder.TranscoderInput
import org.apache.batik.transcoder.TranscoderOutput

import com.mindboardapps.app.smallsketch.tosvg.style.IStyleObject
import com.mindboardapps.app.smallsketch.tosvg.model.*
import org.apache.batik.transcoder.Transcoder

abstract class AbstractSsfToImg(private val styleObject: IStyleObject){
    companion object {
        fun convert(
            transcoder: Transcoder,
            svgFile: File,
            outputStream: OutputStream) {

            val uri = svgFile.toURI()
            val input = TranscoderInput(uri.toString())
            val output = TranscoderOutput(outputStream)

            transcoder.transcode(input, output)
        }
    }

    private fun createSvg(strokeObjectList: List<IStrokeObject>): String = SsfToSvg(styleObject).createSvg( strokeObjectList )

    internal fun createImage(
        transcoder: Transcoder,
        strokeObjectList: List<IStrokeObject>,
        outputStream: OutputStream){

        val tmpDir = File(System.getProperty("java.io.tmpdir"))
        if( tmpDir.exists() ){
            val tmpSvgFile = File(tmpDir, "${UUID.randomUUID().toString()}.svg")
            tmpSvgFile.writeText( createSvg(strokeObjectList) )
            if( tmpSvgFile.exists() ){
                convert(transcoder, tmpSvgFile, outputStream)
                tmpSvgFile.delete()
            }
        }
    }
}
