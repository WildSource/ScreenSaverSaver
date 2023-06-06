# ScreenSaverSaver
You download a game, it eats all up your bandwith.<br>
So you go do something else while it's downloading.<br>
You come back and the screen sleeps stopping all download progress<br>
This just moves your mouse to avoid that but with more options.

## Prerequisite

- The source code is written in java 19 so anything that can work with this version should work
- Tesseract data which is inside the **src/main/resources/tessdata** or included as a folder in the releases<br>
(*when using it make sure the folder is in the same directory as the executable jar*)

## Overview

- First mode, moves the mouse indefinitely with the start button and stops manually with the stop button
- Second mode, select a time with the slider and it will execute it for as long as you set it with a stop button
- Third mode is progress based, press z to record the first location and x for the second. It will display an image that you created. An OCR library reads the number and executes as long as it the progress is under 100.