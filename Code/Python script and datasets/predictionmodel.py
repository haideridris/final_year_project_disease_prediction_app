import pandas as pd
import tensorflow as tf
import numpy as np
from tensorflow import keras
from sklearn.model_selection import train_test_split
from tensorflow import lite

# Classify a persons disease based upon the symptoms they have
# 133rd column of the datasets is 'prognosis'
# There are 41 categories/diseases/labels in this column
# 133rd column is the label the model will predict i.e. the disease a person has
# This example uses 80:20 train test split

# Read the dataset into a dataframe object
dataset = pd.read_csv("Training.csv")

# Preprocessing of dataset

# Check to see if there is the existence of any null values in the dataset
print("Are null values present in the training dataset? ", dataset.isnull().values.any())
# Returns false so there is no null values in the dataset

# Find out the dimensions of the dataset
# The datasets dimensions are 133 columns and 4020 rows of data
print(dataset.info())

# Find out the number of unique disease values in the dataset prognosis column
# 41 distinct diseases in dataset
numberOfDiseasesTraining = len(pd.unique(dataset['prognosis']))
print("Number of diseases in dataset:", numberOfDiseasesTraining)

# To prevent the model just doing pattern recognition and not generalising well
# I will drop duplicate rows from the dataset

# Number of duplicated rows of data in dataset
# 4616 rows
print("Number of duplicate rows in dataset: ", dataset.duplicated().sum())

# Remove duplicated rows whilst keeping the first occurrence of a row
dataset.drop_duplicates(subset=None, keep="first", inplace=True)

# Find out the dimensions of the dataset after removing duplicates
# The datasets dimensions are 133 columns and 304 rows of data after removing duplicates
print(dataset)

# The 132 symptom columns of the dataset are nominal data with values of either 0 and 1
# to signify the presence of a symptom, 0 means not presenting whilst 1 means it does present in a person

# The prognosis column of the dataset is categorical data that is in a text format. It's not in a suitable
# format to predict disease with the model, so label encoding to put the diseases into a numerical data format will be done

# Use the pandas replace function to label encode the prognosis column of dataset - found all the disease names using
# the Testing.csv file provided from source of data
dataset.replace({'prognosis':{'Fungal infection':0,'Allergy':1, 'GERD':2, 'Chronic cholestasis':3,
'Drug Reaction':4, 'Peptic ulcer diseae':5, 'AIDS':6, 'Diabetes ':7, 'Gastroenteritis':8, 'Bronchial Asthma':9,
'Hypertension ':10, 'Migraine':11, 'Cervical spondylosis':12, 'Paralysis (brain hemorrhage)':13, 'Jaundice':14,
'Malaria':15, 'Chicken pox':16, 'Dengue':17, 'Typhoid':18, 'hepatitis A':19, 'Hepatitis B':20, 'Hepatitis C':21,
'Hepatitis D':22, 'Hepatitis E':23, 'Alcoholic hepatitis':24, 'Tuberculosis':25, 'Common Cold':26, 'Pneumonia':27,
'Dimorphic hemmorhoids(piles)':28, 'Heart attack':29, 'Varicose veins':30, 'Hypothyroidism':31, 'Hyperthyroidism':32,
'Hypoglycemia':33, 'Osteoarthristis':34, 'Arthritis':35, '(vertigo) Paroymsal  Positional Vertigo': 36, 'Acne':37,
'Urinary tract infection':38, 'Psoriasis':39, 'Impetigo':40}}, inplace=True)

# Get labelled column from dataset and create labelled dataset
dataset_labels = dataset.pop('prognosis')

# Split dataset into train test sets
# 80:20 split for training and test set
X_train, X_test, y_train, y_test = train_test_split(dataset, dataset_labels, test_size=0.2)

# Done to make the datasets in numpy array format to be used for the model.fit function
# Model can't use pandas dataframe objects, has to use numpy arrays
training_dataset = np.asarray(X_train)
training_labels = np.asarray(y_train)
testing_dataset = np.asarray(X_test)
testing_labels = np.asarray(y_test)

