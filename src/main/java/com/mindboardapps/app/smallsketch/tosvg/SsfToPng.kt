package com.mindboardapps.app.smallsketch.tosvg

import java.io.*
import java.util.UUID
import java.util.zip.GZIPInputStream

import org.apache.batik.transcoder.TranscoderInput
import org.apache.batik.transcoder.TranscoderOutput
import org.apache.batik.transcoder.image.PNGTranscoder

import com.mindboardapps.app.smallsketch.tosvg.style.IStyleObject

class SsfToPng(private val styleObject: IStyleObject){
    companion object {
        fun convert(svgFile: File, outputStream: OutputStream) {
            val uri = svgFile.toURI()
            val input = TranscoderInput(uri.toString())
            val output = TranscoderOutput(outputStream)
            PNGTranscoder().transcode(input, output)
        }
    }

    private fun createSvg(lines: List<String>): String = SsfToSvg(styleObject).createSvg(lines)

    fun createPng(lines: List<String>, outputStream: OutputStream){
        val tmpDir = File(System.getProperty("java.io.tmpdir"))
        if( tmpDir.exists() ){
            val tmpSvgFile = File(tmpDir, "${UUID.randomUUID().toString()}.svg")
            tmpSvgFile.writeText( createSvg(lines) )
            if( tmpSvgFile.exists() ){
                convert(tmpSvgFile, outputStream)
            }
        }
    }
}
