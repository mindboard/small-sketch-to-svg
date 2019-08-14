package com.mindboardapps.app.smallsketch.tosvg

import spock.lang.Specification
import spock.lang.Ignore
import java.util.zip.GZIPInputStream
import com.mindboardapps.app.smallsketch.tosvg.style.*

class PdfExportTest extends Specification {
    private static void toPdf(
        File styleJsonFile,
        File ssfFile,
        File pdfFile){

        def lines = new GZIPInputStream(new FileInputStream(ssfFile)).readLines()
        def outputStream = new FileOutputStream(pdfFile)
        new SsfToPdf( new StyleObject( styleJsonFile ) ).createImage(lines, outputStream)
        outputStream.close()
    }

    //@Ignore
    def "export-as-pdf-test-2"(){
        when:
        def styleJsonFile = new File('./examples/style.json')
        def pdfFile = new File('nuc.pdf')

        toPdf(
            styleJsonFile, 
            new File('./examples/nuc.ssf'),
            pdfFile)

        then:
        pdfFile.exists()
    }

    //@Ignore
    def "export-as-pdf-test-1"(){
        when:
        def styleJsonFile = new File('./examples/style.json')
        def pdfFile = new File('tree.pdf')

        toPdf(
            styleJsonFile, 
            new File('./examples/tree.ssf'),
            pdfFile)

        then:
        pdfFile.exists()
    }
}
