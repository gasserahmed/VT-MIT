from datetime import timedelta
from airflow import DAG
from airflow.operators.python_operator import PythonOperator
from airflow.utils.dates import days_ago
from datetime import datetime
from load_db import load_data

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
    'dag_db',
    default_args=default_args,
    description='push to db',
    schedule_interval=timedelta(days=1),
)


load_db = PythonOperator(
    task_id='push_to_db',
    python_callable=load_data,
    dag=dag,
)

load_db