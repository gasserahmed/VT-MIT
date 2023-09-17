import s3fs
from s3fs.core import S3FileSystem
import numpy as np
import pickle


def transform_data():
    s3 = S3FileSystem()
    # S3 bucket directory (data lake)
    DIR = 's3://ece5984-bucket-gasser18/Lab2/hw2-transform'  # Insert here
    # Get data from S3 bucket as a pickle file
    raw_data = np.load(s3.open('{}/{}'.format(DIR, 'data.pkl')), allow_pickle=True)
    # raw_data = np.load('data.pkl', allow_pickle=True)

    # Dropping rows with NaN in them
    df_data = raw_data.dropna()

    # Removing rows with outliers
    for col in list(df_data.columns)[1:5]:  # We ignore 'Date', 'Volume', and 'OpenInt' columns
        df_data = df_data.drop(df_data[df_data[col].values > 50].index)  # Values above 50 are dropped
        df_data = df_data.drop(df_data[df_data[col].values < 30].index)  # Values below 30 are dropped

    # Dropping duplicate rows
    df_data = df_data.drop_duplicates()

    # Push cleaned data to S3 bucket warehouse
    DIR_wh = 's3://ece5984-bucket-gasser18/Lab2/hw2-transform'  # Insert here
    with s3.open('{}/{}'.format(DIR_wh, 'clean_aadr.pkl'), 'wb') as f:
        f.write(pickle.dumps(df_data))
