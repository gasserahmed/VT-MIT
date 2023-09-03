from datetime import timedelta
from airflow import DAG
from airflow.operators.python_operator import PythonOperator
from airflow.utils.dates import days_ago
from datetime import datetime
from batch_ingest import ingest_data

default_args = {
    'owner': 'airflow',
    'depends_on_past': False,
    'start_date': datetime(2023, 3, 9),
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


ingest_etl