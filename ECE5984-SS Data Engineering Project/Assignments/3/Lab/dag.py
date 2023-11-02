from datetime import timedelta
from airflow import DAG
from airflow.operators.python_operator import PythonOperator
from airflow.utils.dates import days_ago
from datetime import datetime
from batch_ingest import ingest_data
from transform import transform_data
from featureExtraction import feature_extract
from build_train_model import build_train

default_args = {
    'owner': 'airflow',
    'depends_on_past': False,
    'start_date': datetime(2023, 10, 12),
    'email': ['airflow@example.com'],
    'email_on_failure': False,
    'email_on_retry': False,
    'retries': 1,
    'retry_delay': timedelta(minutes=1)
}

dag = DAG(
    'batch_ingest_dag',
    default_args=default_args,
    description='ingest stock data',
    schedule_interval=timedelta(days=1),
)

ingest_etl = PythonOperator(
    task_id='ingest_dataset',
    python_callable=ingest_data,
    dag=dag,
)

transform_etl = PythonOperator(
    task_id='transform_dataset',
    python_callable=transform_data,
    dag=dag,
)

feature_etl = PythonOperator(
    task_id='feature_dataset',
    python_callable=feature_extract,
    dag=dag,
)

model_etl = PythonOperator(
   task_id='build_train_dataset',
   python_callable=build_train,
   dag=dag,
)

ingest_etl >> transform_etl >> feature_etl >> model_etl