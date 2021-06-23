# The Textual View + Interactive Text View

## Starting the Application

The user will first be invited to our 'OOD Instagram' and then be prompted if they would like to
open an existing project. If you have an existing project, answer `Y`/`y` to the question
'Would you like to load an existing project?'. Otherwise, press `N`.

### Opening an existing project

To open an existing `InstagramLayerModelImpl` projects, input the name of the directory when
prompted, and the application will re-open your project. All the layers will be reconstructed with
the images previously saved in the project. When you re-open a project, all layers will be made
visible regardless of the state you left it at before saving previously. The top most layer of the
project will be opened as the 'current' layer of the project.

## Commands

`new` + `layer_name` = creates a new layer with the name `layer_name`

`current` + `layer_name` = sets the current working layer to the layer with the name `layer_name`

`export` + `fileName.type` = exports the current model's top most visible layer to a new file with
the .type image type

`filter` + `operation` = filters the current layer image with `operation`

`transform` + `operation` = transforms the current layer image with `operation`

`mosaic` + `seed_value` = applies a mosaic to the current layer image with the given seed value (
int)

`remove` + `layer_name` = removes the layer with the name `layer_name`

`invisible` + `layer_name` = makes the layer with the given name invisible

`visible` + `layer_name` = makes the layer with the given name visible

`read` + `import_filepath` = reads the image with the given file path

`save` + `directory_name` = saves this project, all of the image files as .png, and a main file to
reconstruct this project in a new folder with the name `directory_name`. If the folder with the
name `directory_name` already exists, the model will override that directory, deleting it and
replacing its contents with the contents of this model currently.

### Order of Operations

In order to use these commands, `current` must be used to specify which layer to be working on. If
not specified before a command, it will default to the last time `current` was called or will send
an error message.

### Example Runs

Note: Use the scriptRunFromJar.txt and script2RunFromJar.txt versions when running from the jar file
since the file paths differ when running from the main method vs running from the jar file in the
/res folder.

#### script.txt

script.txt, which is a file included in this zip folder, shows the following operations in this
order.

- make a new layer called 'first'
- make the current working layer 'first'
- read canyonLowest.jpg from res/images/originals into layer 'first'
- export the top most visible layer as a jpg called 'mycanyon.jpg'
- make a new layer called 'second'
- make the current working layer 'second'
- read canyonLowest.jpg from res/images/originals into layer 'second'
- transform the image on layer 'second' to be greyscale
- delete layer 'second' from the model
- make a new layer called 'third'
- make the current working layer 'third'
- read canyonLowest.jpg from res/images/originals into layer 'third'
- filter the image on layer 'third' to be blurred
- make the 'third' layer invisible
- export the top most visible layer as a jpg called 'noBlurMyCanyon.jpg'
- make the 'third' layer visible again
- export the top most visible layer as a jpg called 'withBlurMyCanyon.jpg'
- save the entire project (with a new directory and a file) called myhappycanyon

#### script2.txt

script.txt, which is a file included in this zip folder, shows the following operations in this
order.

- make a new layer called 'first'
- make the current working layer 'first'
- read fishLowest.jpg from res/images/originals into layer 'first'
- export the top most visible layer as a jpg called 'myNakedFish.jpg'
- make the 'first' layer invisible
- save the project, calling it 'myfish'
- make a new layer called 'second'
- make a new layer called 'third'
- make the current working layer 'second'
- read fishLowest.jpg from res/images/originals into layer 'second'
- transform the image on layer 'second' to be greyscale
- make the current working layer 'third'
- read fishLowest.jpg from res/images/originals into layer 'third'
- filter the image on layer 'third' to be blurred
- make layer 'third' invisible
- export the top most visible layer as a jpg called 'greyscaleMyFish.jpg'
- remove the layer 'third'
- export the top most visible layer as a png called 'withBlurMyFish.png'
- override and re-save this project in a directory called 'myfish'

# The GUI View

## Example Screen

![](res/exampleScreenshot.png)

## Starting the Application

### Project Operations / Getting Started

First thing to do is to **add a layer**. Do this by typing a desired layer title in the text box,
and selecting `Add layer`. Once your layer is added, it should appear in the combo box in the panel
below. <br><br>
Before importing an image to your layer, you must **select it to be the current working layer**. To
do this, select your layer title in the ComboBox. You may click on `Set Current` to ensure that your
selected layer has been set as the current working layer, but there is no need to do so. This was
more of a design choice in order to expose this functionality to the user. You can see which layer
is the current working layer by looking through the drop down ComboBox and seeing which layer has a
check mark next to it to indiciate that it is the current working layer. <br><br>
You can then **import an image from your files**. Note that once you have imported an image of
certain proportions, only images of the same proportions can be imported in this model
again. <br><br>
Once you have created your layer, you can also **set it to be visible/invisible**. To do this,
select your layer from the ComboBox, and click on `visible` or `invisible`. <br><br>
At any time after you have imported an image for the first time in the model, you can **export the
top most visible layer** with the `export` button. This will export the image with your desired file
type and name, the exported image is what you see on the view. <br><br>
To **transform an image on a layer**, select the layer name with the image with `Set Current`, and
use the `blur`, `sharpen`, `greyscale`, and `sepia` buttons. Note that if you are working on a layer
that is underneath a visible layer, even by pressing the buttons you may not see the transformations
in the view. To see the transformations on the view, make sure the current working layer is also the
top most visible layer. <br><br>
To **remove a layer** at any time, type the layer name you would like to remove in the text box and
click `Remove layer`. <br><br>
To **mosaic an image on a layer**, click on `Mosaic` and enter the desired mosaic seed in the pop-up
window before pressing `ok`. If you provide a seed value that is larger than the total number of
pixels in the current working layer image, there will be no change made to the image.

### The Drop Down Menu

All of the above operations for your Instagram OOD project can also be found under the `Tools` menu
bar on the top of the view window. Simply click on these options to get to a certain
operation. `Add layer` and `Remove layer` will no longer use the text input box for the name of the
layer, and rather prompt the user with a pop-up box in order to ask for the layer name.

### Importing a script / Opening an old project.

You also have the option to **import a script** for `Instagram OOD` applications. To do this, click
on `Script` on the bottom most panel, and select the file containing your script. Only .txt files
may be imported. <br><br>
To **open an old project** that you created with this GUI application, select the `main.txt` script
file from the project directory from when you saved the project last, and import that into the
application, your project should reopen where you left it off.

### Saving your project

Click on `save` at any time to **save your `Instagram OOD` as a project**. Choose a directory and a
directory name for your saved project. This project will include `.png` files of all visible layers
in the project, as well as a `main.txt` script file that can be used to re-open your project later
on. 
