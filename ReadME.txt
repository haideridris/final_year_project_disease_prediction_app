This repo contains my final year project I undertook for the academic year 21/22 as a Computer Science student. It contains my dissertation
as well as my code submission. 

This text file will provide guidance on how to run the Python file predictionmodel.py and how to run the Disease
Prediction Android Application. These are found within the /Code subfolder 

Guide to run the predictionmodel.py in PyCharm (note the model ran and saved each time with this file is not 
the same as the one used as the asset for the application, this tflite file can be found in 
the assets folder of the Android application)

These steps are to be followed using a Windows 64-bit OS as they are what I did.

Steps to follow to set up the environment and be able to run the predictionmodel.py file using TensorFlow 2.0:
1) Download and install PyCharm (https://www.jetbrains.com/pycharm/)
2) Download and install Anaconda from an archive at https://repo.anaconda.com/archive/. 
You must find the file name Anaconda2-2018.12-Windows-x86_64.exe, it has a file size of 560MB and a
release date of 2018-12-21 13:16:17
3) During setup please click the option that states Add Anaconda to my PATH environment variable, then install
4) Then open the command prompt as this is where we will set up the a virtual environment where we will
install tensorflow packages into
5) Enter command conda to make sure it is installed properly
6) Create a conda environment called tensor by typing command conda create -n tensor python=3.7, press y and enter
to proceed
7) Enter command activate tensor
8) Enter command pip install tensorflow=2.0.0
9) Now tensorflow 2.0 is installed and close the command prompt can be closed
10) Now open PyCharm and click create a new project, name this demoEnv, leave the other settings alone apart from
unticking create a main.py welcome script if that option is ticked and then click create
11) Now go to File -> Settings, click on Project:DemoEnv and then Project Interpeter
12) Click the small settings icon to the top right and then click add, then click on Conda Environment then check
the option that states Existing environment
13) Now we will utilise the conda environment called tensor we created earlier, click the three dots symbol
to the right of where it says Interpreter and locate and click the pythonw.exe file,
 it will be located in a path something like this
C:\Users\youruser\Anaconda2\envs\tensor\pythonw.exe - please select the pythonw.exe file
Then click OK and Apply - waiting for any download/update messages to finish 
14) Now copy and past the contents of the Python script and datasets folder, into the project by
clicking on venv and pasting - a message will appear stating Copy specified files to the
following directory, Click OK.
15) Click on the Python Packages tab at the bottom of the screen, search for pandas and click install
Repeat this process by searching for numpy and clicking install
Click on the terminal tab at the bottom type activate tensor and enter then pip install sklearn and enter
Then click on Python packages once again and search for sci-kit learn and 
click version 1.0.2 instead of latest version and install
16) You may now right click and run the file, all the output will be printed to the console

On every run a new "diseasepredictionmodel.tflite" file will be saved within this project folder

Guide to run the Disease Prediction Android Application (within Android Studio):
Step 1) Download and install Android Studio
Step 2) Click Open and locate where the DiseasePredictionApplication folder is saved on your machine
Step 3) It should appear with a green android icon next to it. Please click this and click ok.
Step 4) Allow the project to load and gradle sync to progress
Step 5) Head to the drop down bar next to the green play button at the top of the screen. Click it then click
AVD Manager.
Step 6) Click option Create Virtual Device
Step 7) Select a Phone device (for the course of my developmemt Pixel 5 has worked fine as an emulator,
this may be a good option to select). Then click Next.The select Q for the system image then click next again.
Then click Finish.
Step 8) You may now click the green play button at the top of the screen and run the application. Create an
account and then log in.

