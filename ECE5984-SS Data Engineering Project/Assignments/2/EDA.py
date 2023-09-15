import numpy as np
import pandas as pd
import pickle


# Raw data
raw_data = np.load(r'', allow_pickle=True)   # Load pickle data here
# Display the complete data
print("The Dataset looks like:")
print(raw_data)
print(raw_data.shape)
print("====================================")

# Set options to show all columns of the dataset
pd.set_option('display.max_columns', None)
# Display all the columns together in the console
print("Display first 5 rows")
print(raw_data.head().to_string())
print("====================================")


# Getting a feel of the dataset
# Basic EDA functions

print("Basic Dataframe info")
print(raw_data.info())
print("====================================")
print("More detailed Dataframe info")
print(raw_data.describe().to_string())
print("====================================")
print("Number of Empty values in each column:")
print(raw_data.isnull().sum().sort_values(ascending = False))
print("====================================")
print("Number of Unique values in each column:")
print(raw_data.apply(pd.Series.nunique))
print("====================================")
print("Are there duplicate rows?")
print(raw_data.duplicated())
print("====================================")







