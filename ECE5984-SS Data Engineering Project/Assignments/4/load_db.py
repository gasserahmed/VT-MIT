from sqlalchemy import create_engine
import numpy as np
from s3fs.core import S3FileSystem


def load_data():
    s3 = S3FileSystem()
    # S3 bucket directory (data warehouse)
    DIR_wh = ''  # Insert here
    # Get data from S3 bucket as a pickle file
    aapl_df = np.load(s3.open('{}/{}'.format(DIR_wh, 'clean_aapl.pkl')), allow_pickle=True)
    amzn_df = np.load(s3.open('{}/{}'.format(DIR_wh, 'clean_amzn.pkl')), allow_pickle=True)
    googl_df = np.load(s3.open('{}/{}'.format(DIR_wh, 'clean_googl.pkl')), allow_pickle=True)

    # create sqlalchemy engine
    engine = create_engine("mysql+pymysql://{user}:{pw}@{endpnt}"
                           .format(user="admin",
                                   pw="admin12345",
                                   endpnt="database-eng2.cwgvgleixj0c.us-east-1.rds.amazonaws.com"))

    engine.execute("CREATE DATABASE {db}"
                   .format(db=""))  # Insert pid here

    engine = create_engine("mysql+pymysql://{user}:{pw}@{endpnt}/{db}"
                           .format(user="admin",
                                   pw="admin12345",
                                   endpnt="database-eng2.cwgvgleixj0c.us-east-1.rds.amazonaws.com",
                                   db=""))


    # Insert whole DataFrame into MySQL DB
    aapl_df.to_sql('aapl_clean',  # put table name here
                   con=engine, if_exists='replace', chunksize=1000)
    amzn_df.to_sql('amzn_clean',  # put table name here
                   con=engine, if_exists='replace', chunksize=1000)
    googl_df.to_sql('googl_clean',  # put table name here
                    con=engine, if_exists='replace', chunksize=1000)