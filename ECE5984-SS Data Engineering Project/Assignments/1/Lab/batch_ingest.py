import s3fs
from s3fs.core import S3FileSystem
import numpy as np
import pickle

import pandas as pd
from pandas_datareader import data as pdr
import yfinance as yfin

def ingest_data():
    # Choose the ticker variables of the stocks the data of which you want to pull
    # Here we are getting 4 years of stock market data from Apple, Google and Amazon
    tickers = ["AAPL", "GOOGL", "AMZN"]
    start_date = '2019-1-1'
    end_date = '2023-1-1'


    yfin.pdr_override()
    # All the data is stored in a pandas dataframe called data
    data = pdr.get_data_yahoo(tickers, start=start_date, end=end_date)


    # Adding noise to the Data to simulate a noisy dataset
    # NaN values and outliers
    for col in data.columns:
        data.loc[data.sample(frac=0.1).index, col] = np.nan
        data.loc[data.sample(frac=0.005).index, col] = 1000
        data.loc[data.sample(frac=0.005).index, col] = 0

    # Duplicate values
    data = pd.concat([data, data.sample(frac=0.1)])

    s3 = S3FileSystem()
    # S3 bucket directory
    DIR = 's3://ece5984-bucket-gasser18/Lab1/'                    # insert here
    # Push data to S3 bucket as a pickle file
    with s3.open('{}/{}'.format(DIR, 'data.pkl'), 'wb') as f:
        f.write(pickle.dumps(data))

