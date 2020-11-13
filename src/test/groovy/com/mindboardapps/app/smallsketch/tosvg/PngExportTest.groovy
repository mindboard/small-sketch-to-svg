package com.mindboardapps.app.smallsketch.tosvg

import spock.lang.Specification
import spock.lang.Ignore
import java.util.zip.GZIPInputStream
import com.mindboardapps.app.smallsketch.tosvg.style.*

class PngExportTest extends Specification {
    private static void toPng(
        File styleJsonFile,
        File ssfFile,
        File pngFile){

        def lines = new GZIPInputStream(new FileInputStream(ssfFile)).readLines()
        def outputStream = new FileOutputStream(pngFile)
        new SsfToPng( new StyleObject( styleJsonFile ) ).createImage(
            CmdHelper.INSTANCE.toStrokeObjectList(lines),
            outputStream)
        outputStream.close()
    }

    //@Ignore
    def "export-as-png-test coelacanth another style"(){
        when:
        def styleJsonFile = new File('./examples/another-style.json')
        def pngFile = new File('coelacanth_another_style.png')

        toPng(
            styleJsonFile,
            new File('./examples/coelacanth.ssf'),
            pngFile)

        then:
        pngFile.exists()
    }

    //@Ignore
    def "export-as-png-test coelacanth"(){
        when:
        def styleJsonFile = new File('./examples/style.json')
        def pngFile = new File('coelacanth.png')

        toPng(
            styleJsonFile,
            new File('./examples/coelacanth.ssf'),
            pngFile)

        then:
        pngFile.exists()
    }

    //@Ignore
    def "export-as-png-test-2"(){
        when:
        def styleJsonFile = new File('./examples/style.json')
        def pngFile = new File('nuc.png')

        toPng(
            styleJsonFile, 
            new File('./examples/nuc.ssf'),
            pngFile)

        then:
        pngFile.exists()
    }

    //@Ignore
    def "export-as-png-test-1"(){
        when:
        def styleJsonFile = new File('./examples/style.json')
        def pngFile = new File('tree.png')

        toPng(
            styleJsonFile, 
            new File('./examples/tree.ssf'),
            pngFile)

        then:
        pngFile.exists()
    }
}
