import yfinance as yf
import time
import pandas as pd
from kafka import KafkaProducer
from json import dumps

def kafka_producer():
    producer = KafkaProducer(bootstrap_servers=['54.196.246.52:9115'],  # change ip and port number here
                             value_serializer=lambda x:
                             dumps(x).encode('utf-8'))

    tickers = ["AAPL", "GOOGL", "AMZN"]

    t_end = time.time() + 60 * 1  # Amount of time data is sent for in seconds
    data_frames = []  # Create a list to store DataFrames
    while time.time() < t_end:
        data = []
        for ticker in tickers:
            stock = yf.Ticker(ticker)
            quote = stock.info
            new_row = {
                "Name": ticker,
                "Price": quote["regularMarketOpen"],
                "Timestamp": time.time()
            }
            data.append(new_row)
        df_stream = pd.DataFrame(data)
        data_frames.append(df_stream)  # Append each DataFrame to the list
        producer.send('StockData', value=df_stream.to_json(orient='records'))  # Send the DataFrame as JSON
    # Concatenate all DataFrames before exiting the loop
    final_df = pd.concat(data_frames, ignore_index=True)
    print(final_df)
    print("done producing")

kafka_producer()
