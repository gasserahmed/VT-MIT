import s3fs
from s3fs.core import S3FileSystem
import numpy as np
import pickle

import pandas as pd

def ingest_data():
    # Date range
    start_date = '2012-1-1'
    end_date = '2017-1-1'

    s3 = S3FileSystem()
    datasetS3Directory = "s3://ece5984-bucket-gasser18/Lab1/aadr.us.txt"

    # read aadr.us text file from S3 into pandas DataFrame
    with s3.open(datasetS3Directory) as f:
        data = pd.read_csv(f, sep=",")

    # Filter the data based on the date
    data = data[(data['Date'] > start_date) & (data['Date'] < end_date)]

    # Adding noise to the Data to simulate a noisy dataset
    # NaN values and outliers
    for col in data.columns:
        data.loc[data.sample(frac=0.1).index, col] = np.nan
        data.loc[data.sample(frac=0.005).index, col] = 1000
        data.loc[data.sample(frac=0.005).index, col] = 0

    # Duplicate values
    data = pd.concat([data, data.sample(frac=0.1)])

    # S3 bucket directory
    DIR = 's3://ece5984-bucket-gasser18/Lab1/hw1-stream'                    # insert here
    # Push data to S3 bucket as a pickle file
    with s3.open('{}/{}'.format(DIR, 'data.pkl'), 'wb') as f:
        f.write(pickle.dumps(data))

