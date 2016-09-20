#!/bin/bash

curtime=$(date)
git add *
git commit -m "Backup at ${curtime}"
git push origin master
