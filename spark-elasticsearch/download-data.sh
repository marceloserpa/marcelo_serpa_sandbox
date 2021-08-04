#!/bin/bash

mkdir datasets

wget https://files.grouplens.org/datasets/movielens/ml-latest-small.zip

unzip ml-latest-small.zip -d datasets

rm ml-latest-small.zip

echo 'Completed'