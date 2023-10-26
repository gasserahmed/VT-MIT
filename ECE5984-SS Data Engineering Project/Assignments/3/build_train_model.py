import s3fs
from s3fs.core import S3FileSystem
import numpy as np
import pickle
import pandas as pd
import numpy as np
from keras.layers import LSTM, Dense, Dropout
from keras.models import Sequential
from keras.layers import Dense
import tempfile

def build_train():

    s3 = S3FileSystem()
    # S3 bucket directory (data warehouse)
    DIR_aapl = ''                       # Insert here
    DIR_amzn = ''                       # Insert here
    DIR_googl = ''                      # Insert here

    X_train_aapl = np.load(s3.open('{}/{}'.format(DIR_aapl, 'X_train_aapl.pkl')), allow_pickle=True)
    X_test_aapl = np.load(s3.open('{}/{}'.format(DIR_aapl, 'X_test_aapl.pkl')), allow_pickle=True)
    y_train_aapl = np.load(s3.open('{}/{}'.format(DIR_aapl, 'y_train_aapl.pkl')), allow_pickle=True)
    y_test_aapl = np.load(s3.open('{}/{}'.format(DIR_aapl, 'y_test_aapl.pkl')), allow_pickle=True)

    X_train_amzn = np.load(s3.open('{}/{}'.format(DIR_amzn, 'X_train_amzn.pkl')), allow_pickle=True)
    X_test_amzn = np.load(s3.open('{}/{}'.format(DIR_amzn, 'X_test_amzn.pkl')), allow_pickle=True)
    y_train_amzn = np.load(s3.open('{}/{}'.format(DIR_amzn, 'y_train_amzn.pkl')), allow_pickle=True)
    y_test_amzn = np.load(s3.open('{}/{}'.format(DIR_amzn, 'y_test_amzn.pkl')), allow_pickle=True)

    X_train_googl = np.load(s3.open('{}/{}'.format(DIR_googl, 'X_train_googl.pkl')), allow_pickle=True)
    X_test_googl = np.load(s3.open('{}/{}'.format(DIR_googl, 'X_test_googl.pkl')), allow_pickle=True)
    y_train_googl = np.load(s3.open('{}/{}'.format(DIR_googl, 'y_train_googl.pkl')), allow_pickle=True)
    y_test_googl = np.load(s3.open('{}/{}'.format(DIR_googl, 'y_test_googl.pkl')), allow_pickle=True)

    # AAPL dataset model
    # Process the data for LSTM
    trainX_aapl = np.array(X_train_aapl)
    testX_aapl = np.array(X_test_aapl)
    X_train_aapl = trainX_aapl.reshape(X_train_aapl.shape[0], 1, X_train_aapl.shape[1])
    X_test_aapl = testX_aapl.reshape(X_test_aapl.shape[0], 1, X_test_aapl.shape[1])

    # Building the LSTM Model
    lstm_aapl = Sequential()
    lstm_aapl.add(LSTM(32, input_shape=(1, trainX_aapl.shape[1]), activation='relu', return_sequences = False))
    lstm_aapl.add(Dense(1))
    lstm_aapl.compile(loss='mean_squared_error', optimizer ='adam')

    # Model Training
    history_aapl = lstm_aapl.fit(X_train_aapl, y_train_aapl, epochs=25, batch_size=8, verbose=1, shuffle=False, validation_data=(X_test_aapl, y_test_aapl))

    # Save model temporarily
    with tempfile.TemporaryDirectory() as tempdir:
        lstm_aapl.save(f"{tempdir}/lstm_aapl.h5")
        # Push saved model to S3
        s3.put(f"{tempdir}/lstm_aapl.h5", f"{DIR_aapl}/lstm_aapl.h5")

    # AMZN dataset model
    # Process the data for LSTM
    trainX_amzn = np.array(X_train_amzn)
    testX_amzn = np.array(X_test_amzn)
    X_train_amzn = trainX_amzn.reshape(X_train_amzn.shape[0], 1, X_train_amzn.shape[1])
    X_test_amzn = testX_amzn.reshape(X_test_amzn.shape[0], 1, X_test_amzn.shape[1])

    # Building the LSTM Model
    lstm_amzn = Sequential()
    lstm_amzn.add(LSTM(32, input_shape=(1, trainX_amzn.shape[1]), activation='relu', return_sequences = False))
    lstm_amzn.add(Dense(1))
    lstm_amzn.compile(loss='mean_squared_error', optimizer ='adam')

    # Model Training
    history_amzn = lstm_amzn.fit(X_train_amzn, y_train_amzn, epochs=25, batch_size=8, verbose=1, shuffle=False, validation_data=(X_test_amzn, y_test_amzn))

    # Save model temporarily
    with tempfile.TemporaryDirectory() as tempdir:
        lstm_amzn.save(f"{tempdir}/lstm_amzn.h5")
        # Push saved model to S3
        s3.put(f"{tempdir}/lstm_amzn.h5", f"{DIR_amzn}/lstm_amzn.h5")

    # AMZN dataset model
    # Process the data for LSTM
    trainX_googl = np.array(X_train_googl)
    testX_googl = np.array(X_test_googl)
    X_train_googl = trainX_googl.reshape(X_train_googl.shape[0], 1, X_train_googl.shape[1])
    X_test_googl = testX_googl.reshape(X_test_googl.shape[0], 1, X_test_googl.shape[1])

    # Building the LSTM Model
    lstm_googl = Sequential()
    lstm_googl.add(LSTM(32, input_shape=(1, trainX_googl.shape[1]), activation='relu', return_sequences = False))
    lstm_googl.add(Dense(1))
    lstm_googl.compile(loss='mean_squared_error', optimizer ='adam')

    # Model Training
    history_googl = lstm_googl.fit(X_train_googl, y_train_googl, epochs=25, batch_size=8, verbose=1, shuffle=False, validation_data=(X_test_googl, y_test_googl))

    # Save model temporarily
    with tempfile.TemporaryDirectory() as tempdir:
        lstm_googl.save(f"{tempdir}/lstm_googl.h5")
        # Push saved temporary model to S3
        s3.put(f"{tempdir}/lstm_googl.h5", f"{DIR_googl}/lstm_googl.h5")