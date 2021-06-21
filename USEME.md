# Starting the Application
The user will first be invited to our 'OOD Instagram' and then be prompted if they would like
to open an existing project. If you have an existing project, answer `Y`/`y` to the question
'Would you like to load an existing project?'. Otherwise, press `N`.

## Opening an existing project
To open an existing `InstagramLayerModelImpl` projects, input the name of the directory when prompted,
and the application will re-open your project. All the layers will be reconstructed with the images
previously saved in the project. When you re-open a project, all layers will be made visible regardless
of the state you left it at before saving previously. The top most layer of the project will be opened
as the 'current' layer of the project. 

#Commands
`new` + `layer_name` = creates a new layer with the name `layer_name`

`current` + `layer_name` = sets the current working layer to the layer with the name `layer_name`

`export` + `fileName.type` = exports the current model's top most visible layer to a new file with the .type image type

`filter` + `operation` = filters the current layer image with `operation`

`transform` + `operation` = transforms the current layer image with `operation`

`remove` + `layer_name` = removes the layer with the name `layer_name`

`invisible` + `layer_name` = makes the layer with the given name invisible

`visible` + `layer_name` = makes the layer with the given name visible

`read` + `import_filepath` = reads the image with the given file path

`save` + `directory_name` = saves this project, all of the image files as .png, and a main file
to reconstruct this project in a new folder with the name `directory_name`. If the folder with the name `directory_name` already exists,
the model will override that directory, deleting it and replacing its contents with the contents of 
this model currently.

##Order of Operations
In order to use these commands, `current` must be used to specify which layer to be working on. 
If not specified before a command, it will default to the last time `current` was called or will send 
an error message.

## Example Runs
Note: Use the scriptRunFromJar.txt and script2RunFromJar.txt versions when running from the jar 
file since the file paths differ when running from the main method vs running from the jar file in the
/res folder.
#### script.txt
script.txt, which is a file included in this zip folder, shows the following operations in this order.
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
script.txt, which is a file included in this zip folder, shows the following operations in this order.
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
