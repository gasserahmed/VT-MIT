import s3fs
from s3fs.core import S3FileSystem
import numpy as np
import pickle
import pandas as pd
import numpy as np
from sklearn.preprocessing import MinMaxScaler
from sklearn.model_selection import TimeSeriesSplit


def feature_extract():

    s3 = S3FileSystem()
    # S3 bucket directory (data warehouse)
    DIR_wh = ''                                 # Insert here
    # Get data from S3 bucket as a pickle file
    aapl_df = np.load(s3.open('{}/{}'.format(DIR_wh, 'clean_aapl.pkl')), allow_pickle=True)
    amzn_df = np.load(s3.open('{}/{}'.format(DIR_wh, 'clean_amzn.pkl')), allow_pickle=True)
    googl_df = np.load(s3.open('{}/{}'.format(DIR_wh, 'clean_googl.pkl')), allow_pickle=True)

    # Load pickle data locally
    # aapl_df = np.load('clean_aapl.pkl', allow_pickle=True)
    # amzn_df = np.load('clean_amzn.pkl', allow_pickle=True)
    # googl_df = np.load('clean_googl.pkl', allow_pickle=True)

    # Set Target Variable
    target_aapl = pd.DataFrame(aapl_df['Adj Close'])
    target_amzn = pd.DataFrame(amzn_df['Adj Close'])
    target_googl = pd.DataFrame(googl_df['Adj Close'])
    # Selecting the Features
    features = ['Close', 'High', 'Low', 'Open', 'Volume']

    # feature extraction on the AAPL stock market dataset
    # Scaling AAPL dataframe
    scaler = MinMaxScaler()
    feature_transform_aapl = scaler.fit_transform(aapl_df[features])
    feature_transform_aapl = pd.DataFrame(columns=features, data=feature_transform_aapl, index=aapl_df.index)

    # Splitting to Training set and Test set
    timesplit = TimeSeriesSplit(n_splits=10)
    for train_index, test_index in timesplit.split(feature_transform_aapl):
        X_train, X_test = feature_transform_aapl[:len(train_index)], feature_transform_aapl[len(train_index): (len(train_index)+len(test_index))]
        y_train, y_test = target_aapl[:len(train_index)].values.ravel(), target_aapl[len(train_index): (len(train_index)+len(test_index))].values.ravel()

    # Push extracted features to data warehouse
    DIR_aapl = ''                               # Insert here
    with s3.open('{}/{}'.format(DIR_aapl, 'X_train_aapl.pkl'), 'wb') as f:
        f.write(pickle.dumps(X_train))
    with s3.open('{}/{}'.format(DIR_aapl, 'X_test_aapl.pkl'), 'wb') as f:
        f.write(pickle.dumps(X_test))
    with s3.open('{}/{}'.format(DIR_aapl, 'y_train_aapl.pkl'), 'wb') as f:
        f.write(pickle.dumps(y_train))
    with s3.open('{}/{}'.format(DIR_aapl, 'y_test_aapl.pkl'), 'wb') as f:
        f.write(pickle.dumps(y_test))

    # feature extraction on the AMZN stock market dataset
    # Scaling AMZN dataframe
    scaler = MinMaxScaler()
    feature_transform_amzn = scaler.fit_transform(amzn_df[features])
    feature_transform_amzn = pd.DataFrame(columns=features, data=feature_transform_amzn, index=amzn_df.index)

    # Splitting to Training set and Test set
    timesplit = TimeSeriesSplit(n_splits=10)
    for train_index, test_index in timesplit.split(feature_transform_amzn):
        X_train, X_test = feature_transform_amzn[:len(train_index)], feature_transform_amzn[len(train_index): (len(train_index)+len(test_index))]
        y_train, y_test = target_amzn[:len(train_index)].values.ravel(), target_amzn[len(train_index): (len(train_index)+len(test_index))].values.ravel()

    # Push extracted features to data warehouse
    DIR_amzn = ''                               # Insert here
    with s3.open('{}/{}'.format(DIR_amzn, 'X_train_amzn.pkl'), 'wb') as f:
        f.write(pickle.dumps(X_train))
    with s3.open('{}/{}'.format(DIR_amzn, 'X_test_amzn.pkl'), 'wb') as f:
        f.write(pickle.dumps(X_test))
    with s3.open('{}/{}'.format(DIR_amzn, 'y_train_amzn.pkl'), 'wb') as f:
        f.write(pickle.dumps(y_train))
    with s3.open('{}/{}'.format(DIR_amzn, 'y_test_amzn.pkl'), 'wb') as f:
        f.write(pickle.dumps(y_test))

    # feature extraction on the GOOGL stock market dataset
    # Scaling GOOGL dataframe
    scaler = MinMaxScaler()
    feature_transform_googl = scaler.fit_transform(googl_df[features])
    feature_transform_googl = pd.DataFrame(columns=features, data=feature_transform_googl, index=googl_df.index)

    # Splitting to Training set and Test set
    timesplit = TimeSeriesSplit(n_splits=10)
    for train_index, test_index in timesplit.split(feature_transform_googl):
        X_train, X_test = feature_transform_googl[:len(train_index)], feature_transform_googl[len(train_index): (len(train_index)+len(test_index))]
        y_train, y_test = target_googl[:len(train_index)].values.ravel(), target_googl[len(train_index): (len(train_index)+len(test_index))].values.ravel()

    # Push extracted features to data warehouse
    DIR_googl = ''                              # Insert here
    with s3.open('{}/{}'.format(DIR_googl, 'X_train_googl.pkl'), 'wb') as f:
        f.write(pickle.dumps(X_train))
    with s3.open('{}/{}'.format(DIR_googl, 'X_test_googl.pkl'), 'wb') as f:
        f.write(pickle.dumps(X_test))
    with s3.open('{}/{}'.format(DIR_googl, 'y_train_googl.pkl'), 'wb') as f:
        f.write(pickle.dumps(y_train))
    with s3.open('{}/{}'.format(DIR_googl, 'y_test_googl.pkl'), 'wb') as f:
        f.write(pickle.dumps(y_test))




