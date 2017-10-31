import pandas as pd
from datetime import datetime
import csv
import matplotlib.pyplot as plt
import matplotlib.dates as mdates
import matplotlib.patches as mpatches

headers = ['Benchmark','Supplier','Size','Score']
df = pd.read_csv('data.csv',names=headers)

i = 0
for benchmark, df_benchmark in df.groupby('Benchmark'):
    for size, df_size in df_benchmark.groupby('Size'):
        print(df_size)
        plt.bar(df_size['Supplier'], df_size['Score'])

        benchmark_patch = mpatches.Patch(label=benchmark)
        size_patch = mpatches.Patch(label=size)
        plt.legend(handles=[benchmark_patch, size_patch])

        i += 1

        # rotate labels
        plt.gcf().subplots_adjust(bottom=0.5)
        plt.xticks(rotation=90)
        plt.savefig(str(i))
        #plt.show()
        plt.clf()
