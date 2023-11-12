from sqlalchemy import create_engine, inspect
import numpy as np
from s3fs.core import S3FileSystem


def load_data():
    s3 = S3FileSystem()
    # S3 bucket directory (data warehouse)
    DIR_wh = 's3://ece5984-bucket-gasser18/Lab2/lab2-transform'  # Insert here
    # Get data from S3 bucket as a pickle file
    aapl_df = np.load(s3.open('{}/{}'.format(DIR_wh, 'clean_aapl.pkl')), allow_pickle=True)
    amzn_df = np.load(s3.open('{}/{}'.format(DIR_wh, 'clean_amzn.pkl')), allow_pickle=True)
    googl_df = np.load(s3.open('{}/{}'.format(DIR_wh, 'clean_googl.pkl')), allow_pickle=True)

    # create sqlalchemy engine
    engine = create_engine("mysql+pymysql://{user}:{pw}@{endpnt}"
                           .format(user="admin",
                                   pw="wY59A93oZFz2Vff7sjpd",
                                   endpnt="database-eng2.cwgvgleixj0c.us-east-1.rds.amazonaws.com"))
    # Check if the database already exists
    inspector = inspect(engine)
    if 'gasser18' not in inspector.get_schema_names():
        # If the database doesn't exist, create it
        engine.execute("CREATE DATABASE {db}"
                       .format(db="gasser18"))  # Insert pid here
    else:
        print("Database 'gasser18' already exists")

    engine = create_engine("mysql+pymysql://{user}:{pw}@{endpnt}/{db}"
                           .format(user="admin",
                                   pw="wY59A93oZFz2Vff7sjpd",
                                   endpnt="database-eng2.cwgvgleixj0c.us-east-1.rds.amazonaws.com",
                                   db="gasser18"))

    # Insert whole DataFrame into MySQL DB
    aapl_df.to_sql('aapl_clean',  # put table name here
                   con=engine, if_exists='replace', chunksize=1000)
    amzn_df.to_sql('amzn_clean',  # put table name here
                   con=engine, if_exists='replace', chunksize=1000)
    googl_df.to_sql('googl_clean',  # put table name here
                    con=engine, if_exists='replace', chunksize=1000)