# ImageProcessing
OOD Image Processing Instagram Model

## The model
Our model, of the interface `InstagramModel`, includes features to import images of `InstaImage` types, as well as PPM file names. Once an image has been imported
into the model, the client can choose to filter the image with the options of `blur` and `sharpen`. On top of this, they can also choose to color transform the
image into `greyscale` or `sepia`. Any time during this, if the model is holding an image, it can be exported in PPM file format, as well as `InstaImage` format. 
If the client attempts to execute any exports or image processing commands while the model holds no image, an error will be thrown.

`filter(String operation)` takes in the string (not case sensitive) to represent the particular filter to be applied. Current options are blur and sharpen.

`transform(String operation)` takes in the string (not case sensitive) to represent the particular color transofmration to be applied. Current options are greyscale
and sepia.

## InstaImage
InstaImage is an object type that holds the important information of an image. It holds the grid of `Pixel`s to represent the image, as well as a height and width
in pixels. InstaImage can also produce and return algorithmic images such as a rainbow flag, and a black and white checkerboard. 

## Pixel
A `Pixel` holds 3 channels R, G, and B which represent the red, green, and blue components of the resulting color the pixel holds. These channels are represented with
`Channel` objects.

## Channel
A `Channel` holds an integer value from `0-255` which represents the amount of its particular color in a pixel. There are three types of channels currently, 
`ChannelR`, `ChannelG`, and `ChannelB`. In the case where a client attempts to set the value of a channel to any amount less than 0, it will be defaulted to 0, 
and if a client attempts to set the value of a channel to any amount more than 255, it will be defaulted to 255. The set values for channels may take in 
Double values, but they will round those values on a .5 basis to the nearest integer. 
