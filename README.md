
# ssf2img

CLI tool to convert SSF(small-sketch-format) into SVG, PDF, PNG, JPEG format.

* Small Sketch : https://play.google.com/store/apps/details?id=com.mindboardapps.app.smallsketch


## Usage

1. Build ssf2img.zip  
    `./gradlew distZip` and build/distributions/ssf2img.zip is generated.

2. Install ssf2img command  
    Unzip ssf2img.zip in somewhere you want

3. Convert ssf to png  
    `cat foo.ssf | /path/to/ssf2img/bin/ssf2img > foo.png`

4. Convert ssf to png with style  
    `cat foo.ssf | /path/to/ssf2img/bin/ssf2img -s style.json > foo.png`

There is example style file in this project examples/style.json.

5. Convert ssf to svg with style  
    `cat foo.ssf | /path/to/ssf2img/bin/ssf2img -f svg -s style.json > foo.svg`

6. Convert ssf to pdf with style  
    `cat foo.ssf | /path/to/ssf2img/bin/ssf2img -f pdf -s style.json > foo.pdf`

7. Convert ssf to jpg with style  
    `cat foo.ssf | /path/to/ssf2img/bin/ssf2img -f jpg -s style.json > foo.jpg`

There is an example file example1.ssf in the ./resources dir and a style.json example in the ./examples dir.

![Example1](https://github.com/mindboard/ssf2img/blob/master/resources/example1.svg)


## License

See the LICENSE file for license rights and limitations (MIT).
