import s3fs
from s3fs.core import S3FileSystem
import numpy as np
import pickle

import pandas as pd
from pandas_datareader import data as pdr
import yfinance as yfin


def ingest_data():
    # Choose the ticker variables of the stocks the data of which you want to pull
    # Here we are getting all of the stock market data from Apple, Google and Amazon upto present day
    start_date = '2005-1-1'
    tickers = ["AAPL", "GOOGL", "AMZN"]

    yfin.pdr_override()
    # All the data is stored in a pandas dataframe called data
    data = pdr.get_data_yahoo(tickers, start=start_date)

    # Adding noise to the Data to simulate a noisy dataset
    # NaN values
    for col in data.columns:
        data.loc[data.sample(frac=0.001).index, col] = np.nan

    # Choose random column besides Volume
    i = 0
    while i == 0:
        rand_col = data.sample(axis='columns').columns
        if rand_col[0][0] != 'Volume':
            i = 1


    # Outliers
    data.loc[data.sample(frac=0.005).index, rand_col] = 1000
    data.loc[data.sample(frac=0.005).index, rand_col] = 0

    # Adding Duplicate values
    data = pd.concat([data, data.sample(frac=0.005)])

    s3 = S3FileSystem()
    # S3 bucket directory
    DIR = 's3://ece5984-bucket-gasser18/Lab2/lab2-transform'                        #Enter your S3 directory
    # Push data to S3 bucket as a pickle file
    with s3.open('{}/{}'.format(DIR, 'data.pkl'), 'wb') as f:
        f.write(pickle.dumps(data))