# Labels of diseases so when testing we have meaningful output after prediction done in this python file
diseaseLabels = ['Fungal infection','Allergy','GERD','Chronic cholestasis','Drug Reaction', 'Peptic ulcer diseae',
'AIDS','Diabetes ','Gastroenteritis','Bronchial Asthma','Hypertension ','Migraine','Cervical spondylosis',
'Paralysis (brain hemorrhage)','Jaundice','Malaria','Chicken pox','Dengue','Typhoid','hepatitis A','Hepatitis B',
'Hepatitis C','Hepatitis D','Hepatitis E','Alcoholic hepatitis','Tuberculosis','Common Cold','Pneumonia',
'Dimorphic hemmorhoids(piles)','Heart attack','Varicose veins','Hypothyroidism','Hyperthyroidism', 'Hypoglycemia',
'Osteoarthristis','Arthritis','(vertigo) Paroymsal  Positional Vertigo','Acne','Urinary tract infection',
'Psoriasis','Impetigo']

# Define the model's architecture (layers) - an artificial neural network
# 3 layers with a Dense layer at every layer, so it is a fully connected network
model = keras.Sequential([
    # Input layer first
    # The number of input features is specified by input_dim i.e. the model expects rows of data with 132 features
    # Dense layer - every neuron is connected to every neuron in the next layer
    # Activation function is rectified linear unit
    keras.layers.Dense(132, input_dim=132, activation="relu"),
    # First hidden layer, also a dense layer so every neuron is connected to every neuron in previous and next layer
    # Number of neurons chosen for the hidden layer is 64
    # Activation function is also rectified linear unit
    keras.layers.Dense(64, activation="relu"),
    # Final layer is output layer with 41 neurons as we are classifying 41 classes (diseases)
    # Activation function softmax calculates values for each neuron so that the sum of those values equals 1
    # i.e. the probability of the network thinking it's a certain class
    keras.layers.Dense(41, activation="softmax")
])

# Compile the model
# Adam is the optimization algorithm used to update weights in the neural network
# Loss function tells us how different the predicted result is from the actual result
# Used to monitor the error by the neural network and should reduce as the weights update during and after each epoch
# Accuracy metric shows the percentage of predictions that identified the correct label (disease)
model.compile(optimiser='adam', loss='sparse_categorical_crossentropy', metrics=['accuracy'])

# Train model
# Epochs is the number of iterations through all the rows of the training dataset
# Batch size is the number of dataset rows considered, within an epoch, before the model weights are updated
model.fit(training_dataset, training_labels, epochs=30, batch_size=81)

# Evaluate model on test data
test_loss, test_acc = model.evaluate(testing_dataset, testing_labels)

# Print accuracy of model's predictions on test data
print("Accuracy of predictions on test data:", test_acc)

# Testing that we can use the model to predictions
# Predicting a specific class
# Take the output class/nueron that has the highest probability and say that is the prediction
# We will try to predict for fungal infection
print('Prediction for Fungal Infection based off input symptoms')
# Create an input array of values (symptom in 0,1,2 and 102 columns i.e. itching, skin rash, nodal skin eruptions
# and dischromic patches)
prediction = model.predict([[1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]])
# The result is saved as an array where at each element the probability of that index (class) has a value predicted,
# i.e. there are 41 values of probability, for each class and each value sums up to the value of 1 overall
# Find the largest value neuron and returns the index of that neuron with np.argmax() function
# Use that index to return the class label i.e. the disease predicted
# Predicted disease is fungal infection
print('Predicted disease: ', diseaseLabels[np.argmax(prediction[0])])

# Save model in tflite format to use in android studio
keras_file = "diseasepredictionmodel.h5"
tf.keras.models.save_model(model, keras_file)
converter = lite.TFLiteConverter.from_keras_model(model)
tfmodel = converter.convert()
open("diseasepredictionmodel.tflite", "wb").write(tfmodel)

# Accuracy score of 0.9959 on training data after 30th epoch and 0.9672131 on test data for the model saved
# for use in the application