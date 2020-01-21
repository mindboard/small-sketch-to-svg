package com.mindboardapps.app.smallsketch.tosvg

import spock.lang.Specification
import spock.lang.Ignore
import java.util.zip.GZIPInputStream
import com.mindboardapps.app.smallsketch.tosvg.style.*

class JpegExportTest extends Specification {
    private static void toJpeg(
        File styleJsonFile,
        File ssfFile,
        File jpegFile){

        def lines = new GZIPInputStream(new FileInputStream(ssfFile)).readLines()
        def outputStream = new FileOutputStream(jpegFile)
        new SsfToJpeg( new StyleObject( styleJsonFile ) ).createImage(
            CmdHelper.INSTANCE.toStrokeObjectList(lines),
            outputStream)
        outputStream.close()
    }

    //@Ignore
    def "export-as-jpg-test-2"(){
        when:
        def styleJsonFile = new File('./examples/style.json')
        def jpegFile = new File('nuc.jpg')

        toJpeg(
            styleJsonFile, 
            new File('./examples/nuc.ssf'),
            jpegFile)

        then:
        jpegFile.exists()
    }

    //@Ignore
    def "export-as-jpg-test-1"(){
        when:
        def styleJsonFile = new File('./examples/style.json')
        def jpegFile = new File('tree.jpg')

        toJpeg(
            styleJsonFile, 
            new File('./examples/tree.ssf'),
            jpegFile)

        then:
        jpegFile.exists()
    }
}
