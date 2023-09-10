import time
import pandas as pd
from kafka import KafkaProducer
from json import dumps
import requests

def kafka_producer():
    tickers = ["BBURG", "RDFRD", "RNOKE"] # Blacksburg vs Radford vs Roanoke

    api_urls = {}
    # API URLs for Blacksburg, Radford, and Roanoke weathers
    api_urls["BBURG"] = "https://api.openweathermap.org/data/3.0/onecall?lat=37.229572&lon=-80.413940&units=imperial&appid=6cac08fbff1ee18e7adad5d5ead27a93"
    api_urls["RDFRD"] = "https://api.openweathermap.org/data/3.0/onecall?lat=37.1232&lon=-80.5683&units=imperial&appid=6cac08fbff1ee18e7adad5d5ead27a93"
    api_urls["RNOKE"] = "https://api.openweathermap.org/data/3.0/onecall?lat=37.270969&lon=-79.941429&units=imperial&appid=6cac08fbff1ee18e7adad5d5ead27a93"

    producer = KafkaProducer(bootstrap_servers=['54.196.246.52:9116'],  # change ip and port number here
                             value_serializer=lambda x:
                             dumps(x).encode('utf-8'))

    t_end = time.time() + 60 * 1  # Amount of time data is sent for in seconds
    while time.time() < t_end:
      new_rows = []
      df_stream = pd.DataFrame(columns=["City", "Temperature", "Feels Like", "Humidity", "Wind Speed", "Timestamp"])
      for ticker in tickers:
        response = requests.get(api_urls[ticker]).json()
        current_weather = response.get('current')  # get current ticker's weather
        new_row = {
            "City": ticker,
            "Temperature": current_weather.get('temp'),
            "Feels Like": current_weather.get('feels_like'),
            "Humidity": current_weather.get('humidity'),
            "Wind Speed": current_weather.get('wind_speed'),
            "Timestamp": current_weather.get('dt')
            }
        new_rows.append(new_row)
      df_stream = pd.concat([df_stream, pd.DataFrame(new_rows)], ignore_index=True)
      producer.send('WeatherData', value=df_stream.to_json())  # Add topic name here
    print("done producing")


kafka_producer()