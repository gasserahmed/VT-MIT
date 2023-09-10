from datetime import timedelta
from airflow import DAG
from airflow.operators.python_operator import PythonOperator
from airflow.utils.dates import days_ago
from datetime import datetime
from stream_ingest import kafka_consumer

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
    'stream_ingest_dag',
    default_args=default_args,
    description='ingest weather data',
    schedule_interval=timedelta(days=1),
)


kafka_cons = PythonOperator(
    task_id='consume_data',
    python_callable=kafka_consumer,
    dag=dag,
)

kafka_cons