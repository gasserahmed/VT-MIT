import yfinance as yf
import time
import pandas as pd
from kafka import KafkaProducer
from json import dumps


def kafka_producer():
    producer = KafkaProducer(bootstrap_servers=['54.196.246.52:9139'],  # change ip and port number here
                             value_serializer=lambda x:
                             dumps(x).encode('utf-8'))

    tickers = ["AAPL", "GOOGL", "AMZN"]

    t_end = time.time() + 60 * 1  # Amount of time data is sent for in seconds
    while time.time() < t_end:
      df_stream = pd.DataFrame(columns=["Name", "Price", "Timestamp"])
      for ticker in tickers:
        stock = yf.Ticker(ticker)
        quote = stock.info
        new_row = {
            "Name": ticker,
            "Price": quote["regularMarketOpen"],
            "Timestamp": time.time()
            }
        df_stream = df_stream._append(new_row, ignore_index=True)
        producer.send('StockData', value=df_stream.to_json())  # Add topic name here
    print("done producing")


kafka_producer()