#!/usr/bin/python
import sys


# input comes from STDIN (standard input)
for line in sys.stdin:
    # remove leading and trailing whitespace
    line = line.strip()
    # split the line into words
    words = line.split()
    # initialize the dictionary for the line
    dict = {}
    for word in words:
        if word in dict:
            dict[word] = dict[word] + 1
        else:
            dict[word] = 1
    for x in dict.keys():
        print '%s\t%s' % (x, dict[x])
