// ------------------------------------------------------------------------------------------
// An example groovy script to help creating sketch movie. 
// ------------------------------------------------------------------------------------------

@GrabResolver(name='local', root='file://~/.m2/repository')
@Grab(group='com.mindboardapps.app.smallsketch', module='to-svg', version='0.1.0-SNAPSHOT')
@Grab(group='batik', module='batik-transcoder', version='1.6-1')

import org.apache.batik.transcoder.TranscoderInput
import org.apache.batik.transcoder.TranscoderOutput
import org.apache.batik.transcoder.image.PNGTranscoder

import java.util.zip.GZIPInputStream
import com.mindboardapps.app.smallsketch.tosvg.*
import com.mindboardapps.app.smallsketch.tosvg.style.DefaultStyleObject
import com.mindboardapps.app.smallsketch.tosvg.style.StyleObject

import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import java.awt.Color


def svgToPng = { svgFile, pngFile->
    def uri = svgFile.toURI()
    def input = new TranscoderInput(uri.toString())
    def outputStream = new FileOutputStream( pngFile )
    def output = new TranscoderOutput(outputStream)

    new PNGTranscoder().transcode(input, output)
    
    outputStream.close()
}


def getMaxWidthAndHeight = { dir-> 
    def imageSizeList = dir.listFiles( {it.name.endsWith('png')} as FileFilter ).collect {
        def img = ImageIO.read(it)
        [width: img.width, height: img.height]
    }

    def maxWidth = imageSizeList.collect { it.width } . inject()  {w0,w1->Math.max(w0,w1)} 
    def maxHeight= imageSizeList.collect { it.height } . inject() {h0,h1->Math.max(h0,h1)} 
    return [
        width: maxWidth,
        height: maxHeight]
}

def createBaseImage = { widthAndHeight->
    return new BufferedImage(
        widthAndHeight.width,
        widthAndHeight.height,
        BufferedImage.TYPE_4BYTE_ABGR)
}


def styleObject = new StyleObject(new File('style.json'))
def ssfToSvg = new SsfToSvg(styleObject)


def ssfFile = new File(args[0])
def inputStream = new InputStreamReader(new GZIPInputStream(new FileInputStream(ssfFile)), 'UTF-8')
def lines = inputStream.readLines()

def lineCount = lines.size()

int mystep = 2
if( lineCount>mystep ){
    int fileNumber = 0
    (mystep..<lineCount).step(mystep).each {
        def number = String.format('%1$05d', fileNumber)
        def tmpSvgFile = new File("${number}.svg")
        def tmpPngFile = new File("${number}.png")

        // svg
        def tmpLines = lines.take(it)
        tmpSvgFile.text = ssfToSvg.createSvg(tmpLines)

        // png
        svgToPng(tmpSvgFile, tmpPngFile)

        fileNumber += 1
    }
}

//
// resize png files with alignment max width and height.
//

def dir = new File('.')
def widthAndHeight = getMaxWidthAndHeight(dir)

def backgroundColor = new Color(
    styleObject.backgroundColor.r,
    styleObject.backgroundColor.g,
    styleObject.backgroundColor.b)

def pngFileList = dir.listFiles( {it.name.endsWith('png')} as FileFilter )
pngFileList.each { pngFile->
    def bimg = createBaseImage(widthAndHeight)
    def g = bimg.getGraphics()

    def image = ImageIO.read(pngFile)
    g.color = backgroundColor
    g.fillRect(0, 0, widthAndHeight.width, widthAndHeight.height)
    g.drawImage(image, 0, 0, null)
    g.dispose()

    // overwrite it
    def outputPngFile = pngFile
    ImageIO.write(bimg, 'PNG', outputPngFile)
}
