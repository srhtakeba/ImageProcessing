#Starting the Application
The user will first be invited to our 'OOD Instagram' and then be prompted if they would like
to open an existing project. If you have an existing project, answer `Y`/`y` to the question
'Would you like to load an existing project?'. Otherwise, press `N`.

##Opening an existing project
To open an existing `InstagramLayerModelImpl` projects, input the name of the directory when prompted,
and the application will re-open your project. All the layers will be reconstructed with the images
previously saved in the project.


#Commands
`new` + `layer_name` = creates a new layer with the name `layer_name`\\

`current` + `layer_name` = sets the current working layer to the layer with the name `layer_name`\\

`export` + `fileName.type` = exports the current layer image to a new file with the .type image type\\

`filter` + `operation` = filters the current layer image with `operation`\\

`transform` + `operation` = transforms the current layer image with `operation`\\

`remove` + `layer_name` = removes the layer with the name `layer_name`\\

`read` + `import_filepath` = reads the image with the given file path\\

`save` + `directory_name` = saves this project, all of the image files as .png, and a main file
to reconstruct this project in a new folder with the name `directory_name`. Sets the current layer
to the top most layer of this project. If the folder with the name `directory_name` already exists,
the model will override that directory, deleting it and replacing its contents with the contents of 
this model currently.\\

##Order of Operations
In order to use these commands, `current` must be used to specify which layer to be working on. 
If not specified before a command, it will default to the last time `current` was called or will throw
an error. 